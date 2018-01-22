package com.example.averma1212.popular_movies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

import Util.JsonStrongUtil;
import Util.NetworkUtils;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;


class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mThumbs;
    private final String image_base_url = "http://image.tmdb.org/t/p/w185";

    public ImageAdapter(Context c,String[] images) {
        mContext = c;
        mThumbs=images;
    }




    public int getCount() {
            return mThumbs.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(480,600));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }


        if(mThumbs!=null)
        Picasso.with(mContext).load(image_base_url+mThumbs[position]).into(imageView);
        return imageView;
    }

}