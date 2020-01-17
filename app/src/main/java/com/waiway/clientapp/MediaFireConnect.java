package com.waiway.clientapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MediaFireConnect {

   // public static String filelink = "";
    public static String getVideoUrl(String filelink) throws IOException, JSONException {
        String query = URLEncoder.encode(filelink);
        String myurl = "https://channelbox.apkmm.net/api/v1/mediafire?url="+query;
        URL url = new URL(myurl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("Accept","application/json;charset=utf-8");
        urlConnection.setRequestProperty("Content-type","application/json");
        urlConnection.setDoInput(true);
        urlConnection.setRequestMethod("GET");
        InputStream inputStream = urlConnection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) !=null)
        {
            stringBuffer.append(line);
        }

        String result = stringBuffer.toString();
        JSONObject object = new JSONObject(result);
        JSONObject data = object.getJSONObject("data");
        String link = data.getString("file");
        return  link;

    }

}
