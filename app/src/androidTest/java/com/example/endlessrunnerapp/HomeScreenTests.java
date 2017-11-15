package com.example.endlessrunnerapp;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.google.android.gms.games.leaderboard.Leaderboard;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Sean Nemann on 11/15/2017.
 */

public class HomeScreenTests {

    @Rule
    public IntentsTestRule<HomeScreenActivity> mActivityRule = new IntentsTestRule<>(
            HomeScreenActivity.class);

    @Test
    public void testHelpScreenLaunch()
    {
        onView(withId(R.id.buttonHelp)).perform(click());
        intended(hasComponent(HelpActivity.class.getName()));
    }

    @Test
    public void testLeaderBoardsScreenLaunch()
    {
        onView(withId(R.id.buttonLeaderBoards)).perform(click());
        intended(hasComponent(LeaderBoardsActivity.class.getName()));
    }

    @Test
    public void testStatsScreenLaunch()
    {
        onView(withId(R.id.buttonStats)).perform(click());
        intended(hasComponent(PersonalStatsActivity.class.getName()));
    }

    @Test
    public void testRunScreenLaunch()
    {
        onView(withId(R.id.buttonRun)).perform(click());
        intended(hasComponent(RunTrackerActivity.class.getName()));
    }


    @Test
    public void testLoginScreenLaunch()
    {
        onView(withId(R.id.buttonSignOut)).perform(click());
        intended(hasComponent(LoginActivity.class.getName()));
    }
}
