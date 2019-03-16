package com.example.adarsh.lovelyface.Extra;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.adarsh.lovelyface.Login.RegisterActivity;
import com.example.adarsh.lovelyface.Models.User;
import com.example.adarsh.lovelyface.Models.UserAccountSettings;
import com.example.adarsh.lovelyface.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethods {
    private  static final String TAG="FirebaseMethods";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Context mContext;
    private String userId;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public FirebaseMethods(Context context)
    {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        mAuth= FirebaseAuth.getInstance();
        mContext=context;

        if(mAuth.getCurrentUser()!=null)
        {
            userId=mAuth.getCurrentUser().getUid();
        }
    }
    public boolean checkIfUserNameExist(String username, DataSnapshot dataSnapshot)
    {
        User user=new User();

        for(DataSnapshot ds: dataSnapshot.child(userId).getChildren())
        {
            user.setUsername(ds.getValue(User.class).getUsername());
            if(StringManuplate.expendUsername(user.getUsername()).equals(username))
            {
                return  true;
            }
        }
        return false;
    }

    public void registerNewEmail(final String email,final  String username,final String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                            Toast.makeText(mContext,"Authentication failed.",Toast.LENGTH_SHORT).show();

                        } else if(task.isSuccessful()){
                            // If sign in fails, display a message to the user.
                            sendVerificationEmail();
                            userId=mAuth.getCurrentUser().getUid();
                            Log.d(TAG, "complete: Authenticate changed");
                        }

                        // ...
                    }
                });

    }

    public void sendVerificationEmail()
    {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {

                            }else
                            {
                                Toast.makeText(mContext,"Couldn't send verification email",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void addNewUser(String email,String username,String profilephoto)
    {
        User user=new User(userId, (long) 1,email,StringManuplate.condenseUsername(username));

        databaseReference.child(String.valueOf(R.string.database_users))
                .child(userId)
                .setValue(user);

        UserAccountSettings userAccountSettings=new UserAccountSettings(username,(long)0,(long)0, profilephoto);
        databaseReference.child(String.valueOf(R.string.database_user_account_settings))
                .child(userId)
                .setValue(userAccountSettings);
    }
}
