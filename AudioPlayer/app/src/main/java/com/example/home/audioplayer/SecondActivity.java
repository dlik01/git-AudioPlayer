package com.example.home.audioplayer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.widget.TextView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by home on 25.09.2017.
 */

public class SecondActivity extends ListActivity {

    private List<String> directoryEntries = new ArrayList<String>();
    private File currentDirectory = new File("/");
    private TextView titleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //установить основную компоновку
        setContentView(R.layout.second_activity);
        //перейдите в корневой каталог
        browseTo(new File("/sdcard/Music"));
    }

    //перейдите в родительский каталог
    private void upOneLevel(){
        if(this.currentDirectory.getParent() != null) {
            this.browseTo(this.currentDirectory.getParentFile());
        }
    }

    //просматривать файл или каталог
    private void browseTo(final File aDirectory){
        //если мы хотим просмотреть каталог
        if (aDirectory.isDirectory()){
            //заполнить список файлами из этого каталога
            this.currentDirectory = aDirectory;
            fill(aDirectory.listFiles());
        }
    }
    //список заполнения
    private void fill(File[] files) {
        //Очистить список
        this.directoryEntries.clear();
        //добавить все файлы в список
        for (File file : files) {
            this.directoryEntries.add(file.getAbsolutePath());
        }
        //создать адаптер массива, чтобы показать все
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, R.layout.row, this.directoryEntries);
        this.setListAdapter(directoryList);
    }
    //когда вы нажали на элемент

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //получить выбранное имя файла

        int selectionRowID = position;
        String selectedFileString = this.directoryEntries.get(selectionRowID);
        //String nameFile = ;
        titleManager.setText(selectedFileString);

        //если мы выберем «..», то вернитесь вверх

     //   if(selectedFileString.equals("..")){
     //       this.upOneLevel();
     //   } else {
            //перейдите к кликному файлу или каталогу, используя browseTo ()
      //      File clickedFile = null;
      //      clickedFile = new File(selectedFileString);
     //       if (clickedFile != null)
     //           this.browseTo(clickedFile);
     //   }
    }
}
