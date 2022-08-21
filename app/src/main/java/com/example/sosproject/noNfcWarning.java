package com.example.sosproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class noNfcWarning extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_no_nfc_warning);
    }

    public void mOnClick(View v){
        Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
        startActivity(intent);

        finish();  // 액티비티 닫기
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        // 바깥 레이어 클릭시 닫히는 현상 방지
        return event.getAction() != MotionEvent.ACTION_OUTSIDE;
    }

    @Override
    public void onBackPressed(){
        // 백버튼 동작하지 않도록 처리
    }
}