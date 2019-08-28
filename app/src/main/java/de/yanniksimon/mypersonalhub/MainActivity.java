package de.yanniksimon.mypersonalhub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import de.yanniksimon.mypersonalhub.Weather.Weather;
import de.yanniksimon.mypersonalhub.Welcome.Time;

public class MainActivity extends AppCompatActivity {

    public static TextView textViewDate, textViewTime, textViewWelcomeMessage;

    public static RequestQueue requestQueue;



    private static String LOG = "LOG: MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOG, "Assign Variables");
        textViewDate = findViewById(R.id.tv_date);
        textViewTime = findViewById(R.id.tv_time);
        textViewWelcomeMessage = findViewById(R.id.tv_welcomeMessage);


        //Volley
        Log.i(LOG, "Run Volley");
        requestQueue = Volley.newRequestQueue(this);


        //Welcome
        Log.i(LOG, "Run Welcome Functions");
        Time.setWelcomeTime();
        Time.setWelcomeMessage();
        Time.setWelcomeDate();

        //Weather
        Log.i(LOG, "Run Weather Functions");
        Weather.parseJsonWeatherData();

    }
}
