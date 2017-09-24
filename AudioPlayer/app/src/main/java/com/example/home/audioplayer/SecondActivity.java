package com.example.home.audioplayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by home on 25.09.2017.
 */

public class SecondActivity extends Activity {
    private static final String[] namber = {"1","2" ,"3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
    private ArrayAdapter<String>daNamber;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        listView = (ListView) findViewById(R.id.listView);

        daNamber = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namber);
        listView.setAdapter(daNamber);
        //setListAdapter(new ArrayAdapter<String>());


        this.setTitle("SecondActivity");
    }


}
