package com.example.hospialapp.appointments;

import java.io.Serializable;

public class Appointment implements Serializable {
    private  int id,doc_fees,user_id;
    private  String doc_name,doc_special,datee,time,creation_date;

    public Appointment(int doc_fees, String doc_name, String doc_special, String datee, String time , int user_id , String creation_date) {
        this.doc_fees = doc_fees;
        this.doc_name = doc_name;
        this.doc_special = doc_special;
        this.datee = datee;
        this.time = time;
        this.user_id=user_id;
        this.creation_date = creation_date;
    }
    public Appointment(int id, int doc_fees, String doc_name, String doc_special, String datee, String time , int user_id , String creation_date) {
        this.id = id;
        this.doc_fees = doc_fees;
        this.doc_name = doc_name;
        this.doc_special = doc_special;
        this.datee = datee;
        this.time = time;
        this.user_id=user_id;
        this.creation_date = creation_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoc_fees() {
        return doc_fees;
    }

    public void setDoc_fees(int doc_fees) {
        this.doc_fees = doc_fees;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_special() {
        return doc_special;
    }

    public void setDoc_special(String doc_special) {
        this.doc_special = doc_special;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
