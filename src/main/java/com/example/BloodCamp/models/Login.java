package com.example.BloodCamp.models;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;


public class Login {

    @NotEmpty(message = "Please enter the email")
    private String email;

    @NotEmpty(message = "Please enter the password")
    private String password;

    public Login(){}

    public Login(String email, String password) {
        this.email= email;
        this.password = password;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

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
