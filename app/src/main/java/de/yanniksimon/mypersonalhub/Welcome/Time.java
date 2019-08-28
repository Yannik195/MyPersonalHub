package de.yanniksimon.mypersonalhub.Welcome;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.yanniksimon.mypersonalhub.MainActivity;
import de.yanniksimon.mypersonalhub.Variables.Variables;


public class Time {
    private static String LOG = "LOG: TimeClass";

    private static Date date;


    public Time(){
        Log.i(LOG, "Constructor");
        date = Calendar.getInstance().getTime();






        Log.i(LOG, "Date: " + date.toString());

    }

    public static void setWelcomeTime(){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String time = timeFormat.format(date);
        MainActivity.textViewTime.setText(time);
    }

    public static void setWelcomeMessage(){
        int hourOfDate;
        String begruessung = "Hallo";
        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("H");
        hourOfDate = Integer.parseInt(dateFormat.format(date));

        Log.i(LOG, dateFormat.format(date));

        if (hourOfDate >= 4 && hourOfDate <= 10){
            begruessung = "Guten Morgen, ";
        } else if (hourOfDate >= 11 && hourOfDate <= 13){
            begruessung = "Mahlzeit,\n";
        } else if (hourOfDate >= 14 && hourOfDate <= 16){
            begruessung = "Guten Mittag,\n";
        } else if (hourOfDate >= 17){
            begruessung = "Guten Abend,\n";
        }

        MainActivity.textViewWelcomeMessage.setText(begruessung + Variables.userName);

    }

    public static void setWelcomeDate(){
        String wochentag;
        String datum;

        Date date = Calendar.getInstance().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MMM yyyy");
        SimpleDateFormat wochentagFormat = new SimpleDateFormat("E");

        MainActivity.textViewDate.setText("Es ist " + wochentagFormat.format(date) + " der " + dateFormat.format(date));

    }
}
