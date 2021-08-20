package com.example.hospialapp.medicalHistory;

public class MedicalHistory {
   private int id , patient_id;
   private String pressure , sugar , weight , temp , persciption , creation_date;

    public MedicalHistory(int patient_id, String pressure, String sugar, String weight, String temp, String persciption, String creation_date) {
        this.patient_id = patient_id;
        this.pressure = pressure;
        this.sugar = sugar;
        this.weight = weight;
        this.temp = temp;
        this.persciption = persciption;
        this.creation_date = creation_date;
    }

    public MedicalHistory(int id, int patient_id, String pressure, String sugar, String weight, String temp, String persciption, String creation_date) {
        this.id = id;
        this.patient_id = patient_id;
        this.pressure = pressure;
        this.sugar = sugar;
        this.weight = weight;
        this.temp = temp;
        this.persciption = persciption;
        this.creation_date = creation_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPersciption() {
        return persciption;
    }

    public void setPersciption(String persciption) {
        this.persciption = persciption;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
