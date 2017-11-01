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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.models.CurrentUserData;
import com.example.endlessrunnerapp.Database;

import com.example.models.GameRun;
import com.example.models.RealRun;
import com.example.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


/**
 * Login Page
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    protected final String TAG = getClass().getSimpleName();

    private View thisView;

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mUsernameEditText;

    private LinearLayout mNewUserInfo;

    private boolean mCreatingNewUser;

    // Firebase authentication
    private FirebaseAuth mAuth;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mCreatingNewUser = false;
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mEmailEditText = (EditText) v.findViewById(R.id.email_text);
        mPasswordEditText = (EditText) v.findViewById(R.id.password_text);
        mUsernameEditText = (EditText) v.findViewById(R.id.username_text);


        Button loginButton = (Button) v.findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        Button newUserButton = (Button) v.findViewById(R.id.new_user_button);
        newUserButton.setOnClickListener(this);

        mNewUserInfo = (LinearLayout) v.findViewById(R.id.new_user_info);

        // Firebase stuff
        mAuth = FirebaseAuth.getInstance();

        return v;
    }

    private void checkLogin() {
        // First check to make sure email and password properties have data
        if(mEmailEditText.getText().length() == 0) {
            return;
        }
        if(mPasswordEditText.getText().length() == 0) {
            return;
        }

        String email = mEmailEditText.getText().toString();
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
                            goToHome(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    private void createUserAndLogin() {
        // First check to make all properties have data
        if(mEmailEditText.getText().length() == 0 || mPasswordEditText.getText().length() == 0 || mUsernameEditText.getText().length() == 0) {
            return;
        }

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String username = mUsernameEditText.getText().toString();

        // Lets create a new user
        // Firebase serializes that classes too! We can go ahead and set longestRun and bestGame
        final User newUser = new User();
        newUser.email = email;
        newUser.username = username;
        newUser.bestGameRun = new GameRun();
        newUser.longestRun = new RealRun();
        newUser.runningPoints  = 10;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Database.setUserInformation(user.getUid(), newUser);
                            goToHome(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void goToHome(FirebaseUser user) {
        // Need to set the users data

        Database.getUserInformation(user.getUid(), new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                CurrentUserData.username = user.username;
                CurrentUserData.email = user.email;
                CurrentUserData.bestGameRun = user.bestGameRun != null ? user.bestGameRun : new GameRun();
                CurrentUserData.longestRun = user.longestRun != null ? user.longestRun : new RealRun();
                CurrentUserData.runningPoints = user.runningPoints;


                Intent intent = new Intent(getActivity().getApplicationContext(), HomeScreenActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void createNewUser() {
        // Set the visibility of the new_user_info prompt or remove it
        if (!mCreatingNewUser) {
            mCreatingNewUser = true;
            mNewUserInfo.setVisibility(View.VISIBLE);

            Button loginBtn = this.getView().findViewById(R.id.login_button);
            loginBtn.setText("Create");
        } else {
            // Remove the added UI components
            mCreatingNewUser = false;
            mNewUserInfo.setVisibility(View.GONE);
            Button loginBtn = this.getView().findViewById(R.id.login_button);
            loginBtn.setText("Login");
        }



    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            goToHome(currentUser);
//        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                if (mCreatingNewUser){
                    createUserAndLogin();
                } else {
                    checkLogin();
                }
                break;
            case R.id.new_user_button:
                createNewUser();
                break;
        }
    }

}
