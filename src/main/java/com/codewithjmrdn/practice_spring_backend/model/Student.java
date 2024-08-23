package com.codewithjmrdn.practice_spring_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity
public class Student {

    @Id
    @GeneratedValue
    int stud_id;
    String stud_name;
    String stud_email;
    String stud_gender;
    String stud_section;

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public String getStud_name() {
        return stud_name;
    }

    public void setStud_name(String stud_name) {
        this.stud_name = stud_name;
    }

    public String getStud_email() {
        return stud_email;
    }

    public void setStud_email(String stud_email) {
        this.stud_email = stud_email;
    }

    public String getStud_gender() {
        return stud_gender;
    }

    public void setStud_gender(String stud_gender) {
        this.stud_gender = stud_gender;
    }

    public String getStud_section() {
        return stud_section;
    }

    public void setStud_section(String stud_section) {
        this.stud_section = stud_section;
    }
}
