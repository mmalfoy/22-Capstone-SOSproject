package com.example.sosproject;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView.OnEditorActionListener; //OnEditorActionListener import하기
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class BeforeLoginActivity extends AppCompatActivity {

    int a = 0,b = 0,c = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);

        Button btn_rulePage = (Button) findViewById(R.id.rule_page_btn);
        ImageButton btn_firstcheck = (ImageButton) findViewById(R.id.first_check);
        ImageButton btn_secondcheck = (ImageButton) findViewById(R.id.second_check);
        ImageButton btn_thirdcheck = (ImageButton) findViewById(R.id.third_check);


        btn_rulePage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_firstcheck.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a == 0){
                    btn_rulePage.setVisibility(View.VISIBLE);
                    btn_rulePage.setClickable(true);

                    btn_firstcheck.setImageResource(R.drawable.ic_baseline_check_circle_24);
                    a = 1;

                    btn_secondcheck.setImageResource(R.drawable.ic_baseline_check_24);
                    b = 1;

                    btn_thirdcheck.setImageResource(R.drawable.ic_baseline_check_24);
                    c = 1;
                }else{
                    btn_firstcheck.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                    a = 0;

                    btn_secondcheck.setImageResource(R.drawable.ic_baseline_beforecheck_24);
                    b = 0;

                    btn_thirdcheck.setImageResource(R.drawable.ic_baseline_beforecheck_24);
                    c = 0;

                    btn_rulePage.setVisibility(View.INVISIBLE);
                    btn_rulePage.setClickable(false);

                }

            }
        });

        btn_secondcheck.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b == 0){
                    btn_secondcheck.setImageResource(R.drawable.ic_baseline_check_24);
                    b = 1;
                    if(c == 1){
                        btn_rulePage.setVisibility(View.VISIBLE);
                        btn_rulePage.setClickable(true);

                        btn_firstcheck.setImageResource(R.drawable.ic_baseline_check_circle_24);
                        a = 1;
                    }

                }else{
                    btn_secondcheck.setImageResource(R.drawable.ic_baseline_beforecheck_24);
                    b = 0;
                    if(a == 1){
                        btn_firstcheck.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                        a = 0;
                        btn_rulePage.setVisibility(View.INVISIBLE);
                        btn_rulePage.setClickable(false);
                    }
                }

            }
        });

        btn_thirdcheck.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c == 0){
                    btn_thirdcheck.setImageResource(R.drawable.ic_baseline_check_24);
                    c = 1;
                    if(b ==1 ){
                        btn_rulePage.setVisibility(View.VISIBLE);
                        btn_rulePage.setClickable(true);

                        btn_firstcheck.setImageResource(R.drawable.ic_baseline_check_circle_24);
                        a = 1;
                    }
                }else{
                    btn_thirdcheck.setImageResource(R.drawable.ic_baseline_beforecheck_24);
                    c = 0;
                    if(a == 1){
                        btn_firstcheck.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                        a = 0;
                        btn_rulePage.setVisibility(View.INVISIBLE);
                        btn_rulePage.setClickable(false);
                    }
                }
            }
        });

        ImageButton btn_secondright = (ImageButton) findViewById(R.id.second_right);
        btn_secondright.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeLoginActivity.this,Agree_1.class);
                startActivity(intent);


            }
        });

        ImageButton btn_thirdright = (ImageButton) findViewById(R.id.third_right);
        btn_thirdright.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeLoginActivity.this,Agree_2.class);
                startActivity(intent);


            }
        });





    }


    private void end_program(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        moveTaskToBack(true);
        finishAndRemoveTask(); // 액티비티 종료 + 태스크 리스트에서 지우기
        android.os.Process.killProcess(android.os.Process.myPid()); // 앱 프로세스 종료
    }


}