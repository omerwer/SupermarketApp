package com.example.supermarketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChosenCounter extends AppCompatActivity {
    Button done_paying;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_counter2);
        Bundle extras = getIntent().getExtras();
        String registry_id = extras.get("registry_id").toString();
        String estimate_start = extras.get("estimate_start").toString();
        String estimate_end = extras.get("estimate_end").toString();

        ((TextView) findViewById(R.id.counter_number)).setText(registry_id);
        ((TextView) findViewById(R.id.start_time)).setText(estimate_start);
        ((TextView) findViewById(R.id.end_time)).setText(estimate_end);


        done_paying = (Button) findViewById(R.id.done_paying);
        done_paying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ThankYou.class);
                startActivity(intent);
            }
        });
    }
}