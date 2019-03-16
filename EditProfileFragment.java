package com.example.adarsh.lovelyface.Profile;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adarsh.lovelyface.R;
import com.example.adarsh.lovelyface.Extra.UniversalImageLoader;

public class EditProfileFragment extends Fragment {

    private static final String TAG="EditProfileFragment";
    private ImageView mProfilePhoto;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_editprofile,container,false);
        mProfilePhoto=(ImageView) view.findViewById(R.id.edit_profile_photo);

        setProfileImage();

        ImageView backArrow=(ImageView) view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }


    private void setProfileImage()
    {
        Log.d(TAG,"Setting profile image");
        String imgURL="www.gstatic.com/webp/gallery3/2.png";
        UniversalImageLoader.setImage(imgURL,mProfilePhoto,null,"https://");
    }
}
