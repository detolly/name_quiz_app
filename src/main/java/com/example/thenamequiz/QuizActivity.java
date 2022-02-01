package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;

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

    ImageView image_view;

    Button correct_button;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        button1 = findViewById(R.id.quizbutton1);
        button2 = findViewById(R.id.quizbutton2);
        button3 = findViewById(R.id.quizbutton3);

        score_text = findViewById(R.id.quiz_score);

        image_view = findViewById(R.id.quizimageview);

        update_quiz_view();
    }

    public void selected(View view)
    {
        Button clicked_button = (Button)view;
        if (clicked_button == correct_button)
        {
            increase_score();
            update_quiz_view();
        } else {
            stop_score();
        }
    }

    void stop_score()
    {
        score = 0;
        set_score_text();
    }

    void increase_score()
    {
        score++;
        set_score_text();
    }

    void set_score_text()
    {
        score_text.setText("Score: " + score);
    }

    void update_quiz_view()
    {
        ArrayList<Database.Card> cards = App.database().cards();
        
        if (cards.size() < 3) {
            finish();
            return;
        }

        Random r = new Random();

        int one = r.nextInt(cards.size());
        int two = 0;
        while((two = r.nextInt(cards.size())) == one)
            continue;

        int three = 0;
        while((three = r.nextInt(cards.size())) == one || two == three)
            continue;

        button1.setText(cards.get(one).name);
        button2.setText(cards.get(two).name);
        button3.setText(cards.get(three).name);

        int index = r.nextInt(3);

        final Button[] buttons = { button1, button2, button3 };
        final int[] is = { one, two, three };

        image_view.setImageBitmap(cards.get(is[index]).bitmap);
        correct_button = buttons[index];

    }
}