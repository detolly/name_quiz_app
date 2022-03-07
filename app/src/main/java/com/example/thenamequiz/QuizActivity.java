package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;

    TextView score_text;
    TextView high_score_text;
    ImageView image_view;

    QuizViewModel quiz_viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        button1 = findViewById(R.id.quizbutton1);
        button2 = findViewById(R.id.quizbutton2);
        button3 = findViewById(R.id.quizbutton3);

        score_text = findViewById(R.id.quiz_score);
        high_score_text = findViewById(R.id.quiz_high_score);

        image_view = findViewById(R.id.quizimageview);

        quiz_viewmodel = new ViewModelProvider(this).get(QuizViewModel.class);
        quiz_viewmodel.init();

        final Observer<QuizState> observer = quizState -> {
            score_text.setText("Score: " + quizState.current_score);
            high_score_text.setText("High Score: " + quizState.high_score);
            Card card1 = App.database().cards.get(quizState.id_one);
            Card card2 = App.database().cards.get(quizState.id_two);
            Card card3 = App.database().cards.get(quizState.id_three);
            Card[] cards = { card1, card2, card3 };
            button1.setText(card1.name);
            button2.setText(card2.name);
            button3.setText(card3.name);
            image_view.setImageBitmap(cards[quizState.correct_index].bitmap);
        };

        quiz_viewmodel.get_quiz_state().observe(this, observer);
    }

    public void selected(View view)
    {
        Button clicked_button = (Button)view;
        int clicked_index = 0;
        if (clicked_button == button1) clicked_index = 0;
        else if (clicked_button == button2) clicked_index = 1;
        else if (clicked_button == button3) clicked_index = 2;
        else return;

        quiz_viewmodel.did_click(clicked_index);
    }

}