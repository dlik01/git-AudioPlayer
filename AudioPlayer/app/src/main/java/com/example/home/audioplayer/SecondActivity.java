package com.example.home.audioplayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
public class SecondActivity extends ListActivity {
    private List<String> directoryEntries = new ArrayList<String>();
    private File currentDirectory = new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)));
    //при запуске приложения
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //установить основную компоновку
        setContentView(R.layout.second_activity);
        //перейдите в корневой каталог
        browseTo(new File(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC))));
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

            //set titleManager text
            TextView titleManager = (TextView) findViewById(R.id.titleManager);
            titleManager.setText(aDirectory.getAbsolutePath());

        }
    }
    //список заполнения
    private void fill(File[] files) {
        //Очистить список

        this.directoryEntries.clear();

        //if (this.currentDirectory.getParent() != null)
        //    this.directoryEntries.add("..");

        //добавить все файлы в список

        for (File file : files) {
            this.directoryEntries.add(file.getName());
        }

        //создать адаптер массива, чтобы показать все

        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, R.layout.row, this.directoryEntries);
        this.setListAdapter(directoryList);
    }
    //когда вы нажали на элемент

    //@Override
    //protected void onListItemClick(ListView l, View v, int position, long id) {
        //получить выбранное имя файла

    //    int selectionRowID = position;
    //    String selectedFileString = this.directoryEntries.get(selectionRowID);

        //если мы выберем «..», то вернитесь вверх

     //   if(selectedFileString.equals("..")){
    //        this.upOneLevel();
    //    } else {
            //перейдите к кликному файлу или каталогу, используя browseTo ()
    //        File clickedFile = null;
    //        clickedFile = new File(selectedFileString);
    //        if (clickedFile != null)
     //           this.browseTo(clickedFile);
    //    }
    //}
}
