package com.example.endlessrunnerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.models.CurrentUserData;

import org.w3c.dom.Text;
import android.widget.Toast;

import com.example.models.CurrentUserData;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Fragment for the home page where the user can navigate to all pages
 */
public class HomeScreenFragment extends Fragment implements View.OnClickListener {


    public HomeScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_screen, container, false);

        // Update the home screen greeting
        TextView greeting = (TextView) v.findViewById(R.id.homeScreenGreeting);
        greeting.append(" " + CurrentUserData.username + "?");

        // Hook up the buttons
        Button btnRun = (Button) v.findViewById(R.id.buttonRun);
        btnRun.setOnClickListener(this);
        Button btnGame = (Button) v.findViewById(R.id.buttonPlay);
        btnGame.setOnClickListener(this);
        Button btnLdrBds = (Button) v.findViewById(R.id.buttonLeaderBoards);
        btnLdrBds.setOnClickListener(this);
        Button btnStats = (Button) v.findViewById(R.id.buttonStats);
        btnStats.setOnClickListener(this);
        Button btnHelp = (Button) v.findViewById(R.id.buttonHelp);
        btnHelp.setOnClickListener(this);
        Button btnSignOut = (Button) v.findViewById(R.id.buttonSignOut);
        btnSignOut.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonRun:
                goToRun();
                break;
            case R.id.buttonLeaderBoards:
                goToLeaderboards();
                break;
            case R.id.buttonStats:
                goToPersonalStats();
                break;
            case R.id.buttonHelp:
                goToHelp();
                break;
            case R.id.buttonPlay:
                goToGame();
                break;
            case R.id.buttonSignOut:
                signOut();
                break;
        }
    }

    public void goToRun() {
        Intent intent = new Intent(getActivity().getApplicationContext(), RunTrackerActivity.class);
        startActivity(intent);
    }

    public void goToLeaderboards() {
        Intent intent = new Intent(getActivity().getApplicationContext(), LeaderBoardsActivity.class);
        startActivity(intent);
    }

    public void goToPersonalStats() {
        Intent intent = new Intent(getActivity().getApplicationContext(), PersonalStatsActivity.class);
        startActivity(intent);
    }

    public void goToHelp() {
        Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }

    public void goToGame() {
        if(CurrentUserData.runningPoints > 0)
        {
            Intent intent = new Intent(getActivity().getApplicationContext(), GameLauncher.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getActivity(), "Out of Running Points!",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void signOut() {
        // sign out the user and return to the login page
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }


}
