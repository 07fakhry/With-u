package com.example.singuplogin;

public class Contact {
    private String name;
    private String specialty;
    private String number;

    private String phoneNumber;
    public Contact(String name, String specialty, String number) {
        this.name = name;
        this.specialty = specialty;
        this.number = number;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getNumber() {
        return number;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}