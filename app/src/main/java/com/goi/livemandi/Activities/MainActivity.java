package com.goi.livemandi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.goi.livemandi.API.GovApi;
import com.goi.livemandi.Adapter.MandiDataAdapter;
import com.goi.livemandi.Models.Root;
import com.goi.livemandi.R;
import com.goi.livemandi.Utils.AppConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {
    LinearLayout filter_holder;
    private long pressedTime;
    LinearLayout prg_bar_holder,no_data_holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);

        prg_bar_holder = findViewById(R.id.progress_bar_holder);
        no_data_holder = findViewById(R.id.no_data_holder);

        //apply filter
        filter_holder = findViewById(R.id.filter_holder);
        filter_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Filter.class));
            }
        });

        //get all mandi data
        if(isNetworkAvailable()){
            getMandiData();
        }else{
            startActivity(new Intent(getApplicationContext(),NoInternet.class));
        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void getMandiData(){
        String state = getIntent().getStringExtra("state");
        String district = getIntent().getStringExtra("district");
        String min_price = getIntent().getStringExtra("min_price");
        String max_price = getIntent().getStringExtra("max_price");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GovApi service = retrofit.create(GovApi.class);

        Call<Root> call = service.getMandiData(AppConstants.API_KEY,"json",0,10,state,district,min_price,max_price);
        call.enqueue(new Callback<Root>() {

            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                Root serverResponse = response.body();

                if (response.isSuccessful() && response.body() != null && serverResponse.getRecords() != null && serverResponse.getRecords().size() != 0){
                    prg_bar_holder.setVisibility(View.GONE);
                    // get the reference of RecyclerView
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.live_mandi_data_holder);
                    recyclerView.setVisibility(View.VISIBLE);
                    // set a LinearLayoutManager with default vertical orientation
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    linearLayoutManager.setReverseLayout(false);
                    //  call the constructor of CustomAdapter to send the reference and data to Adapter
                    MandiDataAdapter customAdapter = new MandiDataAdapter(serverResponse, getApplicationContext(),MainActivity.this);
                    recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
                }
                else{
                    prg_bar_holder.setVisibility(View.GONE);
                    no_data_holder.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }

        });
    }


    @Override
    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            this.finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}