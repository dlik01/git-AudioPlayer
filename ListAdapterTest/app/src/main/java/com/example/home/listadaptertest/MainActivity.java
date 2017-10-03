package com.example.home.listadaptertest;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        String [] namber =  {"1","2" ,"3","4","5","6","7","8","9","10","11","12","13",
                "14","15","16","17","18","19","20"};
        setListAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, namber));
    }
}
