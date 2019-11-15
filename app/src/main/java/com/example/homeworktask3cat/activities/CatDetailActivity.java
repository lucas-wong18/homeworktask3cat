package com.example.homeworktask3cat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.homeworktask3cat.R;
import com.example.homeworktask3cat.model.Cat;
import com.example.homeworktask3cat.FakeDatabase;
import com.example.homeworktask3cat.model.Picture;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.homeworktask3cat.FavouriteAdapter.favouriteList;

public class CatDetailActivity extends AppCompatActivity {

    private TextView Breed;
    private TextView Description;
    private TextView Weight;
    private TextView Temperament;
    private TextView Origin;
    private TextView LifeSpan;
    private TextView WikipediaUrl;
    private TextView DogFriendly;
    private ArrayList<Picture> PictureList;
    private ImageView detailPicture;
    private Button setFavourite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        // Get the intent that was used to travel to this activity
        Intent intent = getIntent();

        String id = intent.getStringExtra("id");

        // This will now work because first we added the articles that resulted from the Gson
        // conversion to the FakeDatabase.

        final Cat cat = FakeDatabase.getCatById(id);

        Breed = findViewById(R.id.cat_name);
        Description = findViewById(R.id.description);
        Weight = findViewById(R.id.weight);
        Temperament = findViewById(R.id.temperament);
        Origin = findViewById(R.id.origin);
        LifeSpan = findViewById(R.id.lifespan);
        WikipediaUrl = findViewById(R.id.wikipediaurl);
        DogFriendly = findViewById(R.id.dogfriendly);


        setFavourite = findViewById(R.id.set_favourite);
        detailPicture = findViewById(R.id.picture);



        if (cat.getName() == null) {
            Breed.setText("No Data");
        } else {
            Breed.setText(cat.getName());
        }
        if (cat.getDescription() == null) {
            Description.setText("No Data");
        } else {
            Description.setText(cat.getDescription());
        }
        if(cat.getWeight()== null) {
            Weight.setText("No Data");
        } else {
            Weight.setText(cat.getWeight().getMetric() + " kg");
        }
        if(cat.getTemperament()== null) {
            Temperament.setText("No Data");
        } else {
            Temperament.setText(cat.getTemperament());
        }
        if (cat.getOrigin() == null) {
            Origin.setText("No Data");
        } else {
            Origin.setText(cat.getOrigin());
        }
        if(cat.getLife_span()== null) {
            LifeSpan.setText("No Data");
        } else {
            LifeSpan.setText(cat.getLife_span() + " years");
        }
        if(cat.getWikipedia_url()== null) {
            WikipediaUrl.setText("No Data");
        } else {
            WikipediaUrl.setText(cat.getWikipedia_url());
        }
        if(cat.getDog_friendly() == 0) {
            DogFriendly.setText("No Data");
        } else {
            DogFriendly.setText("Tolerance level " + cat.getDog_friendly());
        }

        setFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteList.add(cat);
                Toast.makeText(CatDetailActivity.this, cat.getName() + " added to favourites", Toast.LENGTH_LONG).show();
            }
        });

        String url = "https://api.thecatapi.com/v1/images/search?breed_ids=" + cat.getId();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                Gson gson = new Gson();
                Picture[] pictureArray = gson.fromJson(response, Picture[].class);
                PictureList = new ArrayList<Picture>(Arrays.asList(pictureArray));
                Picture thisPicture = PictureList.get(0);
                System.out.println(thisPicture.getUrl());
                String imageUrl = thisPicture.getUrl();
                Glide.with(CatDetailActivity.this).load(imageUrl).into(detailPicture);




                // NOTE: The New York Times doesn't actually provide the full content of their articles.
                // This is why the JSON doesn't contain any content field. But, you can imagine if they did,
                // then it would be very simple to display that content here.
                //
                // If we turn our focus to the Books data, then there is a lot more that we can work with.

                // Setting the image.
                // Notice that the image we get is very blurry. This is because we've just selected the
                // first link in the JSON (by using index 0 after getMedia()). You could think of a way to
                // write a method that allows you to get the biggest image out of the array of images.
                //if (article.getMedia() != null) {
                //String imageUrl = article.getMedia()[0].getMedia_metadata()[0].getUrl();
                //Glide.with(this).load(imageUrl).into(imageView);

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }
}
