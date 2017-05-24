package com.stephenlightcap.clinic.model.model_schema;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Germex on 5/21/2017.
 */
public class User extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;
    private double weight;
    private double height;
    private double BMI;

    //One to many
    public RealmList<Appointments> appointments;

    public User() {
    }

    public User(int id, String name, double weight, double height, int BMI) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.BMI = BMI;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public RealmList<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(RealmList<Appointments> appointments) {
        this.appointments = appointments;
    }
}
