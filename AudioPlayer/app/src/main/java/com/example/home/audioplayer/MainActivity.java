package com.example.home.audioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton bDestroi, bPlayPause;
    private MediaPlayer mediaPlayer;
    private TextView tView;
    private int state;

    private final int STATE_PLAYED = 1;
    private final int STATE_PAUSED = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPlayPause = (ImageButton) findViewById(R.id.bPlayPause);
        bDestroi= (ImageButton)findViewById(R.id.bDestroi);
        tView = (TextView) findViewById(R.id.tView);
    }
}
