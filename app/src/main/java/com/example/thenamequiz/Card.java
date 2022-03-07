package com.example.thenamequiz;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"name"},
        unique = true)})
public class Card implements Comparable<Card>
{
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "bitmap")
    @NonNull
    public Bitmap bitmap;

    public Card(String name, Bitmap bitmap) { this.name = name; this.bitmap = bitmap; }

    @Override
    public int compareTo(Card card) {
        return name.compareTo(card.name);
    }
}