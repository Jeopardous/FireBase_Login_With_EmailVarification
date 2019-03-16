package com.example.adarsh.lovelyface.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adarsh.lovelyface.Home.HomeActivity;
import com.example.adarsh.lovelyface.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private Context mContext;
    private ProgressBar mProgressBar;
    private EditText mEmail,mPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG="LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        setupFireBaseAuth();
        init();

        mProgressBar=(ProgressBar)findViewById(R.id.loginrequest_progressbar);
        mEmail=(EditText)findViewById(R.id.input_email);
        mPassword=(EditText)findViewById(R.id.input_password);
        mContext=LoginActivity.this;

        mProgressBar.setVisibility(View.GONE);

    }
    private  boolean  isStringNull(String string) {
        if (string.equals("")) {
            return true;
        }
        else {
            return false;
        }
    }

    //---------------------------------------------FIREBASE--------------------------------------------------------------

    private void init()
    {
        Button buttonLogin=(Button)findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString();
                Log.d(TAG,"Attampting Login ");
                if(isStringNull(email) && isStringNull(password))
                {
                    Toast.makeText(mContext,"You must fill out all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    FirebaseUser user=mAuth.getCurrentUser();

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Authentication Failed.",
                                                Toast.LENGTH_SHORT).show();

                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:Failed");
                                        mProgressBar.setVisibility(View.GONE);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        try {
                                            if(user.isEmailVerified())
                                            {
                                                Intent intent=new Intent(mContext,HomeActivity.class);
                                                startActivity(intent);
                                            }
                                            else
                                            {
                                                Toast.makeText(mContext,"Email is not verified check your Inbox",Toast.LENGTH_SHORT).show();
                                                mProgressBar.setVisibility(View.GONE);
                                                mAuth.signOut();
                                            }

                                        }catch (NullPointerException e)
                                        {
                                            Log.e(TAG,"onComplete NullPointerException "+e.getMessage());
                                        }
                                    }

                                    // ...
                                }
                            });
                }
            }
        });
        /*
        If The User is loged in then navigate to HomeActivity
         */
        if(mAuth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(mContext, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        /*
               Navigate to RegisterActivity
         */
        TextView textView=(TextView)findViewById(R.id.text_signup_link);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    // FireBase Authentication Method

    private void setupFireBaseAuth()
    {
        Log.d(TAG,"Setting up fireBaseAuth ");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user=mAuth.getCurrentUser();
                if(user!=null){
                    Log.d(TAG,"OnAuthStateChanged:Sign_in "+user.getUid());
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

    }
    public void onStop() {
        super.onStop();

    }
}
