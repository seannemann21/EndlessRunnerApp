package com.example.endlessrunnerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeScreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
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

        // Hook up the buttons
        Button btnRun = (Button) v.findViewById(R.id.buttonRun);
        btnRun.setOnClickListener(this);
        Button btnLdrBds = (Button) v.findViewById(R.id.buttonLeaderBoards);
        btnLdrBds.setOnClickListener(this);
        Button btnStats = (Button) v.findViewById(R.id.buttonStats);
        btnStats.setOnClickListener(this);
        Button btnHelp = (Button) v.findViewById(R.id.buttonHelp);
        btnHelp.setOnClickListener(this);

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
        Intent intent = new Intent(getActivity().getApplicationContext(), PersonalStats.class);
        startActivity(intent);
    }

    public void goToHelp() {
        Intent intent = new Intent(getActivity().getApplicationContext(), HelpActivity.class);
        startActivity(intent);
    }
}
