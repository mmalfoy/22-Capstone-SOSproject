package com.example.sosproject;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//미 구현 : 최신, 과거순 뷰 정렬
//         조회 기간에 따른 데이터 확인
//         상세 조회시 버튼 클릭 처리

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

    RecyclerView recyclerView;
    Adapter_History adapter;

    DBHelper mDBHelper;
    ArrayList<BoardingInfo> arrayList;

    //DB에서 가져온 데이터 개수
    private int SIZE = 10;

    //DB에서 가져올 값
    private int DAY; //몇일에 탔는지(내부 DB로도 구현 가능)
    private int PAY; //그때 얼마 나왔는지
    private int[] WHERE = {0,0}; //어디서 어디로 가는지 (역의 코드 번호 2개의 쌍 만 있으면 됨) ex)신림(0)->숭입(10) == [0,10]
    private int TIME; //몇시에 탔는지(내부 DB로도 구현 가능)
    private int SUM = 0; //총 금액




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mDBHelper = new DBHelper(this, MainActivity.p_id, 1);
        arrayList = mDBHelper.getBoardingList();
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


        //내역 출력 관련 코드
        recyclerView = (RecyclerView)findViewById(R.id.recyceler_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)) ;

        adapter = new Adapter_History();

        //예시, setArrayData로 각각의 값을 넣어주면, 마련된 ArrayList로 값이 저장됨

        for (int i = 0; i < mDBHelper.cnt; i++) {

            String str_day = dayChanger(arrayList.get(i).getDate());//여기에 값으로 몇일이였나 넣어주면 됨(수정)
            adapter.setArrayData(str_day,0);

            PAY = arrayList.get(i).getFare(); // 여기에 각각 얼마였는지 값으로 넣어주면 됨(수정)
            String str_pay = chargeChanger_history(PAY) + " 원";
            adapter.setArrayData(str_pay,1);

            WHERE = new int[] {arrayList.get(i).getStart(),arrayList.get(i).getEnd()}; //여기 뒤에 {, } 내용만 넣어주면 됨(수정)
            String str_where = whereChanger(WHERE);
            adapter.setArrayData(str_where,2);

            // SUM += PAY;
            String str_sum = chargeChanger_history(arrayList.get(i).getTotal_fare()) + " 원";
            adapter.setArrayData(str_sum,3);

            TIME = arrayList.get(i).getTime(); //여기에 (몇시몇분) 값 넣어주면 됨
            String str_time = timeChanger(TIME);
            adapter.setArrayData(str_time,4);

        }

        recyclerView.setAdapter(adapter);






    }

    //날짜 int -> string
    protected String dayChanger(int day) {
        String temp = Integer.toString(day);
        for (int i = 2; temp.length() - i > 0; i += 3) {
            temp = new StringBuilder(temp).insert(temp.length() - i, ".").toString();
        }
        return temp;

    }

    //비용 int -> string
    protected String chargeChanger_history(int charge) {
        String temp = Integer.toString(charge);
        for (int i = 3; temp.length() - i > 0; i += 4) {
            temp = new StringBuilder(temp).insert(temp.length() - i, ",").toString();
        }
        return temp;

    }


    //역사 코드 -> 탑승내역 변환
    protected String whereChanger(int[] WHERE) {
        String temp = "";
        Station station = new Station();
        temp = station.num2name(WHERE[0]) + " -> " + station.num2name(WHERE[1]);
        return temp;
    }

    //비용 int -> string
    protected String timeChanger(int time) {
        String temp = Integer.toString(time);
        temp = new StringBuilder(temp).insert(2, ":").toString();
        return temp;

    }

    private void history_visiable(int num){


    }
    private void search_result(int from, int to){

        return;
    }
}