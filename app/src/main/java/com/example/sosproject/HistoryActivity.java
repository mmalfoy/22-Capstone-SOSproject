package com.example.sosproject;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    private TextView text_charge;
    private String CHARGE;
    private Button btn_week;
    private Button btn_1month;
    private Button btn_6month;
    private Button btn_search;
    private int from,to;
    private LinearLayout btn_sort;
    private ImageView image_sort;
    private TextView text_sort;
    private int sort_check = 0;

    private LinearLayout history_1;
    private LinearLayout history_2;
    private LinearLayout history_3;
    private LinearLayout history_4;
    private LinearLayout history_5;
    private LinearLayout history_6;
    private LinearLayout history_7;
    private LinearLayout history_8;
    private LinearLayout history_9;
    private LinearLayout history_10;
    private LinearLayout history_11;
    private LinearLayout history_12;
    private LinearLayout history_13;
    private LinearLayout history_14;
    private LinearLayout history_15;
    private LinearLayout history_16;
    private LinearLayout history_17;
    private LinearLayout history_18;
    private LinearLayout history_19;
    private LinearLayout history_20;
    private LinearLayout history_21;
    private LinearLayout history_22;
    private LinearLayout history_23;
    private LinearLayout history_24;
    private LinearLayout history_25;
    private LinearLayout history_26;
    private LinearLayout history_27;
    private LinearLayout history_28;
    private LinearLayout history_29;
    private LinearLayout history_30;


    private TextView day_1;
    private TextView pay_1;
    private TextView where_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //뒤로가기
        ImageView back_card_menu = (ImageView)findViewById(R.id.back_history);
        back_card_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        //뷰의 요금 관련 코드
        //내부 DB저장해서, 불러와서 가격 다시 계산해주는 함수 필요함
        CHARGE = MainActivity.CHARGE;
        text_charge = (TextView) findViewById(R.id.text_charge);
        text_charge.setText(CHARGE);


        //기간 설정 관련 코드
        //상세조회시, 사용자에게 입력받아 처리하는 코드 필요함
        //from - 시작, to - 끝
        btn_week = (Button)findViewById(R.id.btn_week);
        btn_week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                from = 0;//오늘부터
                to = 7;//1주일
                search_result(from, to);

            }
        });

        btn_1month = (Button)findViewById(R.id.btn_month);
        btn_1month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from = 0;//오늘부터
                to = 31;//1달(시간 import해서 사용)
                search_result(from, to);

            }
        });

        btn_6month = (Button)findViewById(R.id.btn_sixmonth);
        btn_6month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                from = 0;//오늘부터
                to = 0;//6개월
                search_result(from, to);
            }
        });

        btn_search = (Button)findViewById(R.id.btn_specialize);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                from = 0;//언제부터
                to = 0;//언제까지
                //입력받기
                search_result(from, to);

            }
        });

        // 최신순,과거순 정렬
        image_sort = (ImageView) findViewById(R.id.image_sort);
        text_sort = (TextView) findViewById(R.id.text_sort);

        btn_sort = (LinearLayout) findViewById(R.id.btn_sort);
        btn_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sort_check == 0){
                    text_sort.setText("과거순");
                    image_sort.setImageResource(R.drawable.ic_baseline_arrow_up_24);
                    sort_check = 1;
                }else{
                    text_sort.setText("최신순");
                    image_sort.setImageResource(R.drawable.ic_baseline_arrow_down_24);
                    sort_check = 0;
                }

            }
        });

        history_1 = (LinearLayout) findViewById(R.id.view_history1);

        pay_1 = (TextView) findViewById(R.id.pay_1);
        where_1 = (TextView) findViewById(R.id.where_1);
        day_1 = (TextView) findViewById(R.id.day_1);




    }

    private void history_visiable(int num){


    }
    private void search_result(int from, int to){

        return;
    }
}