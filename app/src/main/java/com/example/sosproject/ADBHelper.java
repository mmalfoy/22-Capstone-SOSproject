package com.example.sosproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ADBHelper extends SQLiteOpenHelper {
    private int DB_VERSION;
    private String DB_NAME;
    public static int cnt;
    public ADBHelper(@Nullable Context context, String db_name, int db_version) {
        super(context, db_name, null, db_version);
        this.DB_VERSION = db_version;
        this.DB_NAME = db_name;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS AccoountList(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, phone TEXT NOT NULL, birth TEXT NOT NULL, login INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {onCreate(db);}

        public ArrayList<AccountInfo> getAccountInfoList() {
            ArrayList<AccountInfo> accountInfoList = new ArrayList<>();
            cnt = 0;
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM accountInfoList ORDER BY id ASC ", null);
            if (cursor.getCount() > 0) {
                //조회 데이터가 있을때 내부 수행
                while (cursor.moveToNext()) {
                    cnt++;

                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                     @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
                     @SuppressLint("Range") String birth = cursor.getString(cursor.getColumnIndex("birth"));
                    @SuppressLint("Range") int login = cursor.getInt(cursor.getColumnIndex("login"));

                    AccountInfo accountinfo = new AccountInfo(id, name, phone, birth, login);
                    accountInfoList.add(accountinfo);
                }
            }

            cursor.close();
            return accountInfoList;
        }

        public void InsertAccountInfo(String _name, String _phone, String _birth, int _login) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO accountInfoList (name, phone, birth, login) VALUES('" + _name + "','" + _phone + "', '" + _birth + "', '" + _login + "');");
        }

        public void updateAccountInfo(String _name, String _phone, String _birth, int _login){
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("UPDATE accountInfoList SET name ='" + _name + "', phone='" + _phone + "', birth= '" + _birth + "', login='" + _login);
        }

}
