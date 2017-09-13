package com.example.home.audioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton bDestroi, bPlayPause,bStop;
    private MediaPlayer mediaPlayer;
    private TextView tView;
    private int state;

    private final int STATE_STOPED = 0;
    private final int STATE_PLAYED = 1;
    private final int STATE_PAUSED = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPlayPause = (ImageButton) findViewById(R.id.bPlayPause);
        bStop = (ImageButton)findViewById(R.id.bStop);
        bDestroi= (ImageButton)findViewById(R.id.bDestroi);
        bDestroi.setBackgroundResource(R.drawable.layerdrawable);
        tView = (TextView) findViewById(R.id.tView);
        state = STATE_STOPED;
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.muzmoru);

        bPlayPause.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bDestroi:
                Toast.makeText(this, "Мы над этим ещё работаем", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bPlayPause:
                preparePlayerState();
                break;
            case R.id.bStop:
                mediaPlayer.stop();
                bPlayPause.setImageResource(android.R.drawable.ic_media_play);
                tView.setText("Стоп");
                state = STATE_STOPED;
            default:
                Toast.makeText(this, "Мы над жтим ещё работаем", Toast.LENGTH_SHORT).show();
        }
    }

    private void preparePlayerState() {
        switch (state){
            case STATE_STOPED:
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.muzmoru);
                mediaPlayer.start();
                bPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                break;
            case STATE_PLAYED:
                mediaPlayer.pause();
                bPlayPause.setImageResource(android.R.drawable.ic_media_play);
                tView.setText("Pause");
                state = STATE_PAUSED;
                break;
            case STATE_PAUSED:
                mediaPlayer.start();
                bPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                tView.setText("Continue");
                state = STATE_PLAYED;
                break;
        }
    }
}
