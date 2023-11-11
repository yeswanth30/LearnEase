package com.Elearning.models;

public class CourseModel {
    private String courseName;
    private String coursedes;
    private int price;
    private int id;

    private byte[] imageResource;
    public CourseModel(String courseName,  byte[] imageResource, int id) {
        this.courseName = courseName;
        this.id=id;
        this.imageResource = imageResource;
    }
//public CourseModel(String courseName) {
//    this.courseName = courseName;
//}



    public CourseModel(String courseName, int id,String coursedes,int courseprice,  byte[] imageResource ) {
        this.courseName = courseName;
        this.coursedes = coursedes;
        this.price = courseprice;
        this.id=id;
        this.imageResource = imageResource;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImageResource() {
        return imageResource;
    }

    public void setImageResource(byte[] imageResource) {
        this.imageResource = imageResource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursedes() {
        return coursedes;
    }

    public void setCoursedes(String coursedes) {
        this.coursedes = coursedes;
    }
}
