package com.example.adarsh.lovelyface.Profile;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.adarsh.lovelyface.Extra.BottomNavigationViewHelper;
import com.example.adarsh.lovelyface.R;

public class ProfileActivity extends AppCompatActivity {

    TextView textView;

    private ProgressBar progressBar;
    private static final int ACTIVITY_NUM=2;
    private static final String TAG="ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        progressBar=(ProgressBar)findViewById(R.id.profileProgressBar);
        progressBar.setVisibility(View.GONE);
        setupBottomNavigationView();
    }

    private void setupBottomNavigationView()
    {
        Log.d(TAG,"Setting up bottomnavigationview");
        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this,bottomNavigationView);

        Menu menu=bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
