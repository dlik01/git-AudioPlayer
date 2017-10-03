package com.example.home.audioplayer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by home on 26.09.2017.
 */

public class SoundAdapter extends BaseAdapter {
    private ArrayList<Sound> sounds;
    private LayoutInflater soundInf;

    public SoundAdapter(Context c, ArrayList<Sound> theSounds){
        sounds = theSounds;
        soundInf = LayoutInflater.from(c);

    }
    @Override
    public int getCount() {
        return sounds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout soundLay = (LinearLayout)soundInf.inflate(R.layout.sound, parent, false);

        TextView soundView = (TextView) soundLay.findViewById(R.id.soung_title);

        Sound currSouand = sounds.get(position);
        soundView.setText(currSouand.getTitle());
        //artistView.setText(currSouand.getArtist());
        //set position as tag
        soundLay.setTag(position);
        return soundLay;
    }
}
