package com.example.hospialapp.doctor;

public class Doctors {
    int ID , SpecializationID , DocFees;
    long ContactNo;
    String Address ,Name, Email , Password , RegisterDate ;

    public Doctors(int ID, int specializationID, long contactNo, int docFees, String address, String name, String email, String password, String registerDate) {
        this.ID = ID;
        SpecializationID = specializationID;
        ContactNo = contactNo;
        DocFees = docFees;
        Address = address;
        Name = name;
        Email = email;
        Password = password;
        RegisterDate = registerDate;
    }

    public Doctors(int specializationID, long contactNo, int docFees, String address, String name, String email, String password, String registerDate) {
        SpecializationID = specializationID;
        ContactNo = contactNo;
        DocFees = docFees;
        Address = address;
        Name = name;
        Email = email;
        Password = password;
        RegisterDate = registerDate;
    }

    public Doctors() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSpecializationID() {
        return SpecializationID;
    }

    public void setSpecializationID(int specializationID) {
        SpecializationID = specializationID;
    }

    public long getContactNo() {
        return ContactNo;
    }

    public void setContactNo(long contactNo) {
        ContactNo = contactNo;
    }

    public int getDocFees() {
        return DocFees;
    }

    public void setDocFees(int docFees) {
        DocFees = docFees;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }
}
