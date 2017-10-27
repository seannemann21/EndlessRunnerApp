package com.example.endlessrunnerapp;


import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Login Page
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    protected final String TAG = getClass().getSimpleName();

    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    // Firebase authentication
    private FirebaseAuth mAuth;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mUsernameEditText = (EditText) v.findViewById(R.id.username_text);
        mPasswordEditText = (EditText) v.findViewById(R.id.password_text);

        Button loginButton = (Button) v.findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        Button newUserButton = (Button) v.findViewById(R.id.new_user_button);
        newUserButton.setOnClickListener(this);

        // Firebase stuff
        mAuth = FirebaseAuth.getInstance();

        return v;
    }

    private void checkLogin() {
        // First check to make sure email and password properties have data
        if(mUsernameEditText.getText().length() == 0) {
            return;
        }
        if(mPasswordEditText.getText().length() == 0) {
            return;
        }

        String email = mUsernameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // User exists and is validated, go to the home page
                            Intent intent = new Intent(getActivity().getApplicationContext(), HomeScreenActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                checkLogin();
                break;
            case R.id.new_user_button:
                break;
        }
    }

}
