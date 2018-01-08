package com.mysoft.entities;

public class Worker {
    private int id;
    private String firstName;
    private String lastName;
    private String status;

    public Worker(){
    }

    public Worker(int id, String firstName, String lastName, String status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id = " + id +
                ", firstName = '" + firstName + '\'' +
                ", last_name = '" + lastName + '\'' +
                ", status = '" + status + '\'' +
                '}';
    }
}
