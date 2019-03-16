package com.example.adarsh.lovelyface.Extra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.util.Log;
import android.view.MenuItem;

import com.example.adarsh.lovelyface.Home.HomeActivity;
import com.example.adarsh.lovelyface.Likes.LikesActivity;
import com.example.adarsh.lovelyface.Profile.ProfileActivity;
import com.example.adarsh.lovelyface.R;
import com.example.adarsh.lovelyface.Search.SearchActivity;
import com.example.adarsh.lovelyface.Setting.SettingActivity;

import java.lang.reflect.Field;

public class BottomNavigationViewHelper {

    private static final String TAG ="BottomNavigationViewHelper";

            public static void enableNavigation(final Context context,BottomNavigationView view)
            {
                view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                        switch (menuItem.getItemId())
                        {
                            case R.id.ic_home:
                                Intent intent1=new Intent(context,HomeActivity.class);
                                context.startActivity(intent1);
                                break;
                            case R.id.ic_search:
                                Intent intent2=new Intent(context,SearchActivity.class);
                                context.startActivity(intent2);
                                break;
                            case R.id.ic_account:
                                Intent intent3=new Intent(context,ProfileActivity.class);
                                context.startActivity(intent3);
                                break;
                            case R.id.ic_like:
                                Intent intent4=new Intent(context,LikesActivity.class);
                                context.startActivity(intent4);
                                break;
                            case R.id.ic_setting:
                                Intent intent5=new Intent(context, SettingActivity.class);
                                context.startActivity(intent5);
                                break;
                        }
                        return true;

                    }
                });
            }
}
