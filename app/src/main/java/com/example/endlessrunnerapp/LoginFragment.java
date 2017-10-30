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
                            Intent intent = new Intent(getActivity().getApplicationContext(), HomeScreenActivity.class);
                            startActivity(intent);
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

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(getActivity().getApplicationContext(), HomeScreenActivity.class);
                            startActivity(intent);
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
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
