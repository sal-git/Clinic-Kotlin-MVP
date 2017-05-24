package com.stephenlightcap.clinic.model.model_schema;

import io.realm.RealmObject;

/**
 * Created by Germex on 5/21/2017.
 */

public class Doctor extends RealmObject {

    private String name;
    private String study;
    private String image;
    private String description;

    public Doctor() {
    }

    public Doctor(String name, String study, String image, String description) {
        this.name = name;
        this.study = study;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
