package com.example.hospialapp.doctor;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.hospialapp.database.DataBaseHelper;

public class DoctorsMethods {

    public static void add_newDoctor(DataBaseHelper db, Doctors doctor){
        SQLiteDatabase sqLiteDatabase;
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name" , doctor.Name);
        contentValues.put("SpecializationID" , doctor.SpecializationID);
        contentValues.put("Address" , doctor.Address);
        contentValues.put("DocFees" , doctor.DocFees);
        contentValues.put("ContactNo" , doctor.ContactNo);
        contentValues.put("Email" , doctor.Email);
        contentValues.put("Password" , doctor.Password);
        contentValues.put("RegisterDate" , doctor.RegisterDate);
        try{
            sqLiteDatabase=db.getWritableDatabase();
            sqLiteDatabase.insertOrThrow("doctors" , null , contentValues);

        }catch (SQLiteException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean check_doctorExist_login(DataBaseHelper db , String name , String password)
    {
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = db.getReadableDatabase();
        boolean isDoctorExist = false;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from doctors where Name = ? AND Password = ?" , new String[] {name , password});
            if(cursor.moveToNext())
                isDoctorExist = true;
            else
                isDoctorExist = false;
            cursor.close();
        }
        catch (SQLException s)
        {
            s.printStackTrace();
        }
        return isDoctorExist;
    }

    public static String[] get_doctorsBy_specialID(DataBaseHelper db , int special_id){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String[] names = new String[20];
        int i=0;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select D.Name from doctorspecialization AS s  inner join doctors AS D on s.ID = D. SpecializationID  where D.SpecializationID = ?;", new String[]{String.valueOf(special_id)});
            if(cursor.moveToFirst())
            {
                do {
                        names[i] = cursor.getString(0);
                        i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return names;
    }

    public static int get_docFees_byName(DataBaseHelper db, String docName){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String sql = "SELECT DocFees FROM doctors WHERE Name =?";
        int fees=0;
        try {
            Cursor cursor=sqLiteDatabase.rawQuery(sql , new String[]{docName});
            if(cursor.moveToNext()){
                fees = Integer.parseInt(cursor.getString(0));
            }
            cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return fees;
    }

    public static int get_doctorID_byName(DataBaseHelper db,String docName){
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String sql = "SELECT ID FROM doctors WHERE Name =?";
        int id =0;
        try {
            Cursor cursor=sqLiteDatabase.rawQuery(sql , new String[]{docName});
            if(cursor.moveToNext()){
                id = Integer.parseInt(cursor.getString(0));
            }
            cursor.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;

    }

    public static Doctors getDoctorByUserAndPass(DataBaseHelper db , String docName , String docPass)
    {
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase=db.getReadableDatabase();
        Doctors doctor = new Doctors();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from doctors where Name = ? AND Password = ?" , new String[] {docName , docPass});
            if(cursor.moveToNext()){
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int specID = Integer.parseInt(cursor.getString(2));
                String address = cursor.getString(3);
                int fees = Integer.parseInt(cursor.getString(4));
                long contNum = Long.parseLong(cursor.getString(5));
                String email = cursor.getString(6);
                String pass = cursor.getString(7);
                String regDate = cursor.getString(8);
                doctor = new Doctors(id,specID,contNum,fees,address,name,email,pass,regDate);
            }
            cursor.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    return doctor;
    }

    public static long update_doctor_profile(DataBaseHelper db , int doctor_id , String doctor_name , String doctor_email , String doctor_address){
        SQLiteDatabase sqLiteDatabase;
        long result = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name" , doctor_name);
        contentValues.put("Address" , doctor_address);
        contentValues.put("Email" , doctor_email);
        try {
            sqLiteDatabase = db.getWritableDatabase();
            String[] args = new String[]{String.valueOf(doctor_id)};
            result = sqLiteDatabase.update("doctors" , contentValues , "ID=?" , args);
            sqLiteDatabase.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;


    }

    public static void update_doctorPassword(DataBaseHelper db , String new_password , int id)
    {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        String sql = "UPDATE doctors SET Password = " + "'" + new_password + "'" + " where id =" + id;
        sqLiteDatabase.execSQL(sql);
    }
}
