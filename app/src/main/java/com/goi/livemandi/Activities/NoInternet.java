package com.goi.livemandi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.goi.livemandi.R;

public class NoInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//hide the title bar
        setContentView(R.layout.activity_no_internet);
    }
}