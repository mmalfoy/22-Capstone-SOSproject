package com.example.sosproject;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.provider.BaseColumns;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "USER.db"; // 데이터베이스 명
    public static final String TABLE_NAME = "user_table"; // 테이블 명
    public static final String COL_NUM = "NUMBER";          // 번호
    public static final String COL_TOTAL = "total_fare";    // 총 요금
    public static final String COL_MOMENT = "moment_fare";    // 현재 요금
    public static final String COL_YY = "YYYYDDMM";      // 년월일
    public static final String COL_TT = "TTMMSS";        // 시분초
    public static final String COL_WHERE = "station_num";     //역 위치


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE;
        SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("+
                COL_NUM+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TOTAL+" TEXT, "
                + COL_MOMENT+" TEXT, "
                + COL_YY+" TEXT, "
                + COL_TT+" TEXT, "
                + COL_WHERE+" TEXT);";

        db.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    // 데이터베이스 추가하기 insert
    public boolean insertData( String moment_fare, String total_fare,String yymmdd, String ttmmss, Integer where){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MOMENT,moment_fare);
        contentValues.put(COL_TOTAL,total_fare);
        contentValues.put(COL_YY,yymmdd);
        contentValues.put(COL_TT,ttmmss);
        contentValues.put(COL_WHERE, where);
        long result = db.insert(TABLE_NAME, null,contentValues);


        if(result == -1) {
            Log.d(TAG, "실패");
            return false;
        }
        else{
            Log.d(TAG,"add 성공");
            return true;
        }


    }
    // 데이터베이스 삭제하기
    public void deleteData(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);

    }

    //데이터베이스 항목 읽어오기 Read
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                COL_NUM,
                COL_TOTAL,
                COL_MOMENT,
                COL_YY,
                COL_TT,
                COL_WHERE
        };

        String sortOrder = COL_NUM + " DESC";

        Cursor cursor = db.query(
                PersonalDB.PeopleEntry.TABLE_NAME,   // The table to query
                projection,   // The array of columns to return (pass null to get all)
                null,   // where 문에 필요한 column
                null,   // where 문에 필요한 value
                null,   // group by를 적용할 column
                null,   // having 절
                sortOrder   // 정렬 방식
        );

        return cursor;
    }






//
//    //데이터베이스 수정하기
//    public boolean updateData(String id, String name, String phone, String address){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,name);
//        contentValues.put(COL_3,phone);
//        contentValues.put(COL_4,address);
//        db.update(TABLE_NAME,contentValues,"ID = ?", new String[] { id });
//        return true;
//    }
}