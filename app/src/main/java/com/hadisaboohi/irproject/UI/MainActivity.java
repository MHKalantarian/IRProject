package com.hadisaboohi.irproject.UI;

import android.os.Bundle;

import com.hadisaboohi.irproject.R;
import com.hadisaboohi.irproject.Stemmer.Stemmer;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Stemmer persianStemmer = new Stemmer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
