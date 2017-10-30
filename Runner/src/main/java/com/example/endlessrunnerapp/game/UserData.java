package com.example.endlessrunnerapp.game;

/**
 * Created by Sean Nemann on 10/30/2017.
 */

public class UserData {
    public static int runningPoints = 5;
    public static int highScore = 0;

    public static void updateRunningPoints(int rPoints)
    {
        runningPoints = rPoints;
    }

    public static void updateHighScore(int score)
    {
        highScore = score;
    }
}
