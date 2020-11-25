package com.choicely.mylibrary.app;

import android.app.Application;
import android.util.Log;

import com.choicely.mylibrary.dp.RealmHelper;

public class TestLibrary extends Application {
    private final static String TAG = "TestLibrary";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: App start");

        RealmHelper.init(this);
    }
}
