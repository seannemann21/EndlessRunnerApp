package com.example.endlessrunnerapp.game;

/**
 * Created by Sean Nemann on 10/30/2017.
 */

public class UserData {
    public static int runningPoints;
    public static int highScore;

    public static void updateRunningPoints(int points)
    {
        runningPoints = points;
    }

    public static void updateHighScore(int score)
    {
        highScore = score;
    }
}

