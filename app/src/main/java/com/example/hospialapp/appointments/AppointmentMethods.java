package com.example.hospialapp.appointments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.hospialapp.database.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AppointmentMethods {

    public static long add_appoinment(DataBaseHelper db, Appointment userAppoinmentHistory){
        SQLiteDatabase sqLiteDatabase;
        long result = -1;
        ContentValues contentValues = new ContentValues();
        contentValues.put("consultancyFees", userAppoinmentHistory.getDoc_fees());
        contentValues.put("UserID", userAppoinmentHistory.getUser_id());
        contentValues.put("DoctorName", userAppoinmentHistory.getDoc_name());
        contentValues.put("DoctorSpecialization", userAppoinmentHistory.getDoc_special());
        contentValues.put("appointmentDate", userAppoinmentHistory.getDatee());
        contentValues.put("appointmentTime", userAppoinmentHistory.getTime());
        contentValues.put("Creation_Date", userAppoinmentHistory.getCreation_date());
        try {
            sqLiteDatabase =db.getWritableDatabase();
           result= sqLiteDatabase.insert("appointments" , null , contentValues);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;

    }

    public static List<Appointment> get_allAppontments_ByID(DataBaseHelper db, int user_id)
    {
        SQLiteDatabase sqLiteDatabase;
        List<Appointment> userAppoinmentList = new ArrayList<>();


        try {
            sqLiteDatabase = db.getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from appointments where UserID = ? ", new String[]{String.valueOf(user_id)});
            if (cursor.moveToFirst()){
                do {
                    int appointment_id =Integer.parseInt(cursor.getString(0));
                    String doctor_special = cursor.getString(2);
                    String doctor_name = cursor.getString(3);
                    int doctor_fees = Integer.parseInt(cursor.getString(4));
                    String appointment_date = cursor.getString(5);
                    String appointment_time = cursor.getString(6);
                    String appintment_creationDate = cursor.getString(7);
                    userAppoinmentList.add(new Appointment(appointment_id,doctor_fees,doctor_name,doctor_special,appointment_date,appointment_time,user_id,appintment_creationDate));

                }while (cursor.moveToNext());
                cursor.close();
            }

        }catch (SQLException e){
            System.out.println("error in view all:" + e);
        }
        return userAppoinmentList;
    }

    public static List<Appointment> get_allAppontments_ByDocName(DataBaseHelper db, String doc_name)
    {
        SQLiteDatabase sqLiteDatabase;
        List<Appointment> doctorAppoinmentList = new ArrayList<>();


        try {
            sqLiteDatabase = db.getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from appointments where DoctorName = ? ", new String[]{doc_name});
            if (cursor.moveToFirst()){
                do {
                    int appointment_id =Integer.parseInt(cursor.getString(0));
                    int user_id = Integer.parseInt(cursor.getString(1));
                    String doctor_special = cursor.getString(2);
                    String doctor_name = cursor.getString(3);
                    int doctor_fees = Integer.parseInt(cursor.getString(4));
                    String appointment_date = cursor.getString(5);
                    String appointment_time = cursor.getString(6);
                    String appintment_creationDate = cursor.getString(7);
                    doctorAppoinmentList.add(new Appointment(appointment_id,doctor_fees,doctor_name,doctor_special,appointment_date,appointment_time,user_id,appintment_creationDate));

                }while (cursor.moveToNext());
                cursor.close();
            }

        }catch (SQLException e){
            System.out.println("error in view all:" + e);
        }
        return doctorAppoinmentList;
    }

    public static boolean delete_appointment_byID(DataBaseHelper db , int id){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        return sqLiteDatabase.delete("appointments" , "A_ID" + "=" + id , null) > 0;
    }

    public static List<Appointment> get_allAppointments(DataBaseHelper db)
    {
        SQLiteDatabase sqLiteDatabase;
        String get_all_sql = "select * from appointments";
        List<Appointment> userAppoinmentList = new ArrayList<>();


        try {
            sqLiteDatabase = db.getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery(get_all_sql , null);
            if (cursor.moveToFirst()){
                do {
                    int appointment_id =Integer.parseInt(cursor.getString(0));
                    int user_id = Integer.parseInt(cursor.getString(1));
                    int doctor_fees = Integer.parseInt(cursor.getString(4));
                    String doctor_special = cursor.getString(2);
                    String doctor_name = cursor.getString(3);
                    String appointment_date = cursor.getString(5);
                    String appointment_time = cursor.getString(6);
                    String appintment_creationDate = cursor.getString(7);
                    userAppoinmentList.add(new Appointment(appointment_id,doctor_fees,doctor_name,doctor_special,appointment_date,appointment_time,user_id,appintment_creationDate));

                }while (cursor.moveToNext());
                cursor.close();
            }

        }catch (SQLException e){
            System.out.println("error in view all:" + e);
        }


        return userAppoinmentList;
    }


}
