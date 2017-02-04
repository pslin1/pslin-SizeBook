package com.example.pslin_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AddRecord extends AppCompatActivity {
    private EditText dateEditText;
    private EditText nameEditText;
    private EditText neckEditText;
    private EditText bustEditText;
    private EditText chestEditText;
    private EditText waistEditText;
    private EditText hipEditText;
    private EditText inseamEditText;
    private EditText commentEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                nameEditText = (EditText) findViewById(R.id.name_field);
                String name = nameEditText.getText().toString();
                //name field is mandatory
                if (name.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a name!", Toast.LENGTH_LONG).show();
                    return;
                }

                Record record = null;
                record = new Record(name);

                //taken from http://stackoverflow.com/questions/6290531/check-if-edittext-is-empty
                //Feb 1, 2017, 16:18
                //sets attributes of record to whatever is in the EditText
                //if EditText is empty, sets attribute to NULL
                dateEditText = (EditText) findViewById(R.id.date_field);
                String date = dateEditText.getText().toString();
                if (date.matches("")) {
                    record.setDate("NULL");
                } else {
                    record.setDate(date);
                }

                neckEditText = (EditText) findViewById(R.id.neck_field);
                String neckText = neckEditText.getText().toString();
                if (neckText.matches("")) {
                    record.setNeck("NULL");
                } else {
                    neckText = String.format("%.1f", Float.parseFloat(neckText));
                    record.setNeck(neckText);
                }

                bustEditText = (EditText) findViewById(R.id.bust_field);
                String bustText = bustEditText.getText().toString();
                if (bustText.matches("")) {
                    record.setBust("NULL");
                } else {
                    bustText = String.format("%.1f", Float.parseFloat(bustText));
                    record.setBust(bustText);
                }

                chestEditText = (EditText) findViewById(R.id.chest_field);
                String chestText = chestEditText.getText().toString();
                if (chestText.matches("")) {
                    record.setChest("NULL");
                } else {
                    chestText = String.format("%.1f", Float.parseFloat(chestText));
                    record.setChest(chestText);
                }

                waistEditText = (EditText) findViewById(R.id.waist_field);
                String waistText = waistEditText.getText().toString();
                if (waistText.matches("")) {
                    record.setWaist("NULL");
                } else {
                    waistText = String.format("%.1f", Float.parseFloat(waistText));
                    record.setWaist(waistText);
                }

                hipEditText = (EditText) findViewById(R.id.hip_field);
                String hipText = hipEditText.getText().toString();
                if (hipText.matches("")) {
                    record.setHip("NULL");
                } else {
                    hipText = String.format("%.1f", Float.parseFloat(hipText));
                    record.setHip(hipText);
                }

                inseamEditText = (EditText) findViewById(R.id.inseam_field);
                String inseamText = inseamEditText.getText().toString();
                if (inseamText.matches("")) {
                    record.setInseam("NULL");
                } else {
                    inseamText = String.format("%.1f", Float.parseFloat(inseamText));
                    record.setInseam(inseamText);
                }

                commentEditText = (EditText) findViewById(R.id.comment_field);
                String commentText = commentEditText.getText().toString();
                if (commentText.matches("")) {
                    record.setComment("NULL");
                } else {
                    record.setComment(commentText);
                }

                ((MyApplication)getApplicationContext()).recordsList.add(record);
                saveInFile();



            }
        });

    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(((MyApplication)getApplicationContext()).FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(((MyApplication)getApplicationContext()).recordsList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
