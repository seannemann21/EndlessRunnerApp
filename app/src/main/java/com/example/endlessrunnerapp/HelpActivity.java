package com.example.endlessrunnerapp;

import android.support.v4.app.Fragment;
import android.util.Log;

public class HelpActivity extends SingleFragmentActivity {
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected Fragment createFragment() {
        Log.d(TAG, "hehlp screen opened");
        return new HelpFragment();
    }
}
