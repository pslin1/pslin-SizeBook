package com.example.pslin_sizebook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class EditRecord extends AppCompatActivity {
    public Integer id;
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("pos");
        Button deleteButton = (Button) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                //taken from http://stackoverflow.com/questions/3579981/why-is-my-arraylist-removeid-call-not-working
                //Feb 2, 2017, 19:19
                ((MyApplication)getApplicationContext()).recordsList.remove(((MyApplication)getApplicationContext()).recordsList.get(id));
                saveInFile();
            }
        });
        Record record = ((MyApplication)getApplicationContext()).recordsList.get(id);
        EditText nameText = (EditText) findViewById(R.id.name_field_edit);
        nameText.setHint(record.getName());

        EditText dateText = (EditText) findViewById(R.id.date_field_edit);
        dateText.setHint(record.getDate());

        EditText neckText = (EditText) findViewById(R.id.neck_field_edit);
        neckText.setHint(record.getNeck());

        EditText bustText = (EditText) findViewById(R.id.bust_field_edit);
        bustText.setHint(record.getBust());

        EditText chestText = (EditText) findViewById(R.id.chest_field_edit);
        chestText.setHint(record.getChest());

        EditText waistText = (EditText) findViewById(R.id.waist_field_edit);
        waistText.setHint(record.getWaist());

        EditText hipText = (EditText) findViewById(R.id.hip_field_edit);
        hipText.setHint(record.getHip());

        EditText inseamText = (EditText) findViewById(R.id.inseam_field_edit);
        inseamText.setHint(record.getInseam());

        EditText commentText = (EditText) findViewById(R.id.comment_field_edit);
        commentText.setHint(record.getComment());
        Button saveChangesButton = (Button) findViewById(R.id.saveChanges);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record record = ((MyApplication)getApplicationContext()).recordsList.get(id);
                dateEditText = (EditText) findViewById(R.id.date_field_edit);
                String date = dateEditText.getText().toString();
                //taken from http://stackoverflow.com/questions/14721397/checking-if-a-string-is-empty-or-null-in-java
                //Feb 2, 2017, 20:22
                if (date != null && !date.isEmpty()) {
                    record.setDate(date);
                }

                nameEditText = (EditText) findViewById(R.id.name_field_edit);
                String name = nameEditText.getText().toString();
                if(name != null && !name.isEmpty()) {
                    record.setName(name);
                }

                neckEditText = (EditText) findViewById(R.id.neck_field_edit);
                String neck = neckEditText.getText().toString();
                if(neck != null && !neck.isEmpty()) {
                    record.setNeck(neck);
                }

                bustEditText = (EditText) findViewById(R.id.bust_field_edit);
                String bust = bustEditText.getText().toString();
                if(bust != null && !bust.isEmpty()) {
                    record.setBust(bust);
                }

                chestEditText = (EditText) findViewById(R.id.chest_field_edit);
                String chest = chestEditText.getText().toString();
                if(chest != null && !chest.isEmpty()) {
                    record.setChest(chest);
                }

                waistEditText = (EditText) findViewById(R.id.waist_field_edit);
                String waist = waistEditText.getText().toString();
                if(waist != null && !waist.isEmpty()) {
                    record.setWaist(waist);
                }

                hipEditText = (EditText) findViewById(R.id.hip_field_edit);
                String hip = hipEditText.getText().toString();
                if(hip != null && !hip.isEmpty()) {
                    record.setHip(hip);
                }

                inseamEditText = (EditText) findViewById(R.id.inseam_field_edit);
                String inseam = inseamEditText.getText().toString();
                if(inseam != null && !inseam.isEmpty()) {
                    record.setInseam(inseam);
                }

                commentEditText = (EditText) findViewById(R.id.comment_field_edit);
                String comment = commentEditText.getText().toString();
                if(comment != null && !comment.isEmpty()) {
                    record.setComment(comment);
                }
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
