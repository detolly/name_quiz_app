package com.example.thenamequiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuizStateAccessObject {

    @Insert
    void insert(QuizState state);

    @Query("SELECT * FROM QuizState WHERE id=0")
    QuizState get();

    @Update
    void update(QuizState state);


}
