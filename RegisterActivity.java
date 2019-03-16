package com.example.adarsh.lovelyface.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adarsh.lovelyface.Extra.FirebaseMethods;
import com.example.adarsh.lovelyface.Home.HomeActivity;
import com.example.adarsh.lovelyface.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Context mContext;
    EditText mEmail,mPassword,mUsername;
    String email,password,username;
    Button registerButton;
    ProgressBar mProgressBar;
    private FirebaseMethods firebaseMethods;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String append;

    private static final String TAG="RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseMethods= new FirebaseMethods(mContext);

        initWidgets();
        init();
        setupFireBaseAuth();
    }

    private void initWidgets()
    {
        mProgressBar= findViewById(R.id.register_request_progressbar);
        mEmail= findViewById(R.id.input_email);
        mPassword= findViewById(R.id.input_password);
        mUsername= findViewById(R.id.input_username);
        mContext=RegisterActivity.this;
        registerButton= findViewById(R.id.register_button);

        mProgressBar.setVisibility(View.GONE);
    }

    //---------------------------------------------FIREBASE--------------------------------------------------------------

    private void init() {

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString();
                String username=mUsername.getText().toString();

                if(checkUserInputs(email,password,username))
                {
                    mProgressBar.setVisibility(View.VISIBLE);
                    firebaseMethods.registerNewEmail(email,password,username);

                }
            }
        });
    }
    private boolean checkUserInputs(String email,String password,String username)
    {
        if(email.equals("")||password.equals("")||username.equals(""))
        {
            Toast.makeText(mContext,"All fields must be filled out",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // FireBase Authentication Method

    private void setupFireBaseAuth()
    {
        Log.d(TAG,"Setting up fireBaseAuth ");
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        mAuthListener=new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user=mAuth.getCurrentUser();
                if(user!=null){

                    Log.d(TAG,"OnAuthStateChanged:Sign_in "+user.getUid());
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // MakeSure That username already not in use

                            if (firebaseMethods.checkIfUserNameExist(username,dataSnapshot))
                            {
                                append=databaseReference.push().getKey().substring(3,8);
                            }
                            username=username+append;

                            //add new user to database

                            Log.d(TAG,"New Usere Registeration Process: Running ");
                            firebaseMethods.addNewUser(email,username," ");
                            Toast.makeText(mContext,"SignUp Successfully. Sending Verivication Email",
                                    Toast.LENGTH_SHORT).show();

                            mAuth.signOut();

                            //add user_account_setting to database
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    finish();
                }
                else{
                    Log.d(TAG,"OnAuthStateChanged:Sign_out ");


                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }
    public void onStop() {
        super.onStop();
        if(mAuthListener!=null)
        {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }
}
