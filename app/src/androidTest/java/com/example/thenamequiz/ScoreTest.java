package com.example.thenamequiz;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

import android.app.Activity;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@RunWith(AndroidJUnit4.class)
public class ScoreTest {

    @Rule
    public ActivityScenarioRule<QuizActivity> main_activity_rule =
            new ActivityScenarioRule<>(QuizActivity.class);

    @Test
    public void wrong_answer()
    {
        QuizState current_state = App.database().quiz.get();
        final int[] button_ids = { R.id.quizbutton1, R.id.quizbutton2, R.id.quizbutton3 };
        ArrayList<Integer> available_ids = new ArrayList<Integer>();
        available_ids.add(0);
        available_ids.add(1);
        available_ids.add(2);
        available_ids.remove(current_state.correct_index);
        onView(withId(button_ids[available_ids.get(0)])).perform(click());

        current_state = App.database().quiz.get();
        assertEquals(0, current_state.current_score);
    }

    @Test
    public void right_answer()
    {
        QuizState current_state = App.database().quiz.get();
        final int previous_score = current_state.current_score;

        final int[] button_ids = { R.id.quizbutton1, R.id.quizbutton2, R.id.quizbutton3 };
        onView(withId(button_ids[current_state.correct_index])).perform(click());

        current_state = App.database().quiz.get();
        assertEquals(previous_score+1, current_state.current_score);
    }


}