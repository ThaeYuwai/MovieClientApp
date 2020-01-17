package com.waiway.clientapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieHolder> {
    ArrayList<MovieModel> movieModels = new ArrayList<MovieModel>();
    Context mycontext;

    public MovieRecyclerAdapter(ArrayList<MovieModel> movieModels, Context mycontext) {
        this.movieModels = movieModels;
        this.mycontext = mycontext;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View myview = inflater.inflate(R.layout.movieitem,parent,false);

        return new MovieHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, final int position) {
        holder.txtMovieName.setText(movieModels.get(position).movieName);
        Glide.with(mycontext)
                .load(movieModels.get(position).movieImgLink)
                .into(holder.imgMovie);

        ////////
        holder.imgMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mycontext,PlayVideoActivity.class);
                try {
                    PlayVideoActivity.videourl = MediaFireConnect.getVideoUrl(movieModels.get(position).movieVideoLink);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    mycontext.startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieModels.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder
    {
        ImageView imgMovie;
        TextView txtMovieName;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.img_movie);
            txtMovieName = itemView.findViewById(R.id.txt_movie_name);
        }
    }
}
