package com.waiway.clientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class PlayVideoActivity extends AppCompatActivity {

    public static String videourl = "";
    SimpleExoPlayerView exoPlayerView ;
    SimpleExoPlayer player;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        exoPlayerView = findViewById(R.id.exoplayer);

        ///////     google ကေနယူ  permit လုပ်
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        BandwidthMeter b = new DefaultBandwidthMeter();  //
        progressBar = findViewById(R.id.videoloading);

      TrackSelector selector = new DefaultTrackSelector();



        player = ExoPlayerFactory.newSimpleInstance(getApplicationContext(),new DefaultTrackSelector());
        DefaultExtractorsFactory df = new DefaultExtractorsFactory();
        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exo_player");
        Uri uri = Uri.parse(videourl);
        ExtractorMediaSource mediaSource = new ExtractorMediaSource(uri,dataSourceFactory,df,null,null);
        player.prepare(mediaSource);

        exoPlayerView.setPlayer(player);
        player.setPlayWhenReady(true);


        // put data

        SimpleExoPlayer.EventListener listener = new ExoPlayer.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if(playbackState == ExoPlayer.STATE_BUFFERING)
                {
                    progressBar.setVisibility(View.VISIBLE);
                }
                else
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity() {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }
        };
        player.addListener(listener);

    }

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();


    }
}
