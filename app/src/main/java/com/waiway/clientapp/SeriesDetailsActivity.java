package com.waiway.clientapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SeriesDetailsActivity extends AppCompatActivity {

    public  static  SeriesModel model;
    static RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_details);
        setContentView(R.layout.activity_series_details);
        TextView txtSeriesName = findViewById(R.id.txt_series_name);
        ImageView imageView = findViewById(R.id.series_image);
        Glide.with(getApplicationContext())
                .load(model.seriesImageLink)
                .into(imageView);
        txtSeriesName.setText(model.seriesName);
        recyclerView = findViewById(R.id.episode_list);
        FirebaseConnect firebaseConnect = new FirebaseConnect(getApplicationContext());
        firebaseConnect.getEpBySeriesName(model.seriesName);

    }
}
