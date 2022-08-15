package com.example.sosproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "sos.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        final String SQL_CREATE_USER =
//                "CREATE TABLE "+User.UserEntry.TABLE_NAME + " (" +
//                        User.UserEntry.ID + " INTEGER PRIMARY KEY," +
//                        User.UserEntry.PASS + " TEXT)";
        db.execSQL("CREATE TABLE IF NOT EXISTS BoardingInfoList(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, time TEXT, start_s INTEGER, end_s INTEGER, fare INTEGER, total_fare INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public ArrayList<BoardingInfo> getAddressList() {
        ArrayList<BoardingInfo> boardingInfos = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BoardingInfoList ORDER BY id ASC ", null);
        if (cursor.getCount() > 0) {
            //조회 데이터가 있을때 내부 수행
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex("date"));
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                @SuppressLint("Range") int start = cursor.getInt(cursor.getColumnIndex("start"));
                @SuppressLint("Range") int end = cursor.getInt(cursor.getColumnIndex("end"));
                @SuppressLint("Range") int fare = cursor.getInt(cursor.getColumnIndex("fare"));
                @SuppressLint("Range") int total_fare = cursor.getInt(cursor.getColumnIndex("total_fare"));

                BoardingInfo boardingInfo = new BoardingInfo(id, date, time, start, end, fare, total_fare);
                boardingInfos.add(boardingInfo);
            }
        }

        cursor.close();

        return boardingInfos;
    }


    //INSERT 문 (주소 목록을 DB에 넣는다.)
    public void InsertAddress(String _date, String _time, int _start_s, int _end_s, int _fare, int _total_fare) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO AddressList (date, time, start_s, end_s, fare, total_fare) VALUES('" + _date + "','" + _time + "', '" + _start_s + "', '" + _end_s + "', '" + _fare + "', , '" + _total_fare + "');");
    }

    // DELETE 문 (주소 목록을 제거 한다.)
    public void deleteAddress(int _id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM BoardingInfoList WHERE id = '" + _id + "'");

    }
    public void dbInitialize(){

        ArrayList<BoardingInfo> boardingInfos = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM BoardingInfoList");

    } // DB 초기화
}
