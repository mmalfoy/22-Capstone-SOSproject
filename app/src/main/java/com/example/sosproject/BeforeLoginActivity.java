package com.example.sosproject;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
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

import java.security.MessageDigest;
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

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import org.w3c.dom.Text;

public class BeforeLoginActivity extends AppCompatActivity {
    int a = 0,b = 0,c = 0;
    private ISessionCallback mSessionCallback;
    public static String phonenumber = "default";
    public static String birthday = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);

        Button btn_rulePage = (Button) findViewById(R.id.rule_page_btn);
        Button btn_kakaoLogin = (Button) findViewById(R.id.kakao_login_btn);
        ImageButton btn_firstcheck = (ImageButton) findViewById(R.id.first_check);
        ImageButton btn_secondcheck = (ImageButton) findViewById(R.id.second_check);
        ImageButton btn_thirdcheck = (ImageButton) findViewById(R.id.third_check);
        TextView tv_first = (TextView) findViewById(R.id.tv_first);
        TextView tv_second = (TextView) findViewById(R.id.tv_second);
        TextView tv_third = (TextView) findViewById(R.id.tv_third);

        btn_firstcheck.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(a == 0){
                    btn_rulePage.setVisibility(View.VISIBLE);
                    btn_rulePage.setClickable(true);

                    //btn_kakaoLogin.setVisibility(View.INVISIBLE);
                    tv_second.setBackgroundResource(R.drawable.deepblue_radius);
                    //tv_first.setBackgroundResource(R.drawable.lightgray_radius);

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
                    //btn_kakaoLogin.setVisibility(View.VISIBLE);
                    tv_second.setBackgroundResource(R.drawable.lightgray_radius);
                    tv_first.setBackgroundResource(R.drawable.deepblue_radius);
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
                        //btn_kakaoLogin.setVisibility(View.INVISIBLE);
                        tv_second.setBackgroundResource(R.drawable.deepblue_radius);
                        //tv_first.setBackgroundResource(R.drawable.lightgray_radius);

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
                        //btn_kakaoLogin.setVisibility(View.VISIBLE);
                        tv_second.setBackgroundResource(R.drawable.lightgray_radius);
                        tv_first.setBackgroundResource(R.drawable.deepblue_radius);
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
                        // btn_kakaoLogin.setVisibility(View.INVISIBLE);
                        tv_second.setBackgroundResource(R.drawable.deepblue_radius);
                        //tv_first.setBackgroundResource(R.drawable.lightgray_radius);

                        btn_firstcheck.setImageResource(R.drawable.ic_baseline_check_circle_24);
                        a = 1;
                    }
                }else {
                    btn_thirdcheck.setImageResource(R.drawable.ic_baseline_beforecheck_24);
                    c = 0;
                    if(a == 1){
                        btn_firstcheck.setImageResource(R.drawable.ic_baseline_check_circle_outline_24);
                        a = 0;
                        btn_rulePage.setVisibility(View.INVISIBLE);
                        btn_rulePage.setClickable(false);
                        //btn_kakaoLogin.setVisibility(View.VISIBLE);
                        tv_second.setBackgroundResource(R.drawable.lightgray_radius);
                        tv_first.setBackgroundResource(R.drawable.deepblue_radius);
                    }
                }
            }
        });

        btn_rulePage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeforeLoginActivity.this,KakaoLogin2Activity.class);
                startActivity(intent);
                btn_firstcheck.setClickable(false);
                btn_secondcheck.setClickable(false);
                btn_thirdcheck.setClickable(false);
                tv_third.setBackgroundResource(R.drawable.deepblue_radius);
                btn_kakaoLogin.setVisibility(View.INVISIBLE);
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

        //카카오 로그인 기능 시작
        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                // 로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        // 로그인 요청했는데 실패했을 때
                        Toast.makeText(BeforeLoginActivity.this, "로그인 도중에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        // 세션이 닫힘..
                        Toast.makeText(BeforeLoginActivity.this, "세션이 닫혔습니다.. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        // 로그인 성공
                       // Intent intent = new Intent(BeforeLoginActivity.this, KakaoLogin2Activity.class);
                        Intent intent = new Intent(BeforeLoginActivity.this, MainActivity.class);
                        intent.putExtra("name", result.getKakaoAccount().getProfile().getNickname());
                        intent.putExtra("profileImg", result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra("birth", result.getKakaoAccount().getBirthday());
                        startActivity(intent);

                        Toast.makeText(BeforeLoginActivity.this, "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {
                Toast.makeText(BeforeLoginActivity.this, "세션 열기에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }
        };

        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen(); //세션 유지
        //getAppKeyHash();
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

    private void getAppKeyHash()
    {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.e("Hash key", something);
            }
        } catch (Exception e) {
            Log.e("name not found", e.toString());
        }
    }

    // 카카오에서 정보를 가져온 Result값을 받는 메서드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    // mainActivity가 죽었을 때 객체의 해제를 원할하게 해주는 메서드.
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }
}