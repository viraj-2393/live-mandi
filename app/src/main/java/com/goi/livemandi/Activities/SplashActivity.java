package com.goi.livemandi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.goi.livemandi.Activities.MainActivity;
import com.goi.livemandi.Activities.NoInternet;
import com.goi.livemandi.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_splash);

        //check if internet is available
        //if yes then go to main page
        //else go to no internet page
        if(isNetworkAvailable()){
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // this code will be executed after 2 seconds
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                }, 3000);
        }
        else{
            startActivity(new Intent(getApplicationContext(), NoInternet.class));
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}