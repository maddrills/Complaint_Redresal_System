package com.tryOne.AuthonBack.entity.complaint;

import jakarta.persistence.*;

@Entity
@Table(name = "pincodes")
public class Pincodes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "pincode")
    private String pinCode;

    public Pincodes() {
    }

    public Pincodes(String pinCode) {
        this.pinCode = pinCode;
    }

    public Pincodes(int id, String pinCode) {
        this.id = id;
        this.pinCode = pinCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "Pincodes{" +
                "id=" + id +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }
}
