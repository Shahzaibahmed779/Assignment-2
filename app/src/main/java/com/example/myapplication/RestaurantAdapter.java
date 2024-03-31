package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    Context context;
    ArrayList<Restaurants> restaurants;
    public RestaurantAdapter (Context context, ArrayList<Restaurants> restaurants){
        this.context=context;
        this.restaurants=restaurants;
    }

    public void updateList(ArrayList<Restaurants> filteredList) {
        restaurants = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout,parent, false);
        return new RestaurantAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvRating.setText(String.valueOf(restaurants.get(position).getRating()));
        holder.tvName.setText(restaurants.get(position).getName());
        holder.tvLocation.setText(restaurants.get(position).getLocation());
        holder.tvPhoneNumber.setText(restaurants.get(position).getPhoneNumber());
        holder.tvDescription.setText(restaurants.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvRating,tvName,tvLocation,tvPhoneNumber,tvDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRating = itemView.findViewById((R.id.ratingTextView));
            tvName = itemView.findViewById((R.id.nameTextView));
            tvLocation = itemView.findViewById((R.id.locationTextView));
            tvPhoneNumber = itemView.findViewById((R.id.phoneTextView));
            tvDescription = itemView.findViewById((R.id.descriptionTextView));
        }
    }
    }
