package com.example.home.audioplayer;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {
    private Button b1,b2,b3,b4, btSecondActivity;
    //private ImageView iv;
    private MediaPlayer mediaPlayer;
    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;
    private TextView tx1,tx2,tx3, tvStart, test2, test1, testView2;
    //Задал константы для передачи файлов
    public final static String musicName = "Name";
    public final static String musicAdress = "Adress";
    public  String musicIdPath;
    public final static int ACTION_EDIT= 101;

    public static int oneTimeOnly = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 =(Button)findViewById(R.id.button3);
        b4 =(Button)findViewById(R.id.button4);
        btSecondActivity =(Button)findViewById(R.id.btSecondActivity);

        tx1=(TextView)findViewById(R.id.tvStart);
        tx2=(TextView)findViewById(R.id.tvEnd);
        tx3=(TextView)findViewById(R.id.tvOptions);

        test1 = (TextView) findViewById(R.id.test1);
        test2 = (TextView) findViewById(R.id.test2);
        tvStart = (TextView) findViewById(R.id.tvStart);

        testView2 = (TextView)findViewById(R.id.testView2);


        seekbar=(SeekBar)findViewById(R.id.seekBar);
        b2.setEnabled(false);

        initViews();
        //получаем данные папки
        Bundle extras = getIntent().getExtras();
        tx3.setText(extras.getString(SecondActivity.NAME));
        testView2.setText(extras.getString(SecondActivity.PATH));
        musicIdPath = (String) tx3.getText()+ testView2.getText();
        test1.setText(musicIdPath);
        tx3.setText(extras.getString(SecondActivity.NAME));
        mediaPlayer = MediaPlayer.create(MainActivity.this, Uri.parse(musicIdPath));
        //try {
        //    mediaPlayer.setDataSource(String.valueOf(extras.getString(SecondActivity.PATH)));
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        //mediaPlayer.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);

    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            tx1.setText(String.format("%d min %d sec",

                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            seekbar.setMax(mediaPlayer.getDuration());
            //tvSeekbar.setText("Т/З Seekbar = " + seekbar.getProgress());
            //tvMaxSeekbar.setText("М/З SeekBar = " + seekbar.getMax());
            //tvStart.setText("текущее время песни = " +mediaPlayer.getCurrentPosition());
            myHandler.postDelayed(this, 300);
        }
    };

    private void initViews() {
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        //tvSeekbar.setText("значение Seekbar = " + seekbar.getProgress());
        seekbar.setOnTouchListener(new View.OnTouchListener() {
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
    //protected void onActivityResult(int requestCode, int resultCode, Intent data){
    //    super.onActivityResult(requestCode, resultCode, data);
    //    if (requestCode == RESULT_OK){
    //        Bundle extras = data.getExtras();
    //        testView.setText(extras.getString(musicName));
    //        testView2.setText(extras.getString(musicAdress));
    //    }
    //}

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btSecondActivity:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SecondActivity.class);
                //добавляем параметры
                intent.putExtra(musicName, tx3.getText());
                intent.putExtra(musicAdress, testView2.getText());
                startActivityForResult(intent, ACTION_EDIT);
                break;
            case  R.id.button:
                int temp = (int)startTime;

                if((temp+forwardTime)<=finalTime){
                    startTime = startTime + forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(),"You have Jumped forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Cannot jump forward 5 seconds",Toast.LENGTH_SHORT).show();
                }
                break;
            case  R.id.button2:
                Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                    b2.setEnabled(false);
                    b3.setEnabled(true);
                break;
            case  R.id.button3:
                Toast.makeText(getApplicationContext(), "Playing sound",Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }
                tx2.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
                );

                tx1.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
                );

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                b2.setEnabled(true);
                b3.setEnabled(false);
                break;
            case  R.id.button4:
                temp = (int) startTime;
                if((temp-backwardTime)>0){
                        startTime = startTime - backwardTime;
                        mediaPlayer.seekTo((int) startTime);
                        Toast.makeText(getApplicationContext(),"You have Jumped backward 5 seconds",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Cannot jump backward 5 seconds",Toast.LENGTH_SHORT).show();
                    }

                break;
        }
    }
}

