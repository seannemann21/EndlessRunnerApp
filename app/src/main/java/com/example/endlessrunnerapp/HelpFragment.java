package com.example.endlessrunnerapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * HelpActivity Page
 */
public class HelpFragment extends Fragment implements View.OnClickListener {


    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_help, container, false);

        // TODO: hook up buttons, etc.

        return v;
    }

    @Override
    public void onClick(View view) {

    }

}
