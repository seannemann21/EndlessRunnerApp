package com.example.endlessrunnerapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.models.CurrentUserData;
import com.example.models.GameRun;

import java.util.Calendar;
import java.util.UUID;

public class GameLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_launcher);

        Intent intent = new Intent(this, UnityPlayerActivity.class);
        intent.putExtra("username", CurrentUserData.username);
        intent.putExtra("runningPoints", CurrentUserData.runningPoints);
        intent.putExtra("mostPoints", CurrentUserData.bestGameRun.score);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (UnityPlayerActivity.class) : {
                if (resultCode == Activity.RESULT_OK) {
                    int updatedRunningPoints = data.getIntExtra("runningPoints", -1);
                    if(updatedRunningPoints > -1)
                    {
                        CurrentUserData.runningPoints = updatedRunningPoints;
                    }
                    if(CurrentUserData.bestGameRun.score < data.getIntExtra("newHighScore", 0);)
                    {
                        GameRun newHighGameRun = new GameRun();
                        newHighGameRun.date = Calendar.getInstance().getTime();
                        newHighGameRun.id = UUID.randomUUID().toString();
                        newHighGameRun.score = data.getIntExtra("newHighScore", 0);
                        newHighGameRun.userEmail = CurrentUserData.email;
                        CurrentUserData.bestGameRun = newHighGameRun;
                    }
                    // update new user data
                }
                break;
            }
        }
    }
}
