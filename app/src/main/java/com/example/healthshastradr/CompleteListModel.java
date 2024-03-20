package com.example.healthshastradr;

public class CompleteListModel {

    String name, surname, email, number, age, token,date,time;
    String lati,longi;

    public CompleteListModel() {
    }

    public CompleteListModel(String name, String surname, String email, String number, String age, String token, String date,String time, String lati, String longi) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.number = number;
        this.age = age;
        this.token = token;
        this.date = date;
        this.time = time;
        this.lati = lati;
        this.longi = longi;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getDate() {
        return date;
    }

    public void setDate(String datetime) {
        this.date = datetime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
