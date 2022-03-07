package com.example.thenamequiz;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.content.Context;

import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Iterator;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityScenarioRule<SelectActivity> main_activity_rule =
            new ActivityScenarioRule<>(SelectActivity.class);

    private Activity getActivityInstance(){
        final Activity[] currentActivity = {null};

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable(){
            public void run(){
                Collection<Activity> resumedActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                Iterator<Activity> it = resumedActivity.iterator();
                currentActivity[0] = it.next();
            }
        });

        return currentActivity[0];
    }

    @Test
    public void main_button_to_database()
    {
        onView(withId(R.id.button)).perform(click());
        assertEquals(DatabaseActivity.class, getActivityInstance().getClass());
    }

    @Test
    public void main_button_to_quiz()
    {
        onView(withId(R.id.button2)).perform(click());
        assertEquals(QuizActivity.class, getActivityInstance().getClass());
    }

    @Test
    public void main_button_to_add()
    {
        onView(withId(R.id.button3)).perform(click());
        assertEquals(AddActivity.class, getActivityInstance().getClass());
    }
}