package com.example.vasool_drive;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyApplication extends Application {

    public static FirebaseFirestore firebase;
    public static FirebaseAuth firebaseAuth;
    @Override
    public void onCreate() {
        super.onCreate();

    firebase = FirebaseFirestore.getInstance();
    firebaseAuth =FirebaseAuth.getInstance();
    }
}
