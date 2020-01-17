package com.waiway.clientapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class SeriesRecyclerAdapter extends RecyclerView.Adapter<SeriesRecyclerAdapter.SeriesHolder> {

    ArrayList<SeriesModel> seriesModels = new ArrayList<SeriesModel>();
    Context mycontext;

    private InterstitialAd mInterstitialAd;
    public static Activity activity;
    public SeriesRecyclerAdapter(ArrayList<SeriesModel> seriesModels, Context mycontext) {
        this.seriesModels = seriesModels;
        this.mycontext = mycontext;
    }

    @NonNull
    @Override
    public SeriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movieitem,parent,false);  //get layout
        return new SeriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesHolder holder, final int position) {
        holder.txtseriesName.setText(seriesModels.get(position).seriesName);
        Glide.with(mycontext)
                .load(seriesModels.get(position).seriesImageLink)
                .into(holder.imgseries);

        new MyTask().execute();
        holder.imgseries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                Intent intent = new Intent(mycontext,SeriesDetailsActivity.class);
                SeriesDetailsActivity.model = seriesModels.get(position);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                mycontext.startActivity(intent);}
            }
        });
    }

    @Override
    public int getItemCount() {
        return seriesModels.size();
    }

    public class SeriesHolder extends RecyclerView.ViewHolder
    {
        ImageView imgseries;
        TextView txtseriesName;

        public SeriesHolder(@NonNull View itemView) {
            super(itemView);
            imgseries = itemView.findViewById(R.id.img_movie);
            txtseriesName = itemView.findViewById(R.id.txt_movie_name);
        }
    }

    private class MyTask extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mInterstitialAd = new InterstitialAd(mycontext);
                    mInterstitialAd.setAdUnitId(mycontext.getResources().getString(R.string.interstitial_id));
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }
            });
            return null;
        }
    }
}
