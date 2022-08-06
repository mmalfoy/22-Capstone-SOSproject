package com.example.sosproject;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);



        Button btn_agree = (Button) findViewById(R.id.agreebtn);
        btn_agree.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                //여기서 DB로 개인정보동의 확인정보 전송
                // DB서 정보 받아서 sql lite이용 기기에 저장
                //그리고 메인으로 넘어감
                //중간에 로딩화면 보여주면 좋을 듯..??
                // showDialog(str);
                finish();
            }
        });

        Button btn_disagree = (Button) findViewById(R.id.disagreebtn);
        btn_disagree.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                //여기서 DB로 개인정보동의 확인정보 전송
                // DB서 정보 받아서 sql lite이용 기기에 저장
                //그리고 메인으로 넘어감
                //중간에 로딩화면 보여주면 좋을 듯..??
                // showDialog(str);
                Toast.makeText(getApplicationContext(),"개인정보 동의 후 서비스 이용이 가능합니다.\n앱을 종료합니다.", Toast.LENGTH_SHORT).show();
                //end_program();


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