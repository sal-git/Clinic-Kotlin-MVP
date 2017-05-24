package com.stephenlightcap.clinic.model.model_schema;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Germex on 5/23/2017.
 */

public class Appointments extends RealmObject {

    private Long date;
    private Doctor doctor;
    private String reasonForVisit;

    public Appointments() {
    }

    public Appointments(Long date, Doctor doctor, String reasonForVisit) {
        this.date = date;
        this.doctor = doctor;
        this.reasonForVisit = reasonForVisit;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }
}
