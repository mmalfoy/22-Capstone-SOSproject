package com.example.sosproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


@RequiresApi(api = Build.VERSION_CODES.O)
public class SQLiteTestActivity extends AppCompatActivity {

    DBHelper mDBHelper;
    ArrayList<BoardingInfo> arrayList;
    boolean isDown = false;

    //sqlLite 들어갈 데이터
    private String TOTAL = "10000";    // 총 요금
    private String MOMENT = "1000";    // 현재 요금
    private String YY;      // 년월일(int로 변경 가능 - TODAY)
    private String TT;        // 시분초(int로 변경 가능 - TIME)
    private int WHERE;     //역 위치

    //승차 역 코드
    private int STN_CODE1 = 1;
    //하차 역 코드
    private int STN_CODE2 = 10;

    //현재 날짜
    private int TODAY;
    //현재 시각
    private int TIME;

    private String temp;
    private String temp2;


    // 현재 날짜 구하기 -> 결과로 YY, TT 로 값 들어감.
    LocalDateTime now = LocalDateTime.now();
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyyMMddhhmmss");


    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mDBHelper = new DBHelper(this, MainActivity.p_id, 1);

        arrayList = mDBHelper.getBoardingList();

        setContentView(R.layout.activity_sqlite_test);

        TextView day_test = (TextView)findViewById(R.id.history_day_text);
        TextView time_test = (TextView)findViewById(R.id.history_time_text);
        Button btn_test = (Button) findViewById(R.id.history_test_button);
        day_test.setText(Integer.toString(arrayList.get(0).getTime()));
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDown){
                    //버튼클릭 -> nfc 태그 승차 가정
                    btn_test.setText("NFC 승차");
                    Toast.makeText(getApplicationContext(), "승하차 db 데이터 추가", Toast.LENGTH_SHORT).show();
                    day_test.setText("탑승 전");
                    time_test.setText("탑승 전");
                    isDown = false;

                    //승차역 코드 저장(1122 식으로 저장해서, 나중에 (/100)의 몫으로 뽑아옴
                    WHERE = 100*STN_CODE1;

                }else{
                    //버튼클릭 -> nfc 태그 하차 가정
                    btn_test.setText("NFC 하차");

                    //현재 날짜, 시각 관련 코드
                    temp = getTime();
                    temp2 = temp.substring(0,8); //날짜
                    temp = temp.substring(8);    //시각

                    TODAY = Integer.parseInt(temp2);
                    TIME  = Integer.parseInt(temp);

                    YY = Integer.toString(TODAY);
                    TT = Integer.toString(TIME);

                    //하차역 코드 저장(1122 식으로 저장해서, 나중에 (/100)의 몫으로 뽑아옴
                    WHERE += STN_CODE2;

                    //데이터 확인 코드(textView)
                    day_test.setText(YY);
                    time_test.setText(TT);



                    isDown = true;
                }

            }
        });
    }



}