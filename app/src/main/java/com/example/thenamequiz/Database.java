package com.example.thenamequiz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Database {

    public CardAccessObject cards;
    public QuizStateAccessObject quiz;
    public AppDatabase database;

    Database(Context context)
    {
        database = Room.databaseBuilder(context, AppDatabase.class, "quiz_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback() {
            @Override
            public void onCreate(SupportSQLiteDatabase db)
            {
                new Thread(() -> {
                    pre_populate();
                }).start();
            }
        }).build();
        cards = database.card_access_object();
        quiz = database.quiz_access_object();
    }

    private void pre_populate()
    {
        Bitmap bm = BitmapFactory.decodeResource(App.context().getResources(), R.drawable.tonje1);
        add_card("tonje", bm);

        Bitmap bm2 = BitmapFactory.decodeResource(App.context().getResources(), R.drawable.golde);
        add_card("PogChamp", bm2);

        Bitmap bm3 = BitmapFactory.decodeResource(App.context().getResources(), R.drawable.frogman);
        add_card("frogman", bm3);

        QuizState current_state = new QuizState();

        ArrayList<Card> cards = App.database().cards();

        Random r = new Random();

        int one = r.nextInt(cards.size());
        int two = 0;
        while((two = r.nextInt(cards.size())) == one)
            continue;

        int three = 0;
        while((three = r.nextInt(cards.size())) == one || two == three)
            continue;

        current_state.id = 0;

        current_state.id_one = cards.get(one).id;
        current_state.id_two = cards.get(two).id;
        current_state.id_three = cards.get(three).id;

        int index = r.nextInt(3);

        current_state.correct_index = index;

        quiz.insert(current_state);
    }

    public final ArrayList<Card> cards() { return (ArrayList<Card>) cards.getAll(); }

    public long add_card(String name, Bitmap bitmap)
    {
        return cards.insert(new Card(name, bitmap));
    }

    public long add_card(Card card)
    {
        return cards.insert(card);
    }

    public void remove_card(Card card) { cards.delete(card); }

}
