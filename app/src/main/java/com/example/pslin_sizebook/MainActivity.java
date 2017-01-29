package com.example.pslin_sizebook;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

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
                nameText = (EditText) findViewById(R.id.name_field);
                String name = nameText.getText().toString();

                Record record = null;

                record = new Record(name);

            }
        });


    }

}
