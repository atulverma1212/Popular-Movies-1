package com.example.averma1212.popular_movies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

import Util.JsonStrongUtil;
import Util.NetworkUtils;

public class MainActivity extends AppCompatActivity{

    private  GridView gv;
    private  ImageAdapter imageAdapter;
    private String[] images;
    private String sortBy = "top_rated";
    private ArrayList <movieDetails> details = new ArrayList<movieDetails>();
    private TextView tv_error;
    private TextView tv_connecting;
    private ProgressBar progressBar;
    private Menu my_menu;
    private int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = (GridView) findViewById(R.id.grid_view);
        tv_error = (TextView) findViewById(R.id.tv_error_message_display);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        tv_connecting = (TextView) findViewById(R.id.connecting);
        new FetchUrl().execute(sortBy);





     //   imageAdapter = new ImageAdapter(this);


        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent startChild = new Intent(MainActivity.this,childActivity.class);
                startChild.putExtra("movie_class",position);
                startActivity(startChild);


             //   Toast.makeText(MainActivity.this,"#"+position,Toast.LENGTH_LONG).show();
            }
        };

        gv.setOnItemClickListener(listener);
    }


    public void showDataView(){
        tv_error.setVisibility(View.INVISIBLE);
        gv.setVisibility(View.VISIBLE);
    }

    public void showErrormsg(){
        tv_error.setVisibility(View.VISIBLE);
        gv.invalidateViews();
        gv.setVisibility(View.INVISIBLE);
        flag=1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main,menu);
        my_menu = menu;
        if(flag==1)
            my_menu.clear();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int menuthatwasselectedID = item.getItemId();
            if (menuthatwasselectedID == R.id.sort_by_rating) {
                Context context = MainActivity.this;
                //  String Message = "Rating Clicked";
              //  imageAdapter.notifyDataSetChanged();
                gv.invalidateViews();
                sortBy = "top_rated";
                // Toast.makeText(context,sortBy.toString(),Toast.LENGTH_LONG).show();
            }
            if (menuthatwasselectedID == R.id.sort_by_popularity) {
                Context context = MainActivity.this;
                //  String Message = "Popularity Clicked";
        //        imageAdapter.notifyDataSetChanged();
                gv.invalidateViews();
                sortBy = "popular";
                //    Toast.makeText(context,sortBy.toString(),Toast.LENGTH_LONG).show();
            }
            new FetchUrl().execute(sortBy);
            //    gv.setAdapter(imageAdapter);

        return super.onOptionsItemSelected(item);
    }

    public class FetchUrl extends AsyncTask<String, Void, String[]> {
        final String TAG = "Asycnh";

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            tv_connecting.setVisibility(View.VISIBLE);
            gv.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String[] doInBackground(String... sortBys) {
            if(sortBys.length == 0)
                return null;
            String Url = sortBys[0];
          //  Log.e("sortBy: ",Url);
            URL imageUrl = NetworkUtils.buildUrl(Url);
            String[] image_array=null;
            Log.e("sortBy: ",imageUrl.toString());
            try{
                String jsonString = NetworkUtils.getResponseFromHttpUrl(imageUrl);
                image_array = JsonStrongUtil.getImgUrlFromJson(jsonString);
                details = JsonStrongUtil.getDetails();
                movieDetails.set_movies(details);

                Log.e("image1: ",image_array[0]);

            }catch (Exception e){
                e.printStackTrace();
            }

            return image_array;
        }



        @Override
        protected void onPostExecute(String[] image_array) {
            progressBar.setVisibility(View.INVISIBLE);
            tv_connecting.setVisibility(View.INVISIBLE);
            gv.setVisibility(View.VISIBLE);
            if(image_array!=null)
            {
                showDataView();
                imageAdapter = new ImageAdapter(MainActivity.this,image_array);
                gv.setAdapter(imageAdapter);

            }
            else{
                showErrormsg();
                if(my_menu!=null)
                    my_menu.clear();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
