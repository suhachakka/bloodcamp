package com.example.Blood_Camp.models;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;


public class Login {

    @NotEmpty(message = "Please enter the name")
    private String name;

    @NotEmpty(message = "Please enter the password")
    private String password;

    public Login(){}

    public Login(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
