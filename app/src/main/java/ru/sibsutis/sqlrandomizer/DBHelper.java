package ru.sibsutis.sqlrandomizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "LabWorkDatabase", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table students ("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "weight float,"
                + "height integer,"
                + "age integer" + ");");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
}

