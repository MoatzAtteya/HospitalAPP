package com.example.hospialapp.patient;

import java.io.Serializable;

public class Patient {
    String full_name , contact_number , email , address , creation_date , medical_history;
    int id , doc_id , gender , age;

    public Patient(String full_name, String contact_number, String email, String address ,String creation_date , String medical_history , int id, int doc_id, int gender, int age) {
        this.full_name = full_name;
        this.contact_number = contact_number;
        this.email = email;
        this.address = address;
        this.id = id;
        this.doc_id = doc_id;
        this.gender = gender;
        this.age = age;
        this.creation_date = creation_date;
        this.medical_history = medical_history;
    }

    public Patient(String full_name, String contact_number, String email , String creation_date , String address,String medical_history , int doc_id, int gender, int age) {
        this.full_name = full_name;
        this.contact_number = contact_number;
        this.email = email;
        this.address = address;
        this.medical_history = medical_history;
        this.doc_id = doc_id;
        this.gender = gender;
        this.age = age;
        this.creation_date = creation_date;
    }

    public Patient(String full_name, String contact_number, String email, String address, String medical_history, int id, int gender, int age) {
        this.full_name = full_name;
        this.contact_number = contact_number;
        this.email = email;
        this.address = address;
        this.medical_history = medical_history;
        this.id = id;
        this.gender = gender;
        this.age = age;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getMedical_history() {
        return medical_history;
    }

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
    }
}
