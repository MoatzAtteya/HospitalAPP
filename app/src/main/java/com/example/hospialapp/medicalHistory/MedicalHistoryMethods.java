package com.example.hospialapp.medicalHistory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.hospialapp.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryMethods {

    public static long add_new_MedicalH(DataBaseHelper db , MedicalHistory medicalHistory){
        SQLiteDatabase sqLiteDatabase;
        long result = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("Patient_ID" , medicalHistory.getPatient_id());
        contentValues.put("BloodPressure" , medicalHistory.getPressure());
        contentValues.put("BloodSugar" , medicalHistory.getSugar());
        contentValues.put("Weight" , medicalHistory.getWeight());
        contentValues.put("Temprature" , medicalHistory.getTemp());
        contentValues.put("MedicalPres" , medicalHistory.getPressure());
        contentValues.put("Creation_date" , medicalHistory.getCreation_date());
        try {
            sqLiteDatabase = db.getWritableDatabase();
            result = sqLiteDatabase.insert("medicalhistory" , null , contentValues);
            sqLiteDatabase.close();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return result;

    }

    public static List<MedicalHistory> get_all_History(DataBaseHelper db , int patient_id){
        SQLiteDatabase sqLiteDatabase;
        List<MedicalHistory> medicalHistoryList = new ArrayList<>();
        try {
            sqLiteDatabase = db.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from medicalhistory where Patient_ID=?" , new String[]{String.valueOf(patient_id)});
            if(cursor.moveToFirst()){
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    String pressure = cursor.getString(2);
                    String sugar = cursor.getString(3);
                    String weight = cursor.getString(4);
                    String temp = cursor.getString(5);
                    String presc = cursor.getString(6);
                    String date = cursor.getString(7);
                    medicalHistoryList.add(new MedicalHistory(id , patient_id , pressure , sugar
                    ,weight , temp , presc ,date));


                }while (cursor.moveToNext());
                cursor.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return medicalHistoryList;
    }
}
