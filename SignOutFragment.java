package com.example.adarsh.lovelyface.Profile;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adarsh.lovelyface.Login.LoginActivity;
import com.example.adarsh.lovelyface.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignOutFragment extends Fragment {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar mProgressBar;
    private Button mButton;

    private static final String TAG="SignOutFragment";
    private ImageView mProfilePhoto;

    public SignOutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signout,container,false);


        mProgressBar=(ProgressBar) view.findViewById(R.id.signOutProgressBar);
        mButton=(Button) view.findViewById(R.id.signOut_button);

        mProgressBar.setVisibility(View.GONE);
        setupFireBaseAuth();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);
                mAuth.signOut();
                getActivity().finish();
            }
        });

        return view;
    }
//----------------------------------------FIREBASE-----------------------------------------------
    private void setupFireBaseAuth() {
        Log.d(TAG, "Setting up fireBaseAuth ");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "OnAuthStateChanged:Sign_in " + user.getUid());
                } else {
                    Log.d(TAG, "OnAuthStateChanged:Sign_out ");
                    Log.d(TAG, "Navigetting back to login activity");

                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener!=null)
        {
            mAuth.removeAuthStateListener(mAuthListener);

        }
    }

}
