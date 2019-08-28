package de.yanniksimon.mypersonalhub.Weather;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import de.yanniksimon.mypersonalhub.MainActivity;
import de.yanniksimon.mypersonalhub.Variables.Variables;

public class Weather {

    private static String LOG = "LOG: WeatherClass";

    public Weather(){
        Log.i(LOG, "Construcotr");

    }

    public static void jsonWeather(){
        Log.i(LOG, "jsonWeather");
        String url = Variables.apiStuttgartUrl;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(LOG, "JsonRequest");
                try {
                    String temp = response.getJSONObject("main").getString("temp");
                    Log.i(LOG, "Temp: " + temp);
                    Log.i(LOG, "Test: " + response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MainActivity.requestQueue.add(jsonObjectRequest);


    }
}
