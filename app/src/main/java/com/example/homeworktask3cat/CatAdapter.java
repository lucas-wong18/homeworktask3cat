package com.example.homeworktask3cat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeworktask3cat.activities.CatDetailActivity;
import com.example.homeworktask3cat.model.Cat;

import java.util.ArrayList;
import java.util.List;

// We need to give a type in angle brackets <> when we extend RecyclerView.Adapter
// It's saying that we want an adapter that adapts to ArticleViewHolder (our custom ViewHolder)
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> implements Filterable {
    // class variable that holds the data that we want to adapt

    public ArrayList<Cat> cats;
    private List<Cat> catList;

    public void setData(ArrayList<Cat> catsToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.cats = catsToAdapt;

        catList = new ArrayList<>(catsToAdapt);

    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CatViewHolder holder, final int position) {
        final Cat catAtPosition = cats.get(position);
        holder.breed.setText(catAtPosition.getName());





        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("id", catAtPosition.getId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() { return cats.size();}

    // ViewHolder represents one item, but doesn't have data when it's first constructed.
    // We assign the data in onBindViewHolder.
    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView breed;

        // This constructor is used in onCreateViewHolder
        public CatViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            breed = v.findViewById(R.id.cat_name);

        }
    }

    @Override
    public Filter getFilter() {
        return searchResults;
    }

    private Filter searchResults = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence userInput) {

            List<Cat> searchList = new ArrayList<>();


            if (userInput == null || userInput.length() == 0) {
                searchList.addAll(catList);
            }
            else {
                String searchInput = userInput.toString().toLowerCase().trim();
                for (Cat search : catList) {
                    if (search.getName().toLowerCase().contains(searchInput)) {
                        searchList.add(search);
                    }
                }
            }

            FilterResults output = new FilterResults();
            output.values = searchList;
            return output;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cats.clear();
            cats.addAll((ArrayList) results.values);

            notifyDataSetChanged();;

        }
    };




}
