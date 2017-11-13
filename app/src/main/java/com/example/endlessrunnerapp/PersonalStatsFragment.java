package com.example.endlessrunnerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.models.CurrentUserData;


/**
 * Fragment for viewing personal and world stats
 */
public class PersonalStatsFragment extends Fragment {


    public PersonalStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_personal_stats, container, false);

        TextView username = (TextView) v.findViewById(R.id.userName);
        TextView email = (TextView) v.findViewById(R.id.email);
        TextView currentRunningPoints = (TextView) v.findViewById(R.id.textView4);
        TextView pointsEarned = (TextView) v.findViewById(R.id.textView7);
        TextView datePlayed = (TextView) v.findViewById(R.id.textView9);
        TextView distanceRan = (TextView) v.findViewById(R.id.distanceRan);
        TextView runningPointsEarned = (TextView) v.findViewById(R.id.runningPointsEarned);
        TextView dateRan = (TextView) v.findViewById(R.id.dateRan);

        if(CurrentUserData.username != null)
            username.setText("Username: " + CurrentUserData.username);
        if(CurrentUserData.email != null)
            email.setText("Email: " + CurrentUserData.email);
        currentRunningPoints.setText("Current Running Points: " + CurrentUserData.runningPoints);
        if(CurrentUserData.bestGameRun != null)
        {
            pointsEarned.setText("Points Earned: " + CurrentUserData.bestGameRun.score);
            if(CurrentUserData.bestGameRun.date != null)
                datePlayed.setText("Date Played: " + CurrentUserData.bestGameRun.date);
        }

        if(CurrentUserData.longestRun != null)
        {
            distanceRan.setText("Distance Ran: " + CurrentUserData.longestRun.distanceRan + " m");
            runningPointsEarned.setText("Running Points Earned: " + CurrentUserData.longestRun.runningPointsEarned);
            if(CurrentUserData.longestRun.date != null)
                dateRan.setText("Date Ran: " + CurrentUserData.longestRun.date);
        }

        return v;

    }


}
