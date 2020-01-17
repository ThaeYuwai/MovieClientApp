package com.waiway.clientapp;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.PublicKey;
import java.util.ArrayList;

public class FirebaseConnect{
    FirebaseFirestore db;
    CollectionReference movieRef;
    CollectionReference seriesRef;
    CollectionReference categoryRef;
    Context mycontext;
    public FirebaseConnect(Context context) {
        db = FirebaseFirestore.getInstance();
        movieRef = db.collection("movies");
        seriesRef = db.collection("series");
        categoryRef = db.collection("categories");
        mycontext = context;
    }
    public static ArrayList<MovieModel> movieModels = new ArrayList<MovieModel>();
    public void getAllMovie()
    {
        movieRef.whereEqualTo("movieCategories","Movies").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                movieModels.clear();
                for(DocumentSnapshot s : queryDocumentSnapshots)
                {
                    movieModels.add(s.toObject(MovieModel.class));
                }
                MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(movieModels,mycontext);
                MoviesFragment.rc_movies.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mycontext, RecyclerView.HORIZONTAL,false);
                MoviesFragment.rc_movies.setLayoutManager(linearLayoutManager);
            }
        });

    }

    public static ArrayList<SeriesModel> seriesModels = new ArrayList<SeriesModel>();
    public void  getAllSeries(){

        seriesRef.whereEqualTo("seriesCategory","Series").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                seriesModels.clear();
                for(DocumentSnapshot s : queryDocumentSnapshots)
                {
                    seriesModels.add(s.toObject(SeriesModel.class));

                }
                SeriesRecyclerAdapter adapter = new SeriesRecyclerAdapter(seriesModels,mycontext);
                SeriesFragment.rc_all_series.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(mycontext,2);
                SeriesFragment.rc_all_series.setLayoutManager(manager);
            }
        });
        
    }

    static  ArrayList<MovieModel> episodeModel = new ArrayList<>();
    public void getEpBySeriesName(String seriesName)
    {
        movieRef.whereEqualTo("movieSeries",seriesName).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                episodeModel.clear();
                for(DocumentSnapshot s : queryDocumentSnapshots)
                {
                    episodeModel.add(s.toObject(MovieModel.class));
                }
                EpisodeRecyclerAdapter adapter = new EpisodeRecyclerAdapter(episodeModel,mycontext);
                SeriesDetailsActivity.recyclerView.setAdapter(adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mycontext, RecyclerView.VERTICAL,false);
                SeriesDetailsActivity.recyclerView.setLayoutManager(linearLayoutManager);
            }
        });

    }
}
