package com.example.sosproject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        // Log.e("transfer Intent", "")
        ImageView back_card_menu = (ImageView)findViewById(R.id.back_history);
        Intent intent = getIntent();
        String total = intent.getStringExtra("TOTAL");

        TextView tv_h_total_fare = (TextView) findViewById(R.id.history_total_fare);
        tv_h_total_fare.setText(""+total);

        back_card_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}