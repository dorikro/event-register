package com.example.eventregistration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Registration")
public class Registration {

    @Id
    private String id;
    private String fullName;
    private String workEmail;
    private String phoneNumber;

    public Registration() {
    }

    public Registration(String fullName, String workEmail, String phoneNumber) {
        this.fullName = fullName;
        this.workEmail = workEmail;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}