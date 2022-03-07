package com.example.thenamequiz;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuizState {

    @PrimaryKey
    public long id;

    public int current_score;

    public int high_score;

    public int correct_index;

    public long id_one;
    public long id_two;
    public long id_three;

}
