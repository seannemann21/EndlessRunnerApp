package com.example.endlessrunnerapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginTests {

    @Rule
    public IntentsTestRule<LoginActivity> mActivityRule = new IntentsTestRule<>(
            LoginActivity.class);

    @Test
    public void testNewUserDisplaysContent()
    {
        onView(withId(R.id.new_user_button)).perform(click());
        onView(withId(R.id.username_text)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginFail()
    {
        // empty username and password
        onView(withId(R.id.login_button)).perform(click());
        onView(withId(R.id.fragment_login)).check(matches(isDisplayed()));
    }




}
