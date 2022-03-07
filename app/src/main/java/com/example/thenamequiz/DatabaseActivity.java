package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        create_layout();
    }

    enum SortingMethod {
        AZ,
        ZA
    }

    SortingMethod sorting_method = SortingMethod.AZ;

    static int offset = 0;

    @Override
    public void onResume()
    {
        super.onResume();
        offset++;
        create_layout();
    }

    public void sorter_a_z(View v)
    {
        sorting_method = SortingMethod.AZ;
        create_layout();
    }

    public void sorter_z_a(View v)
    {
        sorting_method = SortingMethod.ZA;
        create_layout();
    }

    private void create_layout()
    {
        final LinearLayout linear_layout_outer = findViewById(R.id.linear_layout);

        Card[] arr = App.database().cards().toArray(new Card[App.database().cards().size()]);

        switch(sorting_method)
        {
            case AZ:
                Arrays.sort(arr);
                break;
            case ZA:
                Arrays.sort(arr, Collections.reverseOrder());
                break;
        }

        linear_layout_outer.removeAllViews();

        int i = 0;

        int universal_id = 100;

        for (Card card : arr) {
            CardView card_view = new CardView(getBaseContext());
            card_view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            card_view.setId(universal_id++);

            LinearLayout horizontal_layout = new LinearLayout(this);
            horizontal_layout.setOrientation(LinearLayout.HORIZONTAL);

            card_view.addView(horizontal_layout);

            ImageView image_view = new ImageView(getBaseContext());
            image_view.setId(universal_id++);

            final int size = 450;
            LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(size, size);
            image_view.setLayoutParams(layout_params);

            image_view.setImageBitmap(card.bitmap);

            LinearLayout vertical_layout = new LinearLayout(this);
            vertical_layout.setOrientation(LinearLayout.VERTICAL);
            vertical_layout.setGravity(Gravity.CENTER);

            TextView text_view = new TextView(getBaseContext());
            text_view.setId(universal_id++);
            text_view.setPadding(20, 20, 20, 20);
            text_view.setText(card.name);

            Button delete_button = new Button(getBaseContext());
            delete_button.setId(universal_id++);
            delete_button.setPadding(20, 20, 20, 20);
            delete_button.setId(i);
            delete_button.setOnClickListener((View v) -> {
                App.database().remove_card(card);
                linear_layout_outer.removeView(card_view);
            });
            delete_button.setText("Delete");
            delete_button.setBackgroundColor(Color.rgb(210,144,112));

            vertical_layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));

            horizontal_layout.addView(image_view);
            vertical_layout.addView(text_view);
            vertical_layout.addView(delete_button);
            horizontal_layout.addView(vertical_layout);

            linear_layout_outer.addView(card_view);

            i++;
        }

        linear_layout_outer.invalidate();
    }

    public void add_entry(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        intent.putExtra("SHOULD_FINISH", true);
        startActivity(intent);
    }
}