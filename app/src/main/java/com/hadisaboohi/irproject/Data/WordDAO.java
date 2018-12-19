package com.hadisaboohi.irproject.Data;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WordDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWords(ArrayList<Word> words);

    @Update
    void updateWords(Word... words);

    @Query("SELECT * FROM word")
    List<Word> getAllWords();

    @Query("SELECT * from word where id = :id LIMIT 1")
    Word getWordById(int id);

    @Query("SELECT * from word where body = :body LIMIT 1")
    Word getWordByBody(String body);
}
