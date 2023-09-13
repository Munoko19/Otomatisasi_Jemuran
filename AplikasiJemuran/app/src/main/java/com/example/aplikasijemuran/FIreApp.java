package com.example.aplikasijemuran;

import android.app.Application;

import com.firebase.client.Firebase;

public class FIreApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
