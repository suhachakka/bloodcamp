package com.example.BloodCamp.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int id;

    @NotNull
    @Size(min=5, max=10)
    private String name;

    private String bloodgroup;

    @NotNull
    @Pattern(regexp = "\\(\\d{3}\\)\\d{3}-\\d{4}" , message = "phone number must be(123)456-7890")
    private String mobilenumber;

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

    private String role;

     @ManyToMany
     private List<Event> event;

//default constructor
    public Donor(){}
//parameterized constructor
    public Donor(int id, String name, String bloodgroup, String mobilenumber, int zipcode, String email, String password, String confirmPassword,String role) {
        this.id = id;
        this.name = name;
        this.bloodgroup = bloodgroup;
        this.mobilenumber = mobilenumber;
        this.zipcode = zipcode;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role=role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        checkPassword();
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

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }

    public void checkPassword() {
        if(confirmPassword!= null && password!= null && !password.equals(confirmPassword)){
            confirmPassword=null;
        }
    }

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
