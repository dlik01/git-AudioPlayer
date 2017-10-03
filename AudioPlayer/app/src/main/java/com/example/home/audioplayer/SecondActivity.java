package com.example.home.audioplayer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by home on 25.09.2017.
 */

public class SecondActivity extends ListActivity {

    private List<String> directoryEntries = new ArrayList<String>();
    private File currentDirectory = new File("/");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        //��������� � �������� �������
        browseTo(new File("/sdcard/Music"));
    }

    //��������� � ������������ �������
    private void upOneLevel(){
        if(this.currentDirectory.getParent() != null) {
            this.browseTo(this.currentDirectory.getParentFile());
        }
    }

    //������������� ���� ��� �������
    private void browseTo(final File aDirectory){
        //���� �� ����� ����������� �������
        if (aDirectory.isDirectory()){
            //��������� ������ ������� �� ����� ��������
            this.currentDirectory = aDirectory;
            fill(aDirectory.listFiles());
        }
    }
    //������ ����������
    private void fill(File[] files) {
        //�������� ������
        this.directoryEntries.clear();
        //�������� ��� ����� � ������
        for (File file : files) {
            this.directoryEntries.add(file.getAbsolutePath());
        }
        //������� ������� �������, ����� �������� ���
        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(this, R.layout.row, this.directoryEntries);
        this.setListAdapter(directoryList);
    }
    //����� �� ������ �� �������

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //�������� ��������� ��� �����

        int selectionRowID = position;
        String selectedFileString = this.directoryEntries.get(selectionRowID);

        //���� �� ������� �..�, �� ��������� �����

        if(selectedFileString.equals("..")){
            this.upOneLevel();
        } else {
            //��������� � �������� ����� ��� ��������, ��������� browseTo ()
            File clickedFile = null;
            clickedFile = new File(selectedFileString);
            if (clickedFile != null)
                this.browseTo(clickedFile);
        }
    }
}
