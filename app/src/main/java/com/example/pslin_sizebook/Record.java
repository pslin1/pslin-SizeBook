package com.example.pslin_sizebook;

import java.util.Date;

/**
 * Created by Pierre Lin on 1/28/2017.
 */

public class Record {
    private String name;
    private String date;
    private String neck;
    private String bust;
    private String chest;
    private String waist;
    private String hip;
    private String inseam;
    private String comment;

    public Record(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInseam() {
        return inseam;
    }

    public void setInseam(String inseam) {
        this.inseam = inseam;
    }

    @Override
    public String toString() {
        return "Name: " + name + " | " + "Date: " + this.getDate() + " | " + "Neck: " + this.getNeck() + " | " + "Bust: " + this.getBust() + " | " + "Chest: " + this.getChest() + " | " + "Waist: " + this.getWaist() + " | " + "Hip: " + this.getHip() + " | " + "Inseam: " + this.getInseam() + " | " + "Comments: " + this.getComment();
    }
}
