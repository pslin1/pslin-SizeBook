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
    private EditText dateEditText;
    private EditText nameEditText;
    private EditText neckEditText;
    private EditText bustEditText;
    private EditText chestEditText;
    private EditText waistEditText;
    private EditText hipEditText;
    private EditText inseamEditText;
    private EditText commentEditText;
    //private ArrayList<Record> recordList;
    ArrayList<Record> recordList = new ArrayList<Record>();
    //might add new arraylist here to get rid of NULLPOINTER EXCEPTION
    //java pass by reference vs java pass by value
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
                        nameEditText = (EditText) findViewById(R.id.name_field);
                        String name = nameEditText.getText().toString();

                        Record record = null;
                        record = new Record(name);

                        //taken from http://stackoverflow.com/questions/6290531/check-if-edittext-is-empty
                        //Feb 1, 2017, 16:18
                        dateEditText = (EditText) findViewById(R.id.date_field);
                        String date = dateEditText.getText().toString();
                        if (date.matches("")) {
                            record.setDate("NULL");
                        }
                        else {
                            record.setDate(date);
                        }

                        neckEditText = (EditText) findViewById(R.id.neck_field);
                        String neckText = neckEditText.getText().toString();
                        if (neckText.matches("")) {
                            record.setNeck("NULL");
                        }
                        else {
                            record.setNeck(neckText);
                        }

                        bustEditText = (EditText) findViewById(R.id.bust_field);
                        String bustText = bustEditText.getText().toString();
                        if (bustText.matches("")) {
                            record.setBust("NULL");
                        }
                        else {
                            record.setBust(bustText);
                        }

                        chestEditText = (EditText) findViewById(R.id.chest_field);
                        String chestText = chestEditText.getText().toString();
                        if (chestText.matches("")) {
                            record.setChest("NULL");
                        }
                        else {
                            record.setChest(chestText);
                        }

                        waistEditText = (EditText) findViewById(R.id.waist_field) ;
                        String waistText = waistEditText.getText().toString();
                        if (waistText.matches("")) {
                            record.setWaist("NULL");
                        }
                        else {
                            record.setWaist(waistText);
                        }

                        hipEditText = (EditText) findViewById(R.id.hip_field);
                        String hipText = hipEditText.getText().toString();
                        if (hipText.matches("")) {
                            record.setHip("NULL");
                        }
                        else {
                            record.setHip(hipText);
                        }

                        inseamEditText = (EditText) findViewById(R.id.inseam_field);
                        String inseamText = inseamEditText.getText().toString();
                        if (inseamText.matches("")) {
                            record.setInseam("NULL");
                        }
                        else {
                            record.setInseam(inseamText);
                        }

                        commentEditText = (EditText) findViewById(R.id.comment_field);
                        String commentText = commentEditText.getText().toString();
                        if (commentText.matches("")) {
                            record.setComment("NULL");
                        }
                        else {
                            record.setComment(commentText);
                        }

                        //Float chest = Float.parseFloat();






                        recordList.add(record);
                        adapter.notifyDataSetChanged();

                        saveInFile();
                        setContentView(R.layout.activity_main);
                    }
                });
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                recordList.clear();

                adapter.notifyDataSetChanged();
                deleteFile(FILENAME);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //recordList = new ArrayList<>();
        readFromFile();
        //creates arraylist or else new arrayadapter crashes

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
