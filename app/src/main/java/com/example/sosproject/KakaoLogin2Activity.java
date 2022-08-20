package com.example.sosproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

public class KakaoLogin2Activity extends AppCompatActivity {
    private String strNick, strProfileImg;
    private EditText et_number;
    public static String IdentificationNumber;    // 주민등록번호

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakao_login2);

        Intent intent = getIntent();
        strNick = intent.getStringExtra("name");
        strProfileImg = intent.getStringExtra("profileImg");

        TextView tv_nick = findViewById(R.id.tv_nickName);
        ImageView iv_profile = findViewById(R.id.iv_profile);
        EditText et_number = findViewById(R.id.et_number);

        Button btn_start = findViewById(R.id.btn_start);
        Button btn_logout = findViewById(R.id.btn_logout);

        //닉네임 set
        tv_nick.setText(strNick);
        //프로필 이미지 사진 set
        Glide.with(this).load(strProfileImg).into(iv_profile);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdentificationNumber = et_number.getText().toString(); // 주민등록번호 입력받은 값을 저장
//                Intent intent = new Intent(KakaoLogin2Activity.this, SubActivity.class);
//                intent.putExtra("IdentificationNumber", IdentificationNumber);
//                startActivity(intent); // 액티비티 이동
                finish();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener()
        {
            //카카오 API에 로그아웃 요청
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