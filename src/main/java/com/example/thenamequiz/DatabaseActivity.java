package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        create_layout();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        create_layout();
    }

    private void create_layout()
    {
        final LinearLayout linear_layout_outer = findViewById(R.id.linear_layout);

        linear_layout_outer.removeAllViews();

        final ArrayList<Database.Card> cards = Database.the().cards();
        cards.forEach((card) -> {
            CardView card_view = new CardView(getBaseContext());

            LinearLayout linear_layout = new LinearLayout(getBaseContext());
            linear_layout.setOrientation(LinearLayout.HORIZONTAL);

            ImageView image_view = new ImageView(getBaseContext());
            final int size = 450;
            LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(size, size);
            image_view.setLayoutParams(layout_params);

            image_view.setImageBitmap(card.bitmap);

            TextView text_view = new TextView(getBaseContext());
            text_view.setText(card.name);

            linear_layout.addView(image_view);
            linear_layout.addView(text_view);

            card_view.addView(linear_layout);

            linear_layout_outer.addView(card_view);
        });
    }

    public void add_entry(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("SHOULD_FINISH", true);
        startActivity(intent);
    }
}