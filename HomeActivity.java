package com.example.adarsh.lovelyface.Home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adarsh.lovelyface.Extra.BottomNavigationViewHelper;
import com.example.adarsh.lovelyface.Login.LoginActivity;
import com.example.adarsh.lovelyface.Login.RegisterActivity;
import com.example.adarsh.lovelyface.R;
import com.example.adarsh.lovelyface.Extra.UniversalImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImageUrl=new ArrayList<>();

    //FireBase start Authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext;
    private static final int ACTIVITY_NUM=0;
    private static final String TAG="HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext=HomeActivity.this;
        Log.d(TAG, "OnCreate: Satrting");


        initProfileImage();

        setupFireBaseAuth();

        initImageLoader();
        setupBottomNavigationView();

    }

    private void initImageLoader()
    {
        UniversalImageLoader universalImageLoader=new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

        private void setupBottomNavigationView()
        {
            Log.d(TAG,"Setting up bottomnavigationview");
            BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNavigationView);
            BottomNavigationViewHelper.enableNavigation(HomeActivity.this,bottomNavigationView);

            Menu menu=bottomNavigationView.getMenu();
            MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
            menuItem.setChecked(true);
        }


    //---------------------------------------------FIREBASE--------------------------------------------------------------
    // FireBase Authentication Method

    private void checkCurrentUser(FirebaseUser user)
    {
        Log.d(TAG,"checking if user is loged in");
        if(user==null)
        {
            Intent intent=new Intent(mContext, LoginActivity.class);
            startActivity(intent);
        }

    }
    private void setupFireBaseAuth() {
        Log.d(TAG, "Setting up fireBaseAuth ");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                // Checking the user is logged in
                checkCurrentUser(user);

                if (user != null) {
                    Log.d(TAG, "OnAuthStateChanged:Sign_in " + user.getUid());
                } else {
                    Log.d(TAG, "OnAuthStateChanged:Sign_out ");
                }
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        checkCurrentUser(currentUser);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener!=null)
        {
            mAuth.removeAuthStateListener(mAuthListener);

        }
    }

    //------------------------------------------------------------------------------------------------

    private void initProfileImage()
    {
        Log.d("TAG","Waiting for Internet");

        mImageUrl.add("https://www.gstatic.com/webp/gallery3/2.png");
        mNames.add("Adarsh");
        mImageUrl.add("https://www.gstatic.com/webp/gallery3/2.png");
        mNames.add("Shivam");
        mImageUrl.add("https://www.gstatic.com/webp/gallery3/2.png");
        mNames.add("Monty");
        mImageUrl.add("https://www.gstatic.com/webp/gallery3/2.png");
        mNames.add("Raju");

        initRecyclerView();
    }

    private void initRecyclerView()
    {

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new RecyclerViewAdapter(mNames,mImageUrl,mContext);
        recyclerView.setAdapter(adapter);
    }
}
