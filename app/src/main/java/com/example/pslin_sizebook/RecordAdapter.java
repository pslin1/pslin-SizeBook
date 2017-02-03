package com.example.pslin_sizebook;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Pierre Lin on 2/2/2017.
 */

public class RecordAdapter extends ArrayAdapter<Record> {
    Context context;


    public RecordAdapter(Context context, int resource, List<Record> objects) {
        super(context, resource, objects);
    }

//    public Record getRecord(int index) {
//        return this.((MyApplication)getApplicationContext()).recordsList
//    }
}
