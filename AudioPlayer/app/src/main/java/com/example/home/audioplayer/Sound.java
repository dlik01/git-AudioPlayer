package com.example.home.audioplayer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by home on 26.09.2017.
 */

public class Sound{
    private long id;
    private String title;

    public Sound(long soundId, String soundTitle) {
        id = soundId;
        title = soundTitle;
    }

    public long getId(){return id;}
    public String getTitle(){return title;}
}
