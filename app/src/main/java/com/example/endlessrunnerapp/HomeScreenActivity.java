package com.example.endlessrunnerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.models.CurrentUserData;

public class HomeScreenActivity extends SingleFragmentActivity {
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected Fragment createFragment() {
        return new HomeScreenFragment();
    }
}
