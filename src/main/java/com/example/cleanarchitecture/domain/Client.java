package com.example.cleanarchitecture.domain;

public class Client {

    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private String typeOfId;
    private int age;

    public Client() {
    }
    public Client(Long id, String firstName, String lastName, String city, String typeOfId, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.typeOfId = typeOfId;
        this.age = age;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getTypeOfId() {
        return typeOfId;
    }
    public void setTypeOfId(String typeOfId) {
        this.typeOfId = typeOfId;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
