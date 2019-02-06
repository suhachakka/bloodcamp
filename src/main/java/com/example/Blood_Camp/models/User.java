package com.example.Blood_Camp.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String bloodgroup;
    private int mobilenumber;
    private int zipcode;
    private String email;
    private String password;

    public User(){}

    public User(int id, String name, String bloodgroup, int mobilenumber, int zipcode, String email, String password) {
        this.id = id;
        this.name = name;
        this.bloodgroup = bloodgroup;
        this.mobilenumber = mobilenumber;
        this.zipcode = zipcode;
        this.email = email;
        this.password = password;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public int getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(int mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
