package com.example.hospialapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "hospital_Management_System";
    private static final int DATABASE_VERSION = 1;

    public  DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users (ID Integer PRIMARY KEY AUTOINCREMENT,Full_NAME TEXT NOT NULL,Address TEXT NOT NULL,Gender Integer ,User_Name TEXT NOT NULL,Email TEXT NOT NULL,Password TEXT NOT NULL,Register_Date TEXT NOT NULL)");
        db.execSQL("CREATE TABLE doctorspecialization (ID INTEGER PRIMARY KEY AUTOINCREMENT, Specialization TEXT NOT NULL UNIQUE, Creation_Date TEXT)");
        db.execSQL("CREATE TABLE doctors (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, SpecializationID INTEGER NOT NULL, Address TEXT NOT NULL, DocFees INTEGER NOT NULL, ContactNo INTEGER NOT NULL, Email TEXT NOT NULL, Password TEXT NOT NULL, RegisterDate TEXT NOT NULL, FOREIGN KEY(SpecializationID) REFERENCES doctorspecialization(ID));");
        db.execSQL("CREATE TABLE admins (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, Password TEXT NOT NULL);");
        db.execSQL("CREATE TABLE appointments ( A_ID INTEGER PRIMARY KEY AUTOINCREMENT, UserID INTEGER NOT NULL, DoctorSpecialization TEXT NOT NULL, DoctorName TEXT NOT NULL, consultancyFees INTEGER, appointmentDate TEXT NOT NULL,appointmentTime TEXT NOT NULL, Creation_Date TEXT,FOREIGN KEY(UserID) REFERENCES users(ID),FOREIGN KEY(consultancyFees) REFERENCES doctors(DocFees));");
        db.execSQL("CREATE TABLE medicalhistory (ID INTEGER PRIMARY KEY AUTOINCREMENT, Patient_ID INTEGER NOT NULL,BloodPressure TEXT NOT NULL, BloodSugar TEXT NOT NULL,Weight TEXT NOT NULL,Temprature TEXT,MedicalPres Text,Creation_date TEXT );");
        db.execSQL("CREATE TABLE contact_us (ID INTEGER PRIMARY KEY AUTOINCREMENT, FUll_Name TEXT NOT NULL,Email Text NOT NULL, contact_no Text, Message TEXT NOT NULL, Posting_Date TEXT, IsRead Integer NOT NULL);");
        db.execSQL("CREATE TABLE patients (ID INTEGER PRIMARY KEY AUTOINCREMENT, Doc_ID INTEGER NOT NULL , Patient_Name Text Not NULL, Patient_Contact Text NOT NULL, Patient_Email TEXT not null, Patient_Gender INTEGER not Null ,Patient_Address TEXT NOT NULL , Patient_Age INTEGER NOT NULL, Patient_mHistory TEXT, Creation_Date TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("Drop Table IF EXISTS doctorspecialization");
        db.execSQL("DROP TABLE IF EXISTS DOCTORS");
        db.execSQL("DROP TABLE IF EXISTS appointments");
        db.execSQL("DROP TABLE IF EXISTS medicalhistory");
        db.execSQL("DROP TABLE IF EXISTS contact_us");
        db.execSQL("DROP TABLE IF EXISTS admins");
        db.execSQL("DROP TABLE IF EXISTS patients");
        onCreate(db);
    }


}
