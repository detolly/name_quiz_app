package com.example.thenamequiz;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardAccessObject {

    @Query("SELECT * FROM Card WHERE id=:id")
    Card get(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Card card);

    @Delete
    void delete(Card card);

    @Query("SELECT * FROM Card")
    List<Card> getAll();


}
