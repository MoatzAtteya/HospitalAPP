package com.example.hospialapp.patient;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.hospialapp.appointments.Appointment;
import com.example.hospialapp.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PatientMethods {

    public static  long add_new_patient(DataBaseHelper db , Patient patient)
    {
        SQLiteDatabase sqLiteDatabase;
        long result = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put( "Doc_Id", patient.getDoc_id());
        contentValues.put("Patient_Name" , patient.getFull_name());
        contentValues.put("Patient_Contact" ,patient.getContact_number());
        contentValues.put("Patient_Email" , patient.getEmail());
        contentValues.put("Patient_Gender" , patient.getGender());
        contentValues.put("Patient_Address" , patient.getAddress());
        contentValues.put("Patient_Age" , patient.getAge());
        contentValues.put("Patient_mHistory" , patient.getMedical_history());
        contentValues.put("Creation_Date" , patient.getCreation_date());

        try {
            sqLiteDatabase=db.getWritableDatabase();
            result = sqLiteDatabase.insert("patients" , null ,contentValues);
            sqLiteDatabase.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static List<Patient> get_allPatients_byDocID(DataBaseHelper db , int doc_id)
    {
        SQLiteDatabase sqLiteDatabase;
        List<Patient> patientList = new ArrayList<>();
        try {
            sqLiteDatabase = db.getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from patients where Doc_ID = ? ", new String[]{String.valueOf(doc_id)});
            if (cursor.moveToFirst()){
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    String patient_name = cursor.getString(2);
                    String patient_contNum = cursor.getString(3);
                    String patient_email = cursor.getString(4);
                    int patient_gender = Integer.parseInt(cursor.getString(5));
                    String patient_address = cursor.getString(6);
                    int age = Integer.parseInt(cursor.getString(7));
                    String medical_history = cursor.getString(8);
                    String creation_date = cursor.getString(9);
                    patientList.add(new Patient(patient_name,patient_contNum,patient_email,
                            patient_address,creation_date,medical_history,
                            id , doc_id ,patient_gender ,age));
                }while (cursor.moveToNext());
                cursor.close();
            }

        }catch (SQLException e){
            System.out.println("error in view all:" + e);
        }
        return patientList;
    }

    public static boolean delete_patient_byID(DataBaseHelper db , int id){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        return sqLiteDatabase.delete("patients" , "ID" + "=" + id , null) > 0;
    }

    public static void delete_allPatients(DataBaseHelper db)
    {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + "patients");
        sqLiteDatabase.close();
    }

    public static long update_patient_data(DataBaseHelper db , Patient patient){
        SQLiteDatabase sqLiteDatabase;
        long result = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("Patient_Name" , patient.getFull_name());
        contentValues.put("Patient_Contact" ,patient.getContact_number());
        contentValues.put("Patient_Email" , patient.getEmail());
        contentValues.put("Patient_Gender" , patient.getGender());
        contentValues.put("Patient_Address" , patient.getAddress());
        contentValues.put("Patient_Age" , patient.getAge());
        contentValues.put("Patient_mHistory" , patient.getMedical_history());
        try {
            sqLiteDatabase = db.getWritableDatabase();
            String[] args = new String[]{String.valueOf(patient.id)};
            result = sqLiteDatabase.update("patients" , contentValues , "ID=?" , args);
            sqLiteDatabase.close();
        }catch (SQLException s){
            s.printStackTrace();
        }
     return result;
    }
}
