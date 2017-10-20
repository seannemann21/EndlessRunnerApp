package com.example.endlessrunnerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToRun(View view) {
        Intent intent = new Intent(this, RunTracker.class);
        startActivity(intent);
    }

    public void goToLeaderboards(View view) {
        Intent intent = new Intent(this, LeaderBoardsActivity.class);
        startActivity(intent);
    }

    public void goToPersonalStats(View view) {
        Intent intent = new Intent(this, PersonalStats.class);
        startActivity(intent);
    }

    public void goToHelp(View view) {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
}
