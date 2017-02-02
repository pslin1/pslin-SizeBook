package com.example.pslin_sizebook;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

    //TODO: add persistent saving system use setters for no mandatory fields in RECORD
    //private static final String FILENAME = "file.sav";
    private ListView oldRecordsList;
    //ArrayList<Record> recordList = new ArrayList<Record>();
    //private ArrayAdapter<Record> adapter;
    //private ArrayList<Record> recordList;
    //might add new arraylist here to get rid of NULLPOINTER EXCEPTION
    //java pass by reference vs java pass by value
    private ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button deleteButton = (Button) findViewById(R.id.deleteRecord);
        //Button addButton = (Button) findViewById(R.id.addRecord);
        oldRecordsList = (ListView) findViewById(R.id.oldRecordsList);

        deleteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                ((MyApplication)getApplicationContext()).recordsList.clear();

                adapter.notifyDataSetChanged();
                deleteFile(((MyApplication)getApplicationContext()).FILENAME);
            }
        });

    }
//    public void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }
    public void addRecord(View view) {
        Intent intent = new Intent(this, AddRecord.class);
        //intent.putExtra("recordListKey", recordList);
        startActivity(intent);
        adapter.notifyDataSetChanged();
        //setResult(RESULT_OK);
        //setContentView(R.layout.record_display);

        //saveInFile();

    }


    private void readFromFile() {
        try {
            FileInputStream fis = openFileInput(((MyApplication)getApplicationContext()).FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

//            new TypeToken<ArrayList<Tweet>>().getType());
            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Record>>(){}.getType();
            ((MyApplication)getApplicationContext()).recordsList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            ((MyApplication)getApplicationContext()).recordsList = new ArrayList<Record>();
            // TODO Auto-generated catch block

        }

    }

//    private void saveInFile() {
//        try {
//            FileOutputStream fos = openFileOutput(((MyApplication)getApplicationContext()).FILENAME,
//                    Context.MODE_PRIVATE);
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
//
//            Gson gson = new Gson();
//            gson.toJson(((MyApplication)getApplicationContext()).recordsList, out);
//            out.flush();
//
//            fos.close();
//        } catch (FileNotFoundException e) {
//            // TODO: Handle the Exception later
//            throw new RuntimeException();
//        } catch (IOException e) {
//            // TODO: Handle the Exception Later
//            throw new RuntimeException();
//        }
//    }


    @Override
    protected void onStart() {
        super.onStart();
        //recordList = new ArrayList<>();
        readFromFile();
        //creates arraylist or else new arrayadapter crashes

        adapter = new ArrayAdapter<Record>(this, R.layout.record_list, ((MyApplication)getApplicationContext()).recordsList);
        oldRecordsList.setAdapter(adapter);
    }


}
