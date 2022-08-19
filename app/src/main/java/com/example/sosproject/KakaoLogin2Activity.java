package com.example.sosproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class KakaoLogin2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login2);



            Button btn_example2 = (Button) findViewById(R.id.example2);
            btn_example2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    finish();

                }
            });


    }
}