package com.example.home.audioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int STATE_STOPED = 0;
    private final int STATE_PLAYED = 1;
    private final int STATE_PAUSED = 2;

    private int state;
    private ImageButton bPlayPause, bStop, idDestroi;
    private MediaPlayer mediaPlayer;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPlayPause= (ImageButton) findViewById(R.id.bPlayPause);
        bStop = (ImageButton)findViewById(R.id.bStop);
        idDestroi = (ImageButton) findViewById(R.id.bDestroi);
        idDestroi.setBackgroundResource(R.drawable.layerdrawable);
        text = (TextView) findViewById(R.id.text);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.muzmoru);
        state = STATE_STOPED;

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bPlayPause:
                preparePlayerState();
                break;
            case R.id.bStop:
                mediaPlayer.stop();
                bPlayPause.setImageResource(android.R.drawable.ic_media_play);
                text.setText("Stop");
                state = STATE_STOPED;
                break;
        }
    }
    private void preparePlayerState() {
        switch (state){
            case STATE_STOPED :
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.muzmoru);
                mediaPlayer.start();
                bPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                text.setText("Play");
                state = STATE_PLAYED;
                break;
            case STATE_PLAYED:
                mediaPlayer.pause();
                bPlayPause.setImageResource(android.R.drawable.ic_media_play);
                text.setText("Pause");
                state = STATE_PAUSED;
                break;
            case STATE_PAUSED:
                mediaPlayer.start();
                bPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                text.setText("Continue");
                state = STATE_PLAYED;
                break;
        }
    }
}
