package com.yaratech.yaratube.ui.player;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.yaratech.yaratube.R;


public class ExoPlayerActivity extends AppCompatActivity {
    public static String PLAYER_ACTIVITY_KEY = "Player_Activity";
    SimpleExoPlayer simpleExoPlayer;
    PlayerView playerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);
        progressBar = findViewById(R.id.progressbar);
        Intent intent = getIntent();
        String videoUri = intent.getStringExtra(PLAYER_ACTIVITY_KEY);
        Log.e("TAG", "" + videoUri);
        playerView = (PlayerView) findViewById(R.id.player_view);
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "ExoPlayer"));

        HlsMediaSource hlsMediaSource = new HlsMediaSource
                .Factory(new CacheDataSourceFactory(
                getApplicationContext(),
                100 * 1024 * 1024,
                5 * 1024 * 1024))
                .createMediaSource(Uri.parse(videoUri));
        simpleExoPlayer.prepare(hlsMediaSource);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == ExoPlayer.STATE_BUFFERING) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
        simpleExoPlayer.setPlayWhenReady(true);
        playerView.setPlayer(simpleExoPlayer);
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        simpleExoPlayer.release();
        simpleExoPlayer = null;
    }

}
