package com.example.homeworktask3cat.fragments;


import android.content.Context;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.homeworktask3cat.R;
import com.example.homeworktask3cat.CatAdapter;
import com.example.homeworktask3cat.FakeDatabase;
import com.example.homeworktask3cat.model.Cat;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;


public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;

    private SearchView searchView;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.rv_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // REFER to the comments in BookRecyclerAdapter

        final CatAdapter catAdapter = new CatAdapter();
        String url = "https://api.thecatapi.com/v1/breeds?api_key=d62cc688-c1a6-47e9-aa05-72d65c33b903";

        searchView = view.findViewById(R.id.search_bar);
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                catAdapter.getFilter().filter(newText);
                return false;
            }
        }
        );




        Context context = getContext();
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cat[] catsArray = gson.fromJson(response, Cat[].class);
                ArrayList<Cat> catArrayList = new ArrayList<Cat>(Arrays.asList(catsArray));

                catAdapter.setData(catArrayList);
                FakeDatabase.saveCatsToFakeDatabase(catArrayList);
                recyclerView.setAdapter(catAdapter);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);

        return view;
    }

    // This is just an example of a way that the Fragment can communicate with the parent Activity.
    // Specifically, this is using a method belonging to the parent.
    // @Override
    //public void onResume() {
    //  super.onResume();
    //MainActivity parent = (MainActivity) getActivity();
    //  parent.showCoolMessage("cool (from ArticleRecyclerFragment onResume)");
}
