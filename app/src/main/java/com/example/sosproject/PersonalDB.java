package com.example.sosproject;

import android.provider.BaseColumns;

public final class PersonalDB {
    private PersonalDB() {
    }
//이름, 주민번호
    public static class PeopleEntry implements BaseColumns {
        public static final String TABLE_NAME = "people";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ID = "id";
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME + " TEXT," +
                        COLUMN_ID + " INTEGER)";
        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}