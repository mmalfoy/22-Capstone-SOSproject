package com.example.sosproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MyinfoMenuActivity extends AppCompatActivity {

    //데이터 받아서 setText 해주면 됨

    TextView info_name = (TextView) findViewById(R.id.info_name); //이름
    TextView info_number = (TextView) findViewById(R.id.info_number); //전화번호
    TextView info_brith = (TextView) findViewById(R.id.info_brith); //생년월일
    TextView info_mycharge = (TextView) findViewById(R.id.info_mycharge); //나의 탑승요금
    TextView info_income = (TextView) findViewById(R.id.info_income); //소득분위
    TextView info_extracharge = (TextView) findViewById(R.id.info_extracharge); //추가 비용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo_menu);
    }
}