package com.example.thenamequiz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

public class Database {

    private ArrayList<Card> cards;

    class Card implements Comparable<Card>
    {
        public String name;
        public Bitmap bitmap;

        Card(String name, Bitmap bitmap) { this.name = name; this.bitmap = bitmap; }

        @Override
        public int compareTo(Card card) {
            return name.compareTo(card.name);
        }
    }

    Database()
    {
        cards = new ArrayList<>();

        Bitmap bm = BitmapFactory.decodeResource(App.context().getResources(), R.drawable.tonje1);
        add_card("tonje", bm);

        Bitmap bm2 = BitmapFactory.decodeResource(App.context().getResources(), R.drawable.golde);
        add_card("PogChamp", bm2);

        Bitmap bm3 = BitmapFactory.decodeResource(App.context().getResources(), R.drawable.frogman);
        add_card("frogman", bm3);
    }

    public final ArrayList<Card> cards() { return this.cards; }

    public void add_card(String name, Bitmap bitmap)
    {
        cards.add(new Card(name, bitmap));
    }

}
