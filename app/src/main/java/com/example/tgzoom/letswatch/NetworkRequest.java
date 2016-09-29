package com.example.tgzoom.letswatch;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by tgzoom on 9/29/16.
 */
public class NetworkRequest {

    private HttpURLConnection httpURLConnection = null;
    private BufferedReader bufferedReader       = null;
    private String jsonString                   = null;

    private static String API_KEY              = "api_key";
    private static String KEY                  = "c0dc85e33208e4b08052c3ee216b98f0";

    public String buildUrl(String api_path){
        Uri.Builder builder = new Uri.Builder();
        builder
            .scheme("http")
            .authority("api.themoviedb.org")
            .appendPath("3")
            .appendPath("movie")
            .appendPath(api_path)
            .appendQueryParameter(API_KEY,KEY);

        return builder.build().toString();
    }

    private BufferedReader generateBufferedReader(InputStream inputStream){
        if (inputStream == null) {
            return null;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
        return buffer;
    }

    private HttpURLConnection openConection(URL url, String method){
        try{
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod(method);
            httpConnection.connect();
            return httpConnection;
        }catch (Exception e){
            Log.i("MovieDBException",e.toString());
        }
        return null;
    }

    private String readBufferedReader(BufferedReader buffReader){
        StringBuffer buffer = new StringBuffer();
        String line;
        try{
            while ((line = buffReader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            return buffer.toString();
        }catch (Exception e){

        }
        return null;
    }

    public String getJsonString(String api_path){
        try{

            String urlString = buildUrl(api_path);
            URL url = new URL(urlString);
            httpURLConnection = openConection(url,"GET");
            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader = generateBufferedReader(inputStream);
            if(bufferedReader == null){
                return null;
            }
            jsonString = readBufferedReader(bufferedReader);
            return jsonString;
        }catch (Exception e){
            return e.toString();
        }
    }
}
