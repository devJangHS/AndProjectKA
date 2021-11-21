package com.jhs.fourthapp.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context){
        super(context, "myDB", null, 1 );
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE Member (id CHAR(20) PRIMARY KEY, name CHAR(20));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Member");
        onCreate(sqLiteDatabase);

    }
}
