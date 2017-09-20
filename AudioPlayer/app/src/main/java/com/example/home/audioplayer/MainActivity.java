package com.example.home.audioplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;
import java.util.logging.LogRecord;


public class MainActivity extends Activity {
    private final int STATE_STOPED = 0;
    private final int STATE_PLAYED = 1;
    private final int STATE_PAUSED = 2;

    private int state;
    private Button bPlay;
    private ImageButton bPlayePause, idDestroi;
    private MediaPlayer mediaPlayer;
    private Handler myHandler = new Handler();
    private TextView text, startTime, endTime;
    private SeekBar seekBar;
    private  double startDoubleTime = 0;
    private  double endDobleTime = 0;


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
        bPlay = (Button)findViewById(R.id.bPlay);
        //idDestroi = (ImageButton) findViewById(R.id.idDestroi);
        //bPlayePause.setBackgroundResource(R.drawable.layerdrawable);
        text = (TextView) findViewById(R.id.text);
        endTime= (TextView) findViewById(R.id.endTime);
        startTime= (TextView) findViewById(R.id.startTime);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.muzmoru);
        state = STATE_STOPED;
        initViews();


        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Начинаем воспроизводить музыку", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                endDobleTime = mediaPlayer.getDuration();
                startDoubleTime = mediaPlayer.getCurrentPosition();

                endTime.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) endDobleTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) endDobleTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) endDobleTime)))
                );

                startTime.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startDoubleTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startDoubleTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startDoubleTime)))
                );
                myHandler.postDelayed(UpdateSongTime,100);


                seekBar.setProgress((int) startDoubleTime);
                seekBar.setMax((int) endDobleTime);

            }
        });
        //startTime.setText((int)startDoubleTime);
        //endTime.setText((int)endDobleTime);
        //startDoubleTime = mediaPlayer.getCurrentPosition();
        //endDobleTime = mediaPlayer.getDuration();
        //startTime.setText((int)startDoubleTime);
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
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                state = STATE_PLAYED;

                break;
            case STATE_PLAYED:
                mediaPlayer.pause();
                bPlayePause.setImageResource(android.R.drawable.ic_media_play);
                text.setText("Pause");
                state = STATE_PAUSED;
                startDoubleTime = mediaPlayer.getCurrentPosition();
                startTime.setText((int) startDoubleTime);
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

