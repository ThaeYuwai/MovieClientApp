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


public class EpisodeRecyclerAdapter extends RecyclerView.Adapter<EpisodeRecyclerAdapter.EpisodeHolder> {
    ArrayList<MovieModel> movieModels = new ArrayList<MovieModel>();
    Context myContext;

    public EpisodeRecyclerAdapter(ArrayList<MovieModel> movieModels, Context myContext) {
        this.movieModels = movieModels;
        this.myContext = myContext;
    }

    @NonNull
    @Override
    public EpisodeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View myview = inflater.inflate(R.layout.eplist,parent,false);

        return new EpisodeHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeHolder holder, final int position) {
        holder.txtname.setText(movieModels.get(position).movieName);
        holder.txtsr.setText((position+1)+"");

        holder.icplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext,PlayVideoActivity.class);
                try {
                    PlayVideoActivity.videourl = MediaFireConnect.getVideoUrl(movieModels.get(position).movieVideoLink);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    myContext.startActivity(intent);
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

    public class EpisodeHolder extends RecyclerView.ViewHolder
    {
        ImageView icplay;
        TextView txtsr,txtname;
        public EpisodeHolder(@NonNull View itemView) {
            super(itemView);
            txtsr = itemView.findViewById(R.id.txt_ep_sr);
            txtname = itemView.findViewById(R.id.txt_ep_name);
            icplay = itemView.findViewById(R.id.ep_play);
        }
    }
}
