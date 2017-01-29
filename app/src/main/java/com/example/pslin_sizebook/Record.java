package com.example.pslin_sizebook;

/**
 * Created by Pierre Lin on 1/28/2017.
 */

public class Record {
    private String name;
    private float neck;
    private float bust;
    private float chest;
    private float waist;
    private float hip;

    public Record(String name)  {
        this.setName(name);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getInseam() {
        return inseam;
    }

    public void setInseam(float inseam) {
        this.inseam = inseam;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNeck() {
        return neck;
    }

    public void setNeck(float neck) {
        this.neck = neck;
    }

    public float getBust() {
        return bust;
    }

    public void setBust(float bust) {
        this.bust = bust;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    private float inseam;
    private String comment;

}
