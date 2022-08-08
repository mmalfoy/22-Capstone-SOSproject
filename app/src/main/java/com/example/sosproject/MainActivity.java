package com.example.sosproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//1. 회원정보 동의 한 정보 기기에 어떻게 저장..?
public class MainActivity extends AppCompatActivity {//extends Calender{

    private ImageButton card;
    private RelativeLayout cardLayout;
    private Animation cardAnim;
    private Animation nfcAnim;
    private Animation handAnim;
    LinearLayout main_activity;
    LinearLayout nfcReader;
    ImageView hand;
    private int cardcounter = 0;
    private int handcounter = 0;
    private DrawerLayout drawerLayout;
    private View drawerView;
    private DrawerLayout drawerLayout2;
    private View drawerView2;

    //DB 테스트
    protected PeopleDBHelper dbHelper;
    private TextView test;
    TextView personal_name;
    private String NAME = "Defalut";
    private String CHARGE = "10,000";
    static final String DB_NAME = "personal.db";


    // Retrofit (Spring server 연결부)
    static final String p_id = "990422";
    Retrofit retrofit;
    RetrofitAPI retrofitApi;
    Call<List<UserInfo>> call;
    TextView textView;
    UserInfo p_userInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //로딩화면 관련 코드
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        // server 연결 코드
        retrofitApi = RetrofitClientInstance.getRetrofitInstance().create(RetrofitAPI.class);
        call = retrofitApi.getMember();

        call.enqueue(new Callback<List<UserInfo>>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                if(response.isSuccessful()){
                    textView = (TextView) findViewById(R.id.txt_json);

                    //p_userInfo = response.body().stream().filter(id -> p_id.equals(response.getId())).findAny().orElse(null);


                    //textView.setText(Integer.toString(idx));

                }else{
                    Log.e("MainActivity","response but fail");
                }
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                Log.e("MainActivity!!!", "fail");
                t.printStackTrace();
            }
        });

        //내부 DB관련 코드

        test = findViewById(R.id.txt_json);
        personal_name = findViewById(R.id.personal_name);

        dbHelper = new PeopleDBHelper(this,DB_NAME,1);

        //dbHelper.insertRecord("손현석", 1998);

        int isit = printTabletest("하이");
        Log.d("TAG","isit : "+isit);


        //앱 처음 실행시 코드
        if(isit != 1) {

            intent = new Intent(this,BeforeLogin_explane_Activity.class);
            startActivity(intent);
        }

        main_activity = (LinearLayout)findViewById(R.id.main_activity);
        main_activity.setVisibility(View.VISIBLE);


        //menu 관련 코드
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");


        setSupportActionBar(toolbar);

        new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withMenuLayout(R.layout.menu)
                .inject();

        // 메뉴의 이름, 요금 변경관련 코드
        // 이름은 NAME으로 받음(print함수에 전달되는 thename파라미터값이 NAME임)
        // 요금은 CHARGE로 받음(이건 만원으로 해둠)
        //nameChanger(name); 필요하면 함수 만들기
        chargeChanger(10000); // -> 10,000

        class NewRunnable implements Runnable{
            int n = 1;
            TextView personal_name = (TextView)findViewById(R.id.personal_name);
            TextView personal_charge = (TextView) findViewById(R.id.menu_charge);
            @Override
            public void run(){
                while(n>0){
                    String name = NAME;
                    personal_name.setText(NAME);
                    personal_charge.setText(CHARGE);
                    n--;
                }
            }
        }
        NewRunnable nr = new NewRunnable();
        Thread t = new Thread(nr);
        t.start();



        LinearLayout btn_cardMenu = (LinearLayout)findViewById(R.id.btn_cardMenu);
        btn_cardMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,CardMenuActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout btn_history = (LinearLayout)findViewById(R.id.btn_history);
        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout btn_gps = (LinearLayout)findViewById(R.id.btn_gps);
        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "지하철 노선도 확인",Toast.LENGTH_SHORT).show();

            }
        });





        //setting 관련 코드
        ImageButton btn_setting = (ImageButton) findViewById(R.id.btn_setting);
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein_right,R.anim.stay);

            }
        });




        //main NFC태그 관련 코드
        hand = (ImageView)findViewById(R.id.hand);
        nfcReader = (LinearLayout)findViewById(R.id.nfcReader);
        card = (ImageButton)findViewById(R.id.personal_Card);

        card.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(cardcounter == 0) {
                    nfcReader.setVisibility(View.VISIBLE);

                    cardAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.card_anim); //에니메이션설정파일
                    card.startAnimation(cardAnim);

                    nfcAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.nfc_anim); //에니메이션설정파일
                    nfcReader.startAnimation(nfcAnim);

                    card.setClickable(false);
                    handcounter = 1;

                    if(handcounter == 1){

                        handAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.hand_anim); //에니메이션설정파일
                        hand.startAnimation(handAnim);

                    }



                    cardcounter = 1;
                }
            }

        });

        cardLayout = (RelativeLayout)findViewById(R.id.cardLayout);
        cardLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(cardcounter == 1) {

                    cardAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.card2_anim); //에니메이션설정파일
                    card.startAnimation(cardAnim);

                    nfcAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.nfc2_anim); //에니메이션설정파일
                    nfcReader.startAnimation(nfcAnim);


                    nfcReader.setVisibility(View.INVISIBLE);
                    card.setClickable(true);
                    cardcounter = 0;
                    handcounter = 0;
                }
            }

            });



    }

    //DB 테스트
    private void dbDataDelete(String target){

        String sql = "delete * from mycontacts where name="+"'"+target+"'";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(sql);

    }

    protected int printTabletest(String thename) {


        int isit = 0;
        Cursor cursor = dbHelper.readRecordOrderByAge();
        String result = "";
        String person_name = "";
        String name = "";

        while (cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow(PersonalDB.PeopleEntry._ID));
            name = cursor.getString(cursor.getColumnIndexOrThrow(PersonalDB.PeopleEntry.COLUMN_NAME));
            //person_name = name;
            if(name.equals(thename)){
                person_name = name;
                NAME = name;
                isit = 1;
            }
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(PersonalDB.PeopleEntry.COLUMN_ID));

            result += itemId + " " + name + " " + age + "\n";
        }

        test.setText(result);
        //personal_name.setText(person_name);
        cursor.close();
        return isit;
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
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
            finish();
        }
        else
        {
            presstime = tempTime;
            Toast.makeText(getApplicationContext(), "한번더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }

    public void chargeChanger(int charge){
        String temp = Integer.toString(charge);
        for(int i =3; temp.length()-i>0;i+=4){
            temp = new StringBuilder(temp).insert(temp.length()-i,",").toString();
        }

        CHARGE = temp;

    }


}