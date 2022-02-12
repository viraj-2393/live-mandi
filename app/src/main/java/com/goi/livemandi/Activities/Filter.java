package com.goi.livemandi.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.goi.livemandi.R;

public class Filter extends AppCompatActivity {
    EditText district,min_price,max_price;
    AppCompatButton apply_filter;
    Spinner states;
    String[] provinces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//hide the title bar
        setContentView(R.layout.activity_filter);

        provinces = getResources().getStringArray(R.array.states_list);
        states = findViewById(R.id.state_spinner);
        district = findViewById(R.id.district_input);
        min_price = findViewById(R.id.min_price_input);
        max_price = findViewById(R.id.max_price_input);
        apply_filter = findViewById(R.id.apply_filter);

        ArrayAdapter ad= new ArrayAdapter(this,android.R.layout.simple_spinner_item,provinces);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        states.setAdapter(ad);



        apply_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selected_district=null,selected_min_price=null,selected_max_price=null;
                if(!district.getText().toString().equals("")){
                    String temp;
                    temp = district.getText().toString();
                    selected_district = temp.substring(0, 1).toUpperCase() + temp.substring(1);
                }
                if(!min_price.getText().toString().equals("")){
                    selected_min_price = min_price.getText().toString();
                }
                if(!max_price.getText().toString().equals("")){
                    selected_max_price = max_price.getText().toString();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("state",states.getSelectedItem().toString());
                intent.putExtra("district",selected_district);
                intent.putExtra("min_price",selected_min_price);
                intent.putExtra("max_price",selected_max_price);
                startActivity(intent);
            }
        });


    }
}