package com.example.thenamequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.net.URI;

public class AddActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;

    private Uri uri;

    private boolean should_finish_after_add = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        should_finish_after_add = intent.getBooleanExtra("SHOULD_FINISH", false);
    }

    @Override
    public void onActivityResult(int request_code, int result_code, Intent data)
    {
        super.onActivityResult(request_code, result_code, data);
        if (request_code == PICK_IMAGE) {
            if (result_code != Activity.RESULT_OK) {
                Log.i("AddActivity onActivityResult", "result_code was not RESULT_OK.");
                return;
            }

            uri = data.getData();
            ImageView image_view = (ImageView) findViewById(R.id.imageView2);
            image_view.setImageURI(uri);
        }
    }

    public void select_image(View view)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    public void add_image(View view)
    {
        EditText text = (EditText)findViewById(R.id.editTextTextPersonName2);
        String the_text = text.getText().toString();

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            Database.the().add_card(the_text, bitmap);
        } catch (Exception e)
        {
            return;
        }

        if (should_finish_after_add)
            finish();
    }
}