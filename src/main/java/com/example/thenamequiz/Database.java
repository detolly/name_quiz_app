package com.example.thenamequiz;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

public class Database {

    private static Database static_database = new Database();
    public static Database the() { return static_database; };

    private ArrayList<Card> cards = new ArrayList<>();

    class Card
    {
        public String name;
        public Bitmap bitmap;

        Card(String name, Bitmap bitmap) { this.name = name; this.bitmap = bitmap; }
    }

    Database()
    {

    }

    public final ArrayList<Card> cards() { return this.cards; }

    public void add_card(String name, Bitmap bitmap)
    {
        cards.add(new Card(name, bitmap));
    }

}
