package com.example.pslin_sizebook;


import android.content.Context;
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
    private static final String FILENAME = "file.sav";
    private ListView oldRecordsList;
    private EditText nameText;
    private ArrayList<Record> recordList;
    private ArrayAdapter<Record> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.addRecord);
        oldRecordsList = (ListView) findViewById(R.id.oldRecordsList);
        Button deleteButton = (Button) findViewById(R.id.deleteRecord);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                setContentView(R.layout.record_display);

                Button saveButton = (Button) findViewById(R.id.save);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setResult(RESULT_OK);
                        nameText = (EditText) findViewById(R.id.name_field);
                        String name = nameText.getText().toString();

                        //Record record = null;

                        Record record = new Record(name);

                        recordList.add(record);
                        adapter.notifyDataSetChanged();

                        saveInFile();
                        setContentView(R.layout.activity_main);
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        readFromFile();
        //creates arraylist or else new arrayadapter crashes
        recordList = new ArrayList<Record>();
        adapter = new ArrayAdapter<Record>(this, R.layout.record_list, recordList);
        oldRecordsList.setAdapter(adapter);
    }

    protected void readFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //Jan/31/2017 13:41
            Type listType = new TypeToken<ArrayList<Record>>(){}.getType();
            recordList = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
        }
    }

    protected void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(recordList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
