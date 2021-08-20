package com.example.hospialapp.doctor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.hospialapp.database.DataBaseHelper;

public class Doctorspecialization_Methods {

    public static int getID_specialByName(DataBaseHelper db , String name ){
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase=db.getReadableDatabase();
        int id = -1;
        try {
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT ID FROM doctorspecialization WHERE Specialization=?",new String[] {name});
            if (cursor.moveToNext()){
                id = Integer.parseInt(cursor.getString(0));
            }
            cursor.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        return id;
    }
}
