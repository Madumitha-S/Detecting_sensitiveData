package com.android.phonebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 26-08-2018.
 */

public class MadHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="phonedb";
    public static final int  DATABASE_VERSION=1;
    public  static final String TABLE_NAME = "contact";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_MOBILE= "mobile";
    public static final String QUERY_CREATE = "CREATE TABLE "+TABLE_NAME+" ( "+FIELD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FIELD_NAME+" VARCHAR(100), "+FIELD_MOBILE+" VARCHAR(100) )";


    public MadHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
