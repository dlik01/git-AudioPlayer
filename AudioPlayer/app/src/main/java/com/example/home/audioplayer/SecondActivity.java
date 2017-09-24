package com.example.home.audioplayer;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by home on 25.09.2017.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        this.setTitle("SecondActivity");
    }
}
