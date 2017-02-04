package com.example.pslin_sizebook;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Pierre Lin on 2/1/2017.
 * This class exists to make recordsList and FILENAME global
 * so that all activities can access them
 */
//taken from http://stackoverflow.com/questions/11932178/in-android-how-to-make-array-list-available-to-one-or-more-activities
    //Feb 1, 2017, 19:54
public class MyApplication extends Application {

    public ArrayList<Record> recordsList = null;
    public static final String FILENAME = "file.sav";

    public MyApplication() {
        recordsList = new ArrayList<Record>();
    }
}
