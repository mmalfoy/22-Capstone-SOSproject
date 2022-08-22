package com.example.sosproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class KakaoLogin2Activity extends AppCompatActivity {
    public static String strNick, strProfileImg;
    public static String IdentificationNumber;    // 주민등록번호
    public static String PhoneNumber;             // 휴대폰 번호
    int a=0, b=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login2);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strProfileImg = intent.getStringExtra("profileImg");
        MainActivity.NAME = strNick;

        Log.e("KaKao", MainActivity.NAME);
        TextView tv_nick = (TextView) findViewById(R.id.tv_nickName);      // 이름
        ImageView iv_profile = (ImageView) findViewById(R.id.iv_profile);   // 프로필 사진
        EditText et_number = (EditText) findViewById(R.id.et_number);      // 생년월일
        EditText et_phone = (EditText) findViewById(R.id.et_phone);        // 휴대폰 번호
        Button btn_start = (Button) findViewById(R.id.btn_start);        // 앱 시작하기
        Button btn_logout = (Button) findViewById(R.id.btn_logout);      // 앱 로그아웃

        //닉네임 set
        tv_nick.setText(strNick);
        //프로필 이미지 사진 set
        Glide.with(this).load(strProfileImg).into(iv_profile);

        btn_start.setEnabled(false);

        et_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {              // 생년월일 공란일 때
                    btn_start.setEnabled(false);
                }
                else if (s.length() != 6){          // 생년월일이 6자리가 아닐 때
                    btn_start.setEnabled(false);
                }
                else {
                    a+=1;
                }

                if ((a+b) == 2)
                {
                    btn_start.setBackgroundColor(Color.parseColor("#B4E5FF"));
                    btn_start.setEnabled(true);
                }
                else {
                    btn_start.setEnabled(false);
                }
            }
        });

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {                        // 전화번호 공란일 때
                    btn_start.setEnabled(false);
                }
                else if (s.length() != 11) {                  // 전화번호가 11자리가 아닐 때
                    btn_start.setEnabled(false);
                }
                else {
                    b+=1;
                }

                if ((a+b) == 2)
                {
                    btn_start.setBackgroundColor(Color.parseColor("#B4E5FF"));
                    btn_start.setEnabled(true);
                }
                else {
                    btn_start.setEnabled(false);
                }
            }
        });

        //앱 시작하기 버튼을 눌렀을 때
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IdentificationNumber = et_number.getText().toString(); // 생년월일 저장
                PhoneNumber = et_phone.getText().toString();            // 전화번호 저장
                Toast.makeText(KakaoLogin2Activity.this, "추가정보 입력이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                Intent nintent = new Intent(KakaoLogin2Activity.this, MainActivity.class);
//                nintent.putExtra("name", strNick);
//                Log.e("KaKao", strNick);
//                startActivity(nintent);

                finish();   //현재 액티비티 종료
            }
        });

        //카카오 API에 로그아웃 요청
        btn_logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        // 로그아웃 성공시 수행하는 지점
                        finish(); // 현재 액티비티 종료
                    }
                });
            }
        });
    }
}