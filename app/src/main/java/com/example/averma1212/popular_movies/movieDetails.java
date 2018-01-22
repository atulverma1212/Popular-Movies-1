package com.example.averma1212.popular_movies;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by HP on 08-11-2017.
 */

public class movieDetails {

    private static ArrayList <movieDetails> movies = new ArrayList<movieDetails>();

    private final String DATE = "release_date";
    private final String TITLE = "original_title";
    private final String VOTE = "vote_average";
    private final String PATH = "poster_path";
    private  final String LANG = "original_language";
    private  final String DESC = "overview";
    private final String image_base_url = "http://image.tmdb.org/t/p/w185";


    private String date;
    private String votes;
    private String title;
    private String desc;
    private String lang;
    private String posterPath;

    public movieDetails() {
    }

    public movieDetails(JSONObject json) {
           try{
                 date = json.getString(DATE);
                votes = json.getString(VOTE);
                desc = json.getString(DESC);
                lang = json.getString(LANG);
                posterPath = image_base_url+json.getString(PATH);
                 title = json.getString(TITLE);


             } catch (JSONException e){
                Log.e("Const","JSon error");
                }
    }

    public String getDate() {
        return date;
    }

    public String getVotes() {
        return votes;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getLang() {
        return lang;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public static void set_movies(ArrayList<movieDetails> e){
        movies = new ArrayList<movieDetails>();
        movies = e;
    }

    public static movieDetails get_movie(int position){
        return movies.get(position);
    }
}
