package com.example.averma1212.popular_movies;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class childActivity extends AppCompatActivity {

    private final String TAG = "In childActivity";
    private TextView desc;
    private TextView avg_votes;
    private TextView lang;
    private TextView date;
    private ImageView img;
    private TextView title;
    private movieDetails movie;
    private final String image_base_url = "http://image.tmdb.org/t/p/w342";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);


        desc = (TextView) findViewById(R.id.child_desc);
        avg_votes = (TextView) findViewById(R.id.child_votes);
        lang = (TextView) findViewById(R.id.child_lang);
        date = (TextView) findViewById(R.id.child_date);
        title = (TextView) findViewById(R.id.child_title);
        img = (ImageView) findViewById(R.id.child_img);

        Intent StartingIntent = getIntent();
        int position;

        try{
            position = StartingIntent.getIntExtra("movie_class",0);
            movie = movieDetails.get_movie(position);
        }catch (Exception e){
            Log.e(TAG,"error receiving position");
        }

        desc.setText(movie.getDesc());
        avg_votes.setText(movie.getVotes());
        date.setText(movie.getDate());
        title.setText(movie.getTitle());
       // Log.e(TAG,movie.getPoster_path());
        Picasso.with(childActivity.this).load(movie.getPosterPath()).into(img);

        switch (movie.getLang()){
            case "hi":
                lang.setText("Hindi");
                break;

            case "ja":
                lang.setText("Japanese");
                break;

            default:
                lang.setText("English");
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
