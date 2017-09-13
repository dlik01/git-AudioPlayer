package com.example.home.audioplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton bDestroi, bPlayPause;
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
        bDestroi= (ImageButton)findViewById(R.id.bDestroi);
        tView = (TextView) findViewById(R.id.tView);
        state = STATE_STOPED;
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.muzmoru);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bDestroi:
                Toast.makeText(this, "Мы над этим ещё работаем", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bPlayPause:
                preperePlayerState();
                break;
            default:
                Toast.makeText(this, "Мы над жтим ещё работаем", Toast.LENGTH_SHORT).show();
        }
    }

    private void preperePlayerState() {
        switch (state){
            case STATE_STOPED:
                mediaPlayer.stop();

                break;
            case STATE_PLAYED:
                mediaPlayer.pause();
                bPlayPause.setImageResource(android.R.drawable.ic_media_play);
                state = STATE_PAUSED;
                tView.setText("Пауза");
                break;
            case STATE_PAUSED:
                mediaPlayer.start();
                bPlayPause.setImageResource(android.R.drawable.ic_media_pause);
                tView.setText("Продолжаем");
                state = STATE_PLAYED;

                break;
        }
    }
}
