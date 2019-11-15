package com.example.homeworktask3cat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworktask3cat.model.Cat;

import java.util.ArrayList;

// We need to give a type in angle brackets <> when we extend RecyclerView.Adapter
// It's saying that we want an adapter that adapts to ArticleViewHolder (our custom ViewHolder)
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    // class variable that holds the data that we want to adapt



    public static ArrayList<Cat> favouriteList = new ArrayList<>();

    public void setData(ArrayList<Cat> favouritesToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.favouriteList = favouritesToAdapt;
    }


    @NonNull
    @Override
    public FavouriteAdapter.FavouriteViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_favourite, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        FavouriteAdapter.FavouriteViewHolder favouriteViewHolder= new FavouriteAdapter.FavouriteViewHolder(view);
        return favouriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FavouriteViewHolder holder, final int position) {
        final Cat catAtPosition = favouriteList.get(position);
        holder.favouriteBreed.setText(catAtPosition.getName());
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    // ViewHolder represents one item, but doesn't have data when it's first constructed.
    // We assign the data in onBindViewHolder.
    public static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        public TextView favouriteBreed;



        public FavouriteViewHolder(View v) {
            super(v);
            favouriteBreed = v.findViewById(R.id.favourite_breed);
        }

    }



}
