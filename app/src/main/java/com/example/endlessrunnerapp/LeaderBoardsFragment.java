package com.example.endlessrunnerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Fragment that deals with information regarding the leader boards page
 */
public class LeaderBoardsFragment extends Fragment implements View.OnClickListener {

    public LeaderBoardsFragment() {
        // Required empty public constructor
    }


    // Remove possibly
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_leader_boards, container, false);

        // TODO: hook up buttons, etc.

        return v;
    }



    @Override
    public void onClick(View view) {

    }
}
