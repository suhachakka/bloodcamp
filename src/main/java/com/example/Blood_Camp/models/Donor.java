package com.example.Blood_Camp.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity

public class Donor {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min=5, max=10)
    private String name;

    private String bloodgroup;

    @NotNull
    @Pattern(regexp = "\\(\\d{3}\\)\\d{3-\\d{4}" , message = "phone number must be(123)456-7890")
    private int mobilenumber;

    @NotNull
    @Min(5)
    private int zipcode;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min=5 , max=10)
    private String password;


    @NotNull (message = "passwords do not match")
    @Transient
    private String confirmPassword;





    public Donor(){}

    public Donor(int id, String name, String bloodgroup, int mobilenumber, int zipcode, String email, String password, String confirmPassword) {
        this.id = id;
        this.name = name;
        this.bloodgroup = bloodgroup;
        this.mobilenumber = mobilenumber;
        this.zipcode = zipcode;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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