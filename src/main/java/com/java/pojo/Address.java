package com.java.pojo;

public class Address {

    private String homeAddress;

    private String schoolAddress;

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "homeAddress='" + homeAddress + '\'' +
                ", schoolAddress='" + schoolAddress + '\'' +
                '}';
    }

    public Address(String homeAddress, String schoolAddress) {
        this.homeAddress = homeAddress;
        this.schoolAddress = schoolAddress;
    }

    public String gethomeAddress() {
        return homeAddress;
    }

    public void sethomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getschoolAddress() {
        return schoolAddress;
    }

    public void setschoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }
}
