package com.example.pslin_sizebook;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by Pierre Lin on 2/1/2017.
 */
//taken from http://stackoverflow.com/questions/11932178/in-android-how-to-make-array-list-available-to-one-or-more-activities
    //Feb 1, 2017, 19:54
public class MyApplication extends Application {

    public ArrayList<Record> recordsList = null;

    public MyApplication() {
        recordsList = new ArrayList<Record>();
    }
}
