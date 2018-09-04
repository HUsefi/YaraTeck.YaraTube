package com.yaratech.yaratube;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class ExoPlayerActivity extends AppCompatActivity {
    public static String PLAYER_ACTIVITY_KEY = "Player_Activity";
    SimpleExoPlayer simpleExoPlayer;
    PlayerView playerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);

        Intent intent = getIntent();
        String videoUri = intent.getStringExtra(PLAYER_ACTIVITY_KEY);

        playerView = (PlayerView) findViewById(R.id.player_view);
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "ExoPlayer"));

        HlsMediaSource hlsMediaSource = new HlsMediaSource
                .Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUri));
        simpleExoPlayer.prepare(hlsMediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        playerView.setPlayer(simpleExoPlayer);
    }


}
