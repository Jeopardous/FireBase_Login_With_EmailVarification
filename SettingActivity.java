package com.example.adarsh.lovelyface.Setting;

import android.content.Context;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.adarsh.lovelyface.Extra.BottomNavigationViewHelper;
import com.example.adarsh.lovelyface.Profile.EditProfileFragment;
import com.example.adarsh.lovelyface.Profile.SignOutFragment;
import com.example.adarsh.lovelyface.R;
import com.example.adarsh.lovelyface.Extra.SectionStatePagerAdapter;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {


    private SectionStatePagerAdapter pagerAdapter;
    private ViewPager mViewpager;
    private RelativeLayout mRelativeLayout;
    private Context mContext;
    private static final int ACTIVITY_NUM=4;
    private static final String TAG="SettingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Log.d(TAG, "OnCreate: Satrting");

        mViewpager=(ViewPager)findViewById(R.id.centerViewPager);
        mRelativeLayout=(RelativeLayout)findViewById(R.id.setting_relLayout0);

        mContext=SettingActivity.this;
        setupBottomNavigationView();
        setupSettingList();
        setupFragments();
        // setting up backarrow to profile Activity
        ImageView backArrow=(ImageView)findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Setting up fragments SignUp and EditProfile
    private void setupFragments()
    {
        pagerAdapter=new SectionStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(),getString(R.string.Edit_Profile));
        pagerAdapter.addFragment(new SignOutFragment(),getString(R.string.Sign_Out));
    }

    private void setViewpager(int fragmentNumber)
    {
        mRelativeLayout.setVisibility(View.GONE);
        mViewpager.setAdapter(pagerAdapter);
        mViewpager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingList()
    {
        ListView listView=(ListView)findViewById(R.id.account_setting_list);

        ArrayList<String> options=new ArrayList<>();
        options.add(getString(R.string.Edit_Profile));
        options.add(getString(R.string.Sign_Out));

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    setViewpager(position);
            }
        });
    }

    private void setupBottomNavigationView()
    {
        Log.d(TAG,"Setting up bottomnavigationview");
        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(SettingActivity.this,bottomNavigationView);

        Menu menu=bottomNavigationView.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
