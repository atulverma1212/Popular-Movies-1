package Util;

import android.content.Context;
import android.util.Log;

import com.example.averma1212.popular_movies.movieDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by HP on 07-11-2017.
 */

public class JsonStrongUtil {
    private static ArrayList<movieDetails> details;

    public static String[] getImgUrlFromJson(String JsonString) throws JSONException{
        final String OWM_MESSAGE_CODE = "status_code";
        String[] parsedData = null;


        JSONObject jsonObject = new JSONObject(JsonString);

        if (jsonObject.has(OWM_MESSAGE_CODE)) {
            int errorCode = jsonObject.getInt(OWM_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray result_arr = jsonObject.getJSONArray("results");
        parsedData = new String[result_arr.length()];
        details = new ArrayList<movieDetails>();
        for(int i=0;i<result_arr.length();i++)
        {
            JSONObject object = result_arr.getJSONObject(i);
            movieDetails json_object = new movieDetails(object);
            details.add(json_object);



            parsedData[i] = object.getString("poster_path");
        }
        Log.e("JSON11: ",details.get(0).getTitle());
        return parsedData;

    }

    public static ArrayList<movieDetails> getDetails() {
        return details;
    }
}
