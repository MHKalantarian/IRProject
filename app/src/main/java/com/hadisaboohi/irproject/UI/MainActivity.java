package com.hadisaboohi.irproject.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.hadisaboohi.irproject.BuildConfig;
import com.hadisaboohi.irproject.Data.AppDatabase;
import com.hadisaboohi.irproject.Data.Document;
import com.hadisaboohi.irproject.Data.Word;
import com.hadisaboohi.irproject.Helper.Indexer;
import com.hadisaboohi.irproject.Helper.Stemmer;
import com.hadisaboohi.irproject.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static AppDatabase database;
    private static SharedPreferences prefs;
    private Context mContext;
    private RecyclerView documentsRv;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        database = AppDatabase.getInstance(mContext);

        prefs = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        if (prefs.getBoolean("isFirstRun", true))
            new Indexing().execute();
        else
            setupUI();
    }

    private void setupUI() {
        documentsRv = findViewById(R.id.documents_rv);
        documentsRv.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        documentsRv.setAdapter(new DocumentAdapter(mContext, database.documentDAO().getAllDocuments()));

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.length() > 0)
                    search(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() == 0)
                    documentsRv.setAdapter(new DocumentAdapter(mContext, database.documentDAO().getAllDocuments()));
                return true;
            }
        });
    }

    private void search(String query) {
        long startTime = System.currentTimeMillis();
        if (query.contains(" و ")) {
            documentsRv.setAdapter(new DocumentAdapter(mContext, multipleAndSearch(query.split(" و "))));
        } else if (query.contains(" ")) {
            documentsRv.setAdapter(new DocumentAdapter(mContext, multipleSearch(query.split(" "))));
        } else
            try {
                documentsRv.setAdapter(new DocumentAdapter(mContext, database.documentDAO().getDocumentsById(database.wordDAO().getWordByBody(Stemmer.i().stem(query)).getDocumentIds())));
            } catch (Exception e) {
                Toast.makeText(mContext, "Not found!", Toast.LENGTH_LONG).show();
            }
        Toast.makeText(mContext, "Response time : " + (System.currentTimeMillis() - startTime) + "MS", Toast.LENGTH_LONG).show();
    }

    private List<Document> multipleAndSearch(String[] words) {
        Set<Integer> commonDocumentIds = new LinkedHashSet<Integer>(database.wordDAO().getWordByBody(Stemmer.i().stem(words[0])).getDocumentIds());
        for (String body : words) {
            try {
                commonDocumentIds.retainAll(database.wordDAO().getWordByBody(Stemmer.i().stem(body)).getDocumentIds());
            } catch (Exception e) {
                Toast.makeText(mContext, "Not found!", Toast.LENGTH_LONG).show();
            }
        }
        return database.documentDAO().getDocumentsById(new ArrayList<>(commonDocumentIds));
    }

    private List<Document> multipleSearch(String[] words) {
        Log.e("Method", "multipleSearch");
        List<Word> wordList = new ArrayList<>();
        for (String body : words) {
            wordList.add(database.wordDAO().getWordByBody(Stemmer.i().stem(body)));
        }

        List<Integer> foundDocumentIds = new ArrayList<>();
        for (int i = 0; i < wordList.size() - 1; i++) {
            int j = 0, k = 0;
            try {
                while (j < wordList.get(i).getPositionIndices().size() && k < wordList.get(i + 1).getPositionIndices().size()) {
                    Log.e("DocId1", wordList.get(i).getPositionIndices().get(j).getDocumentId() + " j=" + j);
                    Log.e("DocId2", wordList.get(i + 1).getPositionIndices().get(k).getDocumentId() + " k=" + k);
                    if (wordList.get(i).getPositionIndices().get(j).getDocumentId() == wordList.get(i + 1).getPositionIndices().get(k).getDocumentId()) {
                        if (i > 0) {
                            if (foundDocumentIds.contains(wordList.get(i).getPositionIndices().get(j).getDocumentId()))
                                for (Integer position : wordList.get(i).getPositionIndices().get(j).getPositions()) {
                                    if (wordList.get(i + 1).getPositionIndices().get(k).getPositions().contains(
                                            position + words[i].length() + 1
                                    ))
                                        foundDocumentIds.add(wordList.get(i).getPositionIndices().get(j).getDocumentId());
                                }
                        } else {
                            for (Integer position : wordList.get(i).getPositionIndices().get(j).getPositions()) {
                                if (wordList.get(i + 1).getPositionIndices().get(k).getPositions().contains(
                                        position + words[i].length() + 1
                                ))
                                    foundDocumentIds.add(wordList.get(i).getPositionIndices().get(j).getDocumentId());
                            }
                        }
                        j++;
                        k++;
                    } else if (wordList.get(i).getPositionIndices().get(j).getDocumentId() > wordList.get(i + 1).getPositionIndices().get(k).getDocumentId()) {
                        k++;
                    } else if (wordList.get(i).getPositionIndices().get(j).getDocumentId() < wordList.get(i + 1).getPositionIndices().get(k).getDocumentId()) {
                        j++;
                    }
                }
            } catch (Exception e) {
                Toast.makeText(mContext, "Not found!", Toast.LENGTH_LONG).show();
            }
        }

        return database.documentDAO().getDocumentsById(foundDocumentIds);
    }

    private class Indexing extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(mContext, null, "در حال ساخت شاخص...");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            prefs.edit().putBoolean("isFirstRun", false).apply();
            progressDialog.dismiss();
            setupUI();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Indexer.Index(database);
            return null;
        }
    }
}
