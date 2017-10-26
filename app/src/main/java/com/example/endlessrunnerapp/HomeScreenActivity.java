package com.example.endlessrunnerapp;

import android.support.v4.app.Fragment;

public class HomeScreenActivity extends SingleFragmentActivity {
    protected final String TAG = getClass().getSimpleName();


    @Override
    protected Fragment createFragment() {
        return new HomeScreenFragment();
    }
}
