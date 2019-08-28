package de.yanniksimon.mypersonalhub.Weather;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Watchable;

import de.yanniksimon.mypersonalhub.MainActivity;
import de.yanniksimon.mypersonalhub.Variables.Variables;

public class Weather {

    private static String LOG = "LOG: WeatherClass";

    private int temp, temp_min, temp_max;
    long sunrise, sunset;
    private String condition,conditionAbb,conditionIconUrl, location;

    static Weather weather;




    public Weather(String location, String condition, String conditionIconCode, String conditionIconUrl, int temp, int temp_min, int temp_max, long sunrise, long sunset){
        Log.i(LOG, "Construcotr");

        this.location = location;
        this.condition = condition;
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.sunrise = sunrise;
        this.sunset = sunset;

    }

    //Gets the Data
    public static Weather parseJsonWeatherData(){
        Log.i(LOG, "jsonWeather");
        String url = Variables.apiStuttgartUrl;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(LOG, "JsonRequest");
                try {
                    String location = response.getString("name");
                    String condition = response.getJSONArray("weather").getJSONObject(0).getString("main");
                    String conditionIconCode = response.getJSONArray("weather").getJSONObject(0).getString("icon");
                    int temp = response.getJSONObject("main").getInt("temp");
                    int temp_min = response.getJSONObject("main").getInt("temp_min");
                    int temp_max = response.getJSONObject("main").getInt("temp_max");
                    long sunrise = response.getJSONObject("sys").getLong("sunrise");
                    long sunset = response.getJSONObject("sys").getLong("sunset");

                    //Create Icon URL
                    String iconUrl = Variables.conditionIconUrl;
                    iconUrl = iconUrl.concat(conditionIconCode + ".png");

                    Log.i(LOG, "WeatherObjectData " +
                            "\nLocation: " + location +
                            "\nCondition: " + condition +
                            "\nConditionIconCode: " + conditionIconCode +
                            "\nConditionIcon: " + iconUrl +
                            "\nTemperature: " + temp +
                            "\nTemperature Min: " + temp_min +
                            "\nTemperature Max: " + temp_max +
                            "\nSunrise: " + sunrise +
                            "\nSunset: " + sunset);



                    weather = new Weather(location, condition,conditionIconCode,iconUrl, temp, temp_min, temp_max, 239482394L,239849384L);




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
        return weather;

    }

    //Puts the Data into the UI
    public static void generateWeatherUI(Weather weather){
        MainActivity.textViewLocation.setText(weather.getLocation());
        MainActivity.textViewCondition.setText(weather.getCondition());
        MainActivity.textViewTemp.setText(weather.getTemp());
        MainActivity.textViewTempMax.setText(weather.temp_max);
        MainActivity.textViewTempMin.setText(weather.temp_min);



    }


    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(int temp_min) {
        this.temp_min = temp_min;
    }

    public int getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(int temp_max) {
        this.temp_max = temp_max;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
