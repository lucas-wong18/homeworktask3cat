package com.example.homeworktask3cat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.homeworktask3cat.FavouriteAdapter;
import com.example.homeworktask3cat.R;



/**
 *  This is what you get when you click File > New > Fragment > blank fragment, and don't untick
 *  some of the checkboxes. There's a whole bunch of stuff included that would help you communicate
 *  with the parent Activity.
 *
 *  For the purposes of this app, we don't really need it but I keep it in here just to show you
 *  what it looks like.
 *
 */
public class FavouriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button clearButton;




    public FavouriteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.rv_favourite);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);


        final FavouriteAdapter favouriteAdapter = new FavouriteAdapter();
        recyclerView.setAdapter(favouriteAdapter);

       
        return view;

    }


}





