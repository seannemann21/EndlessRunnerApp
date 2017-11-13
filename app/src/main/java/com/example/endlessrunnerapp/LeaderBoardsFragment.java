package com.example.endlessrunnerapp;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.example.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.zip.Inflater;


/**
 * Fragment that deals with information regarding the leader boards page
 */
public class LeaderBoardsFragment extends Fragment implements View.OnClickListener {

    private class ScoreObj {
        public String name;
        public int score;
        public ScoreObj(String n, int s) {
            this.name = n;
            this.score = s;
        }
    }

    private class SortByScore implements Comparator<ScoreObj> {
        public int compare(ScoreObj a, ScoreObj b) {
            return b.score - a.score;
        }
    }

    private List<ScoreObj> bestRunsGame;
    private List<ScoreObj> bestRunsReal;


    public LeaderBoardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_leader_boards, container, false);

        bestRunsGame = new ArrayList<>();
        bestRunsReal = new ArrayList<>();

        getScores(v, inflater);


        return v;
    }


    @TargetApi(24)
    private void getScores(View v, final LayoutInflater inflater) {
        final View vCopy = v;

        // Get all of the users and their scores
        Database.getAllUserInformation(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    User u = userSnapshot.getValue(User.class);
                    if (u != null) {
                        String username = u.username;
                        int bestGameScore = 0;
                        if (u.bestGameRun != null)
                            bestGameScore = u.bestGameRun.score;
                        int longestRunScore = 0;
                        if (u.longestRun != null)
                            longestRunScore = u.longestRun.runningPointsEarned;

                        bestRunsGame.add(new ScoreObj(username, bestGameScore));
                        bestRunsReal.add(new ScoreObj(username, longestRunScore));

                    }
                }

                bestRunsGame.sort(new SortByScore());
                bestRunsReal.sort(new SortByScore());

                // Now populate the view with these scores / people
                LinearLayout GamesLayout =  vCopy.findViewById(R.id.gamesLayout);
                LinearLayout RunsLayout = vCopy.findViewById(R.id.runsLayout);
                // Populate
                for (int i = 0; i < bestRunsGame.size() && i < 5; i++) {
                    ScoreObj info = bestRunsGame.get(i);
                    View itemView = inflater.inflate(R.layout.leader_boards_single_item_layout, null);
                    TextView username = itemView.findViewById(R.id.leader_boards_item_username);
                    username.setText(info.name);
                    TextView score = itemView.findViewById(R.id.leader_boards_item_score);
                    score.setText(((Integer) info.score).toString());

                    GamesLayout.addView(itemView);

                }

                // Populate best run scores
                for (int i = 0; i < bestRunsReal.size() && i < 5; i++) {
                    ScoreObj info = bestRunsReal.get(i);
                    View itemView = inflater.inflate(R.layout.leader_boards_single_item_layout, null);
                    TextView username = itemView.findViewById(R.id.leader_boards_item_username);
                    username.setText(info.name);
                    TextView score = itemView.findViewById(R.id.leader_boards_item_score);
                    score.setText(((Integer) info.score).toString());

                    RunsLayout.addView(itemView);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onClick(View view) {

    }
}
