package com.example.sosproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KakaoLogin1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login1);

        Button btn_example1 = (Button) findViewById(R.id.example1);
        btn_example1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(KakaoLogin1Activity.this, KakaoLogin2Activity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}