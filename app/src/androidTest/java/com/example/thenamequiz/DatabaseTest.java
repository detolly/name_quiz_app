package com.example.thenamequiz;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    @Rule
    public ActivityScenarioRule<DatabaseActivity> main_activity_rule =
            new ActivityScenarioRule<>(DatabaseActivity.class);

    @Test
    public void number_of_registered_cards()
    {
        Bitmap bm2 = BitmapFactory.decodeResource(App.context().getResources(), R.drawable.golde);
        Card c = new Card("test_resource_ignore", bm2);
        c.id = 123123123;
        try {
            List<Card> cards = App.database().database.card_access_object().getAll();
            final int size_before = cards.size();

            App.database().add_card(c);

            cards = App.database().database.card_access_object().getAll();
            int size_after = cards.size();

            assertEquals(size_before+1, size_after);

            App.database().remove_card(c);

            cards = App.database().database.card_access_object().getAll();
            size_after = cards.size();

            assertEquals(size_before, size_after);
        } catch (Exception e)
        {
            throw e;
        } finally {

            App.database().remove_card(c);

        }

    }
}
