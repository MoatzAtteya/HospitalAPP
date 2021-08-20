package com.example.hospialapp.user.pojo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.hospialapp.database.DataBaseHelper;

public class UsersMethods {



    public static void add_new_user(DataBaseHelper db, users user){
        SQLiteDatabase sqLiteDatabase;
        ContentValues contentValues = new ContentValues();
        contentValues.put("Gender" , user.gender);
        contentValues.put("User_Name" , user.userName);
        contentValues.put("Full_NAME" , user.fullName);
        contentValues.put("Email" , user.email);
        contentValues.put("Address" , user.address);
        contentValues.put("Password" , user.password);
        contentValues.put("Register_Date" , user.register_date);

        try{
            sqLiteDatabase=db.getWritableDatabase();
            sqLiteDatabase.insertOrThrow("users" , null , contentValues);

        }catch (SQLiteException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean check_username_exist(DataBaseHelper db , String name){
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = db.getReadableDatabase();
        boolean result = false;
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE User_Name = ?;" , new String[] {name});
            if (cursor == null){
                result = true;
            } else {
                result = false;
            }
            cursor.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return  result;
    }

    public static boolean check_user_exist(DataBaseHelper db,String name , String password){
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase=db.getReadableDatabase();
        boolean result = false;
        //String sql=("SELECT * FROM USERS WHERE User_Name= ? And Password= ?;");
        try {
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE User_Name= ? And Password= ?;",new String[] {name,password});
            if (cursor.moveToNext()){
                result = true;
            } else
                result = false;

            cursor.close();
        }
        catch (SQLiteException s)
        {
            s.printStackTrace();
        }
        return result;
    }

    public static boolean check_username_exist(DataBaseHelper db,String name , int id){
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase=db.getReadableDatabase();
        boolean result = false;
        try {
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE User_Name= ? AND ID != ? ;",new String[] {name, String.valueOf(id)});
            if (cursor.moveToNext()){
                result = true;
            } else
                result = false;

            cursor.close();
        }
        catch (SQLiteException s)
        {
            s.printStackTrace();
        }
        return result;
    }
    public static  String get_userName_byID(DataBaseHelper db , int id){
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase = db.getReadableDatabase();
        String userName = "";
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT Full_NAME FROM users where ID = ?;" , new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()) {
                userName = cursor.getString(0);
            }
            cursor.close();
        }

        catch (SQLiteException e){
            e.printStackTrace();
        }
        return userName;
    }

    public static users get_user_byNameAndPassword(DataBaseHelper db,String name,String password){
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase=db.getReadableDatabase();
        users user = new users();
        try {
            Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM USERS WHERE User_Name= ? And Password= ?;",new String[] {name,password});
            if (cursor.moveToNext()){
                int id=Integer.parseInt(cursor.getString(0));
                String full_name=cursor.getString(1);
                String address=cursor.getString(2);
                int gender=Integer.parseInt(cursor.getString(3));
                String user_name=cursor.getString(4);
                String email=cursor.getString(5);
                String password_user=cursor.getString(6);
                String register_date=cursor.getString(7);
                user=new users(id,gender,user_name,full_name,email,address,password_user,register_date);
            }
            cursor.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        return user;
    }

    public static void update_userData(DataBaseHelper db,String name, String email , String address ,int id){
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        String sql = "update users set User_Name = " +"'" + name + "'" + " , Email = " + "'" + email + "'" + " ,Address= " +"'"+ address +"'" + " where id= " + id;
        sqLiteDatabase.execSQL(sql);
    }

    public static void update_userPassword(DataBaseHelper db , String new_password , int id)
    {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        String sql = "UPDATE users SET Password = " + "'" + new_password + "'" + " where id =" + id;
        sqLiteDatabase.execSQL(sql);
    }



}
