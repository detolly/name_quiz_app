package com.example.thenamequiz;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = { Card.class, QuizState.class }, version = 1)
@TypeConverters({ ImageBitmapString.class })
public abstract class AppDatabase extends RoomDatabase {

    public abstract CardAccessObject card_access_object();
    public abstract QuizStateAccessObject quiz_access_object();

}
