package com.example.bookmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "booksdb";
    public static final int DB_VERSION = 1;

    private static DBHelper instance;
    private static String SQL_CREATE = String.format(
        "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL)",
        BookContract.TABLE_NAME,
        BookContract.Columns._ID,
        BookContract.Columns.title,
        BookContract.Columns.author,
        BookContract.Columns.publishing_house,
        BookContract.Columns.borrowed
    );

    private static String SQL_DROP = "DROP TABLE IF EXISTS "+BookContract.TABLE_NAME;

    private DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    public static DBHelper getInstance(Context context){
        if (instance == null){
            instance = new DBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
