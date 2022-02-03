package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }

    public void switch_to_database_layout(View view)
    {
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }

    public void switch_to_quiz_layout(View view)
    {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void switch_to_add_layout(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

}