package de.yanniksimon.mypersonalhub;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import de.yanniksimon.mypersonalhub.Weather.Weather;
import de.yanniksimon.mypersonalhub.Welcome.Time;

public class MainActivity extends AppCompatActivity {

    public static TextView textViewDate, textViewTime, textViewWelcomeMessage;

    //Weather UI Elements
    public static TextView textViewLocation, textViewCondition, textViewTemp, textViewTempMax, textViewTempMin;
    public static ImageView imageViewCondition;


    public static RequestQueue requestQueue;

    //Context
    private static Context context;



    private static String LOG = "LOG: MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();


        //Assign UI Elements
        Log.i(LOG, "Assign UI Elements");

        //Welcome UI Elements
        textViewDate = findViewById(R.id.tv_date);
        textViewTime = findViewById(R.id.tv_time);
        textViewWelcomeMessage = findViewById(R.id.tv_welcomeMessage);

        //Weather UI Elements
        textViewLocation = findViewById(R.id.textView_location);
        textViewCondition = findViewById(R.id.textView_condition);
        textViewTemp = findViewById(R.id.textView_temp);
        textViewTempMax = findViewById(R.id.textView_temp_max);
        textViewTempMin = findViewById(R.id.textView_temp_min);
        imageViewCondition = findViewById(R.id.imageView_condition);

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

        //Load Data
        Weather weather = Weather.parseJsonWeatherData();

    }

    public static Context getContext(){
        return context;
    }
}
