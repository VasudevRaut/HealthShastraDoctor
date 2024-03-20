package com.example.healthshastradr;

public class UpcommingListModel {
    String app_id, patient_number, doctor_number, date, time, payment_status, appointment_status, prescription;
    String name, surname, number, email, pass, age, token, lati, longi;


    public UpcommingListModel(String app_id, String patient_number, String doctor_number, String date, String time, String payment_status, String appointment_status, String prescription, String name, String surname, String number, String email, String pass, String age, String token, String lati, String longi) {
        this.app_id = app_id;
        this.patient_number = patient_number;
        this.doctor_number = doctor_number;
        this.date = date;
        this.time = time;
        this.payment_status = payment_status;
        this.appointment_status = appointment_status;
        this.prescription = prescription;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.pass = pass;
        this.age = age;
        this.token = token;
        this.lati = lati;
        this.longi = longi;
    }


    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getPatient_number() {
        return patient_number;
    }

    public void setPatient_number(String patient_number) {
        this.patient_number = patient_number;
    }

    public String getDoctor_number() {
        return doctor_number;
    }

    public void setDoctor_number(String doctor_number) {
        this.doctor_number = doctor_number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getAppointment_status() {
        return appointment_status;
    }

    public void setAppointment_status(String appointment_status) {
        this.appointment_status = appointment_status;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public UpcommingListModel() {
    }
}
