package com.example.sosproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MyinfoMenuActivity extends AppCompatActivity {
    private String profile;
    //데이터 받아서 setText 해주면 됨

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo_menu);

        Intent intent2 = getIntent();
        profile = intent2.getStringExtra("profile");

        ImageView iv_profile = (ImageView) findViewById(R.id.iv_profile);   //프로필 사진
        TextView text_user_name = (TextView) findViewById(R.id.text_user_name); //이름
        TextView info_name = (TextView) findViewById(R.id.info_name); //이름
        TextView info_number = (TextView) findViewById(R.id.info_number); //전화번호
        TextView info_brith = (TextView) findViewById(R.id.info_brith); //생년월일
        TextView info_mycharge = (TextView) findViewById(R.id.info_mycharge); //나의 탑승요금
        TextView info_income = (TextView) findViewById(R.id.info_income); //소득분위
        TextView info_extracharge = (TextView) findViewById(R.id.info_extracharge); //추가 비용

        // setText 로 할당하기
        Glide.with(this).load(profile).into(iv_profile);
        text_user_name.setText(MainActivity.NAME);
        info_name.setText(MainActivity.NAME);
        info_number.setText(MainActivity.phoneNumber);
        info_brith.setText(MainActivity.birthday);
        info_mycharge.setText(MainActivity.CHARGE);
        info_income.setText(MainActivity.p_userInfo.getIncome_grade());
        info_extracharge.setText("추가비용");

        //뒤로가기
        ImageView back_myinfo_menu = (ImageView)findViewById(R.id.back_myinfo_menu);
        back_myinfo_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}