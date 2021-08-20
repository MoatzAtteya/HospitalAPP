package com.example.hospialapp.user.pojo;

import java.util.Date;

public class users {
    int id;

    public int getGender() {
        return gender;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    int gender;
    String userName,fullName,email,address,password,register_date;

    public users() {

    }

    public users(int gender, String userName, String fullName, String email, String address, String password, String register_date) {
        this.gender = gender;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.register_date = register_date;
    }

    public users(int id, int gender, String userName, String fullName, String email, String address, String password, String register_date) {
        this.id = id;
        this.gender = gender;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.password = password;
        this.register_date = register_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int isGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreation_date() {
        return register_date;
    }

    public void setCreation_date(String register_date) {
        this.register_date = register_date;
    }
}
