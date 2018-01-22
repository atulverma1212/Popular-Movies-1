package Util;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by HP on 07-11-2017.
 */

public class NetworkUtils {
    private static String TAG = "Error";
    final static String apiBaseUrl = "http://api.themoviedb.org/3/movie";
    final static String key ="4790e766d5e07e62ad78b4c0c5793e72";
    final static String queryParameter = "api_key";




    public static URL buildUrl(String sortBy){
        Uri uri = Uri.parse(apiBaseUrl).buildUpon().appendPath(sortBy).appendQueryParameter(queryParameter,key).build();
        String builtUri = String.valueOf(uri);

        Log.d("TAG",builtUri.toString());

        URL url = null;
        try{
            url = new URL(builtUri);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}


