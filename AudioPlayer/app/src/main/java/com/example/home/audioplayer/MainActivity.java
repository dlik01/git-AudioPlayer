package com.example.home.audioplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.app.Activity;


public class MainActivity extends Activity {
    private final int STATE_STOPED = 0;
    private final int STATE_PLAYED = 1;
    private final int STATE_PAUSED = 2;

    private int state;
    private ImageButton bPlayePause, idDestroi;
    private MediaPlayer mediaPlayer;
    private TextView text;
    private SeekBar seekBar;


    //меню

    //public boolean onCreateOptionsMenu(Menu menu){
    //    MenuInflater menuInflater = getMenuInflater();

    //    return  super.onCreateOptionsMenu(menu);
    //}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPlayePause = (ImageButton) findViewById(R.id.bPlayPause);
        //idDestroi = (ImageButton) findViewById(R.id.idDestroi);
        bPlayePause.setBackgroundResource(R.drawable.layerdrawable);
        text = (TextView) findViewById(R.id.text);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.muzmoru);
        state = STATE_STOPED;
        initViews();
        ///// тест кода

    }

    private void initViews() {
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });
    }

    private void seekChange(View v) {
        if (mediaPlayer.isPlaying()){
            SeekBar sb = (SeekBar)v;
            mediaPlayer.seekTo(sb.getProgress());
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bPlayPause:
                preparePlayerState();
                break;
            case R.id.bStop:
                mediaPlayer.stop();
                bPlayePause.setImageResource(android.R.drawable.ic_media_play);
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
                bPlayePause.setImageResource(android.R.drawable.ic_media_pause);
                text.setText("Play");
                state = STATE_PLAYED;
                break;
            case STATE_PLAYED:
                mediaPlayer.pause();
                bPlayePause.setImageResource(android.R.drawable.ic_media_play);
                text.setText("Pause");
                state = STATE_PAUSED;
                break;
            case STATE_PAUSED:
                mediaPlayer.start();
                bPlayePause.setImageResource(android.R.drawable.ic_media_pause);
                text.setText("Continue");
                state = STATE_PLAYED;
                break;
        }
    }


}

