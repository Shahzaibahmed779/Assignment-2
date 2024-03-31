package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Restaurants> restaurants = new ArrayList<>();
    ArrayList<Restaurants> filteredList = new ArrayList<>();
    RestaurantAdapter adapter;
    EditText searchEditText;
    ImageButton searchButton;
    private String lastUniqueKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchId);
        searchButton = findViewById(R.id.leftIcon);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_id);
        setupRestaurants();
        loadRestaurantsFromSharedPreferences();
        adapter = new RestaurantAdapter(this, restaurants);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter(searchEditText.getText().toString());
            }
        });
    }

    private void loadRestaurantsFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("RestaurantDetails", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            if (key.contains("_name")) { // Identify entries for  names
                String uniqueKey = key.substring(0, key.lastIndexOf("_"));
                String name = (String) entry.getValue();
                String location = sharedPreferences.getString(uniqueKey + "_location", "");
                String phone = sharedPreferences.getString(uniqueKey + "_phone", "");
                String description = sharedPreferences.getString(uniqueKey + "_description", "");
                String rating = sharedPreferences.getString(uniqueKey + "_rating", "");
                restaurants.add(new Restaurants(rating, name, location, phone, description));
            }
        }
    }

    private void filter(String text) {
        filteredList.clear();
        for (Restaurants item : restaurants) {
            if (item.getName().toLowerCase(Locale.ROOT).startsWith(text.toLowerCase(Locale.ROOT))) {
                filteredList.add(item);
            }
        }
        Collections.sort(filteredList, Collections.<Restaurants>reverseOrder());
        adapter.updateList(filteredList);
    }

    private void setupRestaurants() {
        String[] RestaurantRatings = getResources().getStringArray(R.array.restaurant_ratings);
        String[] RestaurantNames = getResources().getStringArray(R.array.restaurant_names);
        String[] RestaurantLocations = getResources().getStringArray(R.array.restaurant_locations);
        String[] RestaurantPhones = getResources().getStringArray(R.array.restaurant_phones);
        String[] RestaurantDescriptions = getResources().getStringArray(R.array.restaurant_descriptions);

        for (int i = 0; i < RestaurantNames.length; i++) {
            restaurants.add(new Restaurants(RestaurantRatings[i], RestaurantNames[i], RestaurantLocations[i], RestaurantPhones[i], RestaurantDescriptions[i]));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRestaurantsFromSharedPreferences();
        adapter.notifyDataSetChanged();
    }


}
