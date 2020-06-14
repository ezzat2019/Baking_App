package com.example.programmer.bakingapp;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleITest2 {
    @Rule
    public ActivityTestRule<RecipesActivity> activityTestRule =
            new ActivityTestRule<>(RecipesActivity.class);

    // Looks for an EditText with id = "R.id.etInput"
    // Types the text "Hello" into the EditText
    // Verifies the EditText has text "Hello"
    @Test
    public void validateRecycleRecipeInt() {
        try {
            Thread.sleep(Long.parseLong("10000"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rec_recipes)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.rec_steps)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    }


}
