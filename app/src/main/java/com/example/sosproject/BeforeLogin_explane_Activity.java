package com.example.sosproject;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicator3;

public class BeforeLogin_explane_Activity extends AppCompatActivity {

    private ViewPager mViewPager;
    MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login_explane);
        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);


        //시작하기 버튼 관련 코드
        Button btn_start = (Button) findViewById(R.id.btnStart);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BeforeLogin_explane_Activity.this, BeforeLoginActivity.class);
                startActivity(intent);
                finish();

            }
        });





    }
    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new Fragment_1(), "1");
        adapter.addFragment(new Fragment_2(), "2");
        adapter.addFragment(new Fragment_3(), "3");
        viewPager.setAdapter(adapter);
    }

    //뒤로가기 버튼 클릭 관련 코드
    private final long finishtimeed = 1000;
    private long presstime = 0;
    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - presstime;

        if (0 <= intervalTime && finishtimeed >= intervalTime)
        {
            ActivityCompat.finishAffinity(this);
            System.exit(0);
        }
        else
        {
            presstime = tempTime;
            Toast.makeText(getApplicationContext(), "한번더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }

}