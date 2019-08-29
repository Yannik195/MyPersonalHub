package de.yanniksimon.mypersonalhub.News;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.yanniksimon.mypersonalhub.MainActivity;
import de.yanniksimon.mypersonalhub.Variables.Variables;

public class News {

    private String name, title, description, url, imageUrl, date;

    private static JSONArray articles;
    private static ArrayList<News> newsArrayList;


    private static String LOG = "LOG: NewsClass";

    public News(String name,String title, String description, String url, String imageUrl, String date){

        this.name = name;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.date = date;

    }



    public static void parseJsonNewsData(){
        Log.i(LOG, "parseJsonNewsData");
        String url = Variables.newsApiUrl;
        newsArrayList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(LOG, "JsonObjectRequest - onResponse");

                try {
                    articles = response.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++){
                        String name = articles.getJSONObject(i).getJSONObject("source").getString("name");
                        String title = articles.getJSONObject(i).getString("title");
                        String description = articles.getJSONObject(i).getString("description");
                        String url = articles.getJSONObject(i).getString("url");
                        String imageUrl = articles.getJSONObject(i).getString("urlToImage");
                        String date = articles.getJSONObject(i).getString("publishedAt");

                        newsArrayList.add(new News(name,title,description,url,imageUrl,date));
                        generateNewsUI(i);

                    }
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

    private static void generateNewsUI(int i){
        Log.i(LOG, "generateNewsUI" + newsArrayList.get(0).getTitle());
        MainActivity.textViewNewsName.setText(newsArrayList.get(0).getName());
        MainActivity.textViewNewsTitle.setText(newsArrayList.get(0).getTitle());
        MainActivity.textViewNewsTime.setText(newsArrayList.get(0).getDate());
        MainActivity.textViewNewsTitle.setText(newsArrayList.get(0).getTitle());
        MainActivity.textViewNewsDescription.setText(newsArrayList.get(0).getDescription());

        Glide
                .with(MainActivity.getContext())
                .load(newsArrayList.get(0).getImageUrl())
                .centerCrop()
                .into(MainActivity.imageViewNewsImage);

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
