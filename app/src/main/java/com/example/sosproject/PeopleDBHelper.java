package com.example.sosproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class PeopleDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public PeopleDBHelper(Context context,String DATABASE_NAME,int DATABASE_VERSION) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PersonalDB.PeopleEntry.SQL_CREATE_TABLE); // 테이블 생성
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // 단순히 데이터를 삭제하고 다시 시작하는 정책이 적용될 경우
        sqLiteDatabase.execSQL(PersonalDB.PeopleEntry.SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    void insertRecord(String name, int id) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(PersonalDB.PeopleEntry.COLUMN_NAME, name);
        values.put(PersonalDB.PeopleEntry.COLUMN_ID, id);

        db.insert(PersonalDB.PeopleEntry.TABLE_NAME, null, values);
    }

    public Cursor readRecordOrderByAge() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                PersonalDB.PeopleEntry.COLUMN_NAME,
                PersonalDB.PeopleEntry.COLUMN_ID
        };

        String sortOrder = PersonalDB.PeopleEntry.COLUMN_ID + " DESC";

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
}