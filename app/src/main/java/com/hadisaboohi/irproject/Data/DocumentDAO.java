package com.hadisaboohi.irproject.Data;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DocumentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDocuments(ArrayList<Document> documents);

    @Update
    void updateDocuments(Document... documents);

    @Query("SELECT * FROM document")
    List<Document> getAllDocuments();

    @Query("SELECT * from document where id = :id LIMIT 1")
    Document getDocumentById(int id);

    @Query("SELECT * FROM document WHERE id IN (:ids)")
    List<Document> getDocumentsById(List<Integer> ids);
}
