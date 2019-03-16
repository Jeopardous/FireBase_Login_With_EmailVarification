package com.example.adarsh.lovelyface.Home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adarsh.lovelyface.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImageUrl=new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mNames, ArrayList<String> mImageUrl, Context mContext) {
        this.mNames = mNames;
        this.mImageUrl = mImageUrl;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.layout_center_cardview,viewGroup,false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrl.get(i))
                .into(viewHolder.mainimage);
        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrl.get(i))
                .into(viewHolder.image1);

        viewHolder.nametext.setText(mNames.get(i));


    }

    @Override
    public int getItemCount() {
        return mImageUrl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView getImage1() {
            return image1;
        }

        public void setImage1(ImageView image1) {
            this.image1 = image1;
        }

        public ImageView getMainimage() {
            return mainimage;
        }

        public void setMainimage(ImageView mainimage) {
            this.mainimage = mainimage;
        }

        public TextView getNametext() {
            return nametext;
        }

        public void setNametext(TextView nametext) {
            this.nametext = nametext;
        }

        ImageView image1,mainimage;
        TextView nametext;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image1=itemView.findViewById(R.id.cardviewImage1);
            mainimage=itemView.findViewById(R.id.maincardview_image);
            nametext=itemView.findViewById(R.id.name_text);

        }
    }
}
