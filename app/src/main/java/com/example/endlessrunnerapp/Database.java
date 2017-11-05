package com.example.endlessrunnerapp;

import com.example.models.CurrentUserData;
import com.example.models.RealRun;
import com.example.models.GameRun;
import com.example.models.User;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Database {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();



    public static void getUserInformation(String userId, ValueEventListener el) {
        DatabaseReference ref = database.getReference("users/" + userId);
        ref.addValueEventListener(el);
    }

    public static void setUserInformation(String userId, User user) {
        DatabaseReference ref = database.getReference("users/" + userId);
        ref.setValue(user);
    }

    public static void setUserRealRunInformation(String userId, RealRun realRun) {
        DatabaseReference ref = database.getReference("users/" + userId + "/longestRun/");
        ref.setValue(realRun);
    }

    public static void setUserGameRunInformation(String userId, GameRun gameRun) {
        DatabaseReference ref = database.getReference("users/" + userId + "/bestGameRun/");
        ref.setValue(gameRun);
    }

    public static void getAllUserInformation(ValueEventListener el) {
        DatabaseReference ref = database.getReference("users/");
        ref.addValueEventListener(el);
    }



}
