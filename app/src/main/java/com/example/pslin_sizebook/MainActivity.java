package com.example.pslin_sizebook;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    private ListView oldRecordsList;
    private RecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oldRecordsList = (ListView) findViewById(R.id.oldRecordsList);
        //Taken from http://stackoverflow.com/questions/30711517/how-to-change-the-contents-of-listview-on-item-click
        //Feb 2, 2017, 18:00
        oldRecordsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //mouse click position is passed via bundle, string key "pos"
                //is retrieved later in EditRecord activity
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                editRecord(bundle);
                adapter.notifyDataSetChanged();
            }
        });


    }
    public void onResume() {
        //refreshes counter every time we return to MainActivity
        super.onResume();
        int numRecords = ((MyApplication)getApplicationContext()).recordsList.size();
        TextView textView = (TextView) this.findViewById(R.id.Records);
        String recordsCount = "Number of Records: " + String.valueOf(numRecords);
        textView.setText(recordsCount);
    }
    //editRecord called whenever an item from oldRecordsList is clicked
    public void editRecord( Bundle bundle) {
        Intent intent = new Intent(this, EditRecord.class);
        intent.putExtras(bundle);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }
    //addRecord called when add record button clicked
    //no listener, method called when button clicked
    //on click in activity_main.xml
    public void addRecord(View view) {
        Intent intent = new Intent(this, AddRecord.class);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }


    private void readFromFile() {
        try {
            FileInputStream fis = openFileInput(((MyApplication)getApplicationContext()).FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //new TypeToken<ArrayList<Tweet>>().getType());
            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Record>>(){}.getType();
            ((MyApplication)getApplicationContext()).recordsList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            ((MyApplication)getApplicationContext()).recordsList = new ArrayList<Record>();
            // TODO Auto-generated catch block

        }

    }

    //creates adapter when app first starts
    @Override
    protected void onStart() {
        super.onStart();
        readFromFile();

        adapter = new RecordAdapter(this, R.layout.record_list, ((MyApplication)getApplicationContext()).recordsList);
        oldRecordsList.setAdapter(adapter);
    }


}
