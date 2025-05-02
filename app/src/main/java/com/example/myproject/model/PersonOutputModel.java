package com.example.myproject.model;

public class PersonOutputModel {
    public String message;
    public boolean status;
    public PersonModel person;

    public PersonOutputModel(String message, boolean status, PersonModel person) {
        this.message = message;
        this.status = status;
        this.person = person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }
}
