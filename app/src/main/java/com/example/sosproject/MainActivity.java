package com.example.sosproject;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    NfcAdapter nfcAdapter;
    private int ride_or_quit;  // 0에서 nfc 태깅: 승차 / 1에서 nfc 태깅: 하차
    private int is_tag_mode;  // 0: nfc 태그 불가 / 1: nfc 태그 가능
    Station station = new Station();

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
    static String NAME;
    static String CHARGE;
    static final String DB_NAME = "personal.db";


    // Retrofit (Spring server 연결부)
    static final String p_id = "990321";

    UserInfo p_userInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        //로딩화면 관련 코드
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
//        Log.e("before after Retrofit", CHARGE);
//        Log.e("before after Retrofit", NAME);

        // 내부 DB관련 코드

        // Log.e("after", Integer.toString(p_userInfo.getTotal_fare()));
        // Log.e("after", NAME);
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


        // server 연결 코드결
        // retrofitAPI interface 구현

        selectDB();

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

        // Retrofit 통신이 비동기적으로 발생해서 Retrofit callback 함수 내에서 NAME이랑 CHARGE를 할당하는 작업이
        // NewRunnable 쓰레드가 돌아간 이후에 실행되는 것 같아서
        // personal_name, personal_charge에 값을 할당하는 부분을 Retrofit callback 함수 내에 넣었습니다
//        class NewRunnable implements Runnable{
//            int n = 1;
//            TextView personal_name = (TextView)findViewById(R.id.personal_name);
//            TextView personal_charge = (TextView) findViewById(R.id.menu_charge);
//
//            @Override
//            public void run(){
//                while(n>0){
//                    String name = NAME;
//                    personal_name.setText(NAME);
//                    personal_charge.setText(CHARGE);
//                    n--;
//                }
//            }
//        }



//        NewRunnable nr = new NewRunnable();
//        Thread t = new Thread(nr);
//        t.start();

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
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });


                LinearLayout btn_gps = (LinearLayout) findViewById(R.id.btn_gps);
                btn_gps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "지하철 노선도 확인", Toast.LENGTH_SHORT).show();

                    }
                });


                //setting 관련 코드
                ImageButton btn_setting = (ImageButton) findViewById(R.id.btn_setting);
                btn_setting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein_right, R.anim.stay);

                    }
                });


                //main NFC태그 관련 코드
                hand = (ImageView) findViewById(R.id.hand);
                nfcReader = (LinearLayout) findViewById(R.id.nfcReader);
                card = (ImageButton) findViewById(R.id.personal_Card);

                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // personal_card를 누를 시 cardcounter가 0이면
                        if (cardcounter == 0) {

                            // nfcReader LinearLayout이 보여짐
                            nfcReader.setVisibility(View.VISIBLE);

                            cardAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.card_anim); //에니메이션설정파일
                            card.startAnimation(cardAnim);

                            nfcAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                                    R.anim.nfc_anim); //에니메이션설정파일
                            nfcReader.startAnimation(nfcAnim);

                            card.setClickable(false);
                            handcounter = 1;

                    is_tag_mode = 1;  // nfc 태그시 정보 읽어오기 가능

                    if (handcounter == 1) {

                        handAnim = AnimationUtils.loadAnimation(getApplicationContext(),
                                R.anim.hand_anim); //에니메이션설정파일
                        hand.startAnimation(handAnim);
                        sendToDB(); // 하차시 DB에 데이터 전송
                    }

                            cardcounter = 1;
                        }
                    }

                });

                cardLayout = (RelativeLayout) findViewById(R.id.cardLayout);
                cardLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cardcounter == 1) {

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
                    is_tag_mode = 0; //nfc 태그 태깅해도 읽어오기 불가능
                }
            }
        });
    }

    //DB 테스트
    private void dbDataDelete(String target) {

        String sql = "delete * from mycontacts where name=" + "'" + target + "'";
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
            if (name.equals(thename)) {
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

        if (0 <= intervalTime && finishtimeed >= intervalTime) {
            finish();
        } else {
            presstime = tempTime;
            Toast.makeText(getApplicationContext(), "한번더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show();
        }
    }

    public void chargeChanger(int charge) {
        String temp = Integer.toString(charge);
        for (int i = 3; temp.length() - i > 0; i += 4) {
            temp = new StringBuilder(temp).insert(temp.length() - i, ",").toString();
        }

        CHARGE = temp;

    }

    // NFC 태그를 읽었을 때 DB로 데이터를 전송하는 함수, total 요금을 전송
    public void sendToDB(){
        // 누적 비용을 만들어서 전송할 거임

        String age = p_userInfo.getAge();
        String income = p_userInfo.getIncome_grade();

        UserInfo n_userInfo = new UserInfo(p_id, p_userInfo.getAge(), p_userInfo.getIncome_grade(), p_userInfo.getTotal_fare());
        n_userInfo.setTotal_fare(Integer.toString(calculateFare(1, 4)));

        String temp = n_userInfo.getTotal_fare();

        updateDB(n_userInfo);
        Log.e("send to DB",NAME+", "+age+", "+income+", "+temp);

    }


    // 누적요금 합치는 공식 -> 예시임
            public int calculateFare(int startStation, int endStation){
                int base_fare = 1250;
                int total_send = Integer.parseInt(p_userInfo.getTotal_fare());
                int age = Integer.parseInt(p_userInfo.getAge());
                int income = Integer.parseInt(p_userInfo.getIncome_grade());
                int distance = endStation - startStation;
                total_send += base_fare + distance*(int)(income*0.05*100);
                p_userInfo.setTotal_fare(Integer.toString(total_send));
                return total_send;
            }

    // Retrofit 통신으로
    private void selectDB() {
        RetrofitAPI retrofitApi = RetrofitClientInstance.getRetrofitInstance().create(RetrofitAPI.class);
        Call<UserInfo> call = retrofitApi.getMember(p_id);

        call.enqueue(new Callback<UserInfo>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                // 통신이 잘 이뤄지면
                if (response.isSuccessful()) {
                    UserInfo s_userInfo = response.body(); // DB의 데이터를 List<UserInfo> 형태로 읽어들임
                    // list에서 p_id를 id로 갖는 UserInfo 타입의 객체를 p_userInfo에 저장
                    // p_userInfo = list.stream().filter(h -> h.getId().equals(p_id)).findFirst().orElseThrow(() -> new IllegalArgumentException());
                    p_userInfo = s_userInfo;
                    // NAME에 p_userInfo.getId() 할당
                    String name = s_userInfo.getId();
                    // CHARGE에 p_userInfo.getTotal_fare() 할당
                    chargeChanger(Integer.parseInt(s_userInfo.getTotal_fare())); // -> 10,000과 같이 세 자리 ,로 끊는 함수

                    TextView personal_name = (TextView) findViewById(R.id.personal_name);
                    TextView personal_charge = (TextView) findViewById(R.id.menu_charge);

                    personal_name.setText(name);
                    personal_charge.setText(CHARGE);

                } else {
                    Log.e("SelectDB", "response but fail");
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e("SelectDB", "fail");
                t.printStackTrace();
            }
        });
    }

    private void updateDB(UserInfo u_userInfo) {
            RetrofitAPI retrofitApi = RetrofitClientInstance.getRetrofitInstance().create(RetrofitAPI.class);
        Call<UserInfo> call = retrofitApi.updateMember(u_userInfo.getId(), u_userInfo.getAge(), u_userInfo.getIncome_grade(), u_userInfo.getTotal_fare());
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                Log.e("updateDB", u_userInfo.getTotal_fare());
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e("SelectDB", "fail");
                t.printStackTrace();
            }
        });
        selectDB();
    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        enableForegroundDispatchSystem();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        disableForegroundDispatchSystem();
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        if (intent.hasExtra(NfcAdapter.EXTRA_TAG)) {
//            if (is_tag_mode == 1) {
//                Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
//                // nfc tag 데이터 읽는 부분
//                if (ride_or_quit == 0){
//                    ride(parcelables);
//                    ride_or_quit = 1;
//                }
//                else {
//                    quit(parcelables);
//                    ride_or_quit = 0;
//                }
//            }
//            else {
//                Toast.makeText(this, "open NFC tag mode", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void ride(Parcelable[] parcelables){
//        String rideStation;
//        boolean is_empty = true;
//        if(parcelables != null && parcelables.length > 0)
//        {
//            rideStation = readTextFromMessage((NdefMessage) parcelables[0]);
//            if (!rideStation.equals("None")) {
//                is_empty = false;
//                Toast.makeText(this, "ride: " + station.string2num(rideStation), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        if (is_empty) {// 빈 nfc tag 라면 No NDEF messages found 메시지 출력
//            Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void quit(Parcelable[] parcelables){
//        String quitStation;
//        boolean is_empty = true;
//        if(parcelables != null && parcelables.length > 0)
//        {
//            quitStation = readTextFromMessage((NdefMessage) parcelables[0]);
//            if (!quitStation.equals("None")) {
//                is_empty = false;
//                Toast.makeText(this, "quit: " + station.string2num(quitStation), Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        if (is_empty) {// 빈 nfc tag 라면 No NDEF messages found 메시지 출력
//            Toast.makeText(this, "No NDEF messages found!", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private String readTextFromMessage(NdefMessage ndefMessage) {
//        NdefRecord[] ndefRecords = ndefMessage.getRecords();
//
//        if(ndefRecords != null && ndefRecords.length>0){
//            NdefRecord ndefRecord = ndefRecords[0];
//            return getTextFromNdefRecord(ndefRecord);
//            // Toast.makeText(this, tagContent, Toast.LENGTH_SHORT).show();
//        }else
//        {
//            return "None";
//        }
//    }
//
//    public String getTextFromNdefRecord(NdefRecord ndefRecord)
//    {
//        String tagContent = null;
//        try {
//            byte[] payload = ndefRecord.getPayload();
//            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
//            int languageSize = payload[0] & 0063;
//            tagContent = new String(payload, languageSize + 1,
//                    payload.length - languageSize - 1, textEncoding);
//        } catch (UnsupportedEncodingException e) {
//            Log.e("getTextFromNdefRecord", e.getMessage(), e);
//        }
//        return tagContent;
//    }
//
//    private void enableForegroundDispatchSystem() {
//
//        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        IntentFilter[] intentFilters = new IntentFilter[]{};
//        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilters, null);
//    }
//
//    private void disableForegroundDispatchSystem() {
//        nfcAdapter.disableForegroundDispatch(this);
//    }
}