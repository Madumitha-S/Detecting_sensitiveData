package com.android.phonebook;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by User on 26-08-2018.
 */

public class MyDatabase {
    public static final String TABLE_NAME= "contact";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_NAME ="name";
    public static final String FIELD_MOBILE = "mobile";
    Context con;
    MadHelper madHelper;
    public MyDatabase(Context context)
    {
        con=context;
        madHelper = new MadHelper(con);
    }
    public boolean addContact(String name,String mobile)
    {
        SQLiteDatabase db = madHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIELD_NAME,name);
        values.put(FIELD_MOBILE,mobile);
        db.insert(TABLE_NAME,FIELD_NAME,values);
        return true;
    }
    public ArrayList<String> getAllContacts()
    {
        ArrayList<String> data = new ArrayList<>();
        SQLiteDatabase db = madHelper.getWritableDatabase();
        String[]col = {FIELD_NAME,FIELD_MOBILE};
        Cursor cu = db.query(TABLE_NAME,col,null,null,null,null,null);
        cu.moveToFirst();
        while( cu.moveToNext())
        {
            String name = cu.getString(cu.getColumnIndex(FIELD_NAME));
            String mobile = cu.getString(cu.getColumnIndex(String.valueOf(FIELD_MOBILE)));
            String row = name +" "+mobile;
            data.add(row);
        }
        return data;
     }
}
