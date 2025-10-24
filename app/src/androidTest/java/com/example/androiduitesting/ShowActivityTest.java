package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testActivitySwitches() {
        // Add a city first
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click the first city in the list
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Check that ShowActivity is displayed (cityTextView is part of ShowActivity)
        onView(withId(R.id.cityTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistency() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Toronto"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click it
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Verify that the same city name is shown in ShowActivity
        onView(withId(R.id.cityTextView)).check(matches(withText(containsString("Toronto"))));
    }


    @Test
    public void testBackButtonFunctionality() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Vancouver"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click the city to go to ShowActivity
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Tap back button
        onView(withId(R.id.backButton)).perform(click());

        // Check that we're back to MainActivity (list is visible again)
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
