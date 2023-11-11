package com.Elearning.models;

public class InstructorModel {
    private String courseName;
    private String courseDes;
    private String coursePrice;
    private int id;


    private byte[] imageResource;

    public InstructorModel(String courseName, int id , byte[] imageResource, String courseDes, String coursePrice) {
        this.courseName = courseName;
        this.courseDes = courseDes;
        this.coursePrice = coursePrice;
        this.id = id;
        this.imageResource = imageResource;
    }

    public InstructorModel(String courseName, int id, byte[] imageResource) {
        this.courseName = courseName;
        this.id = id;
        this.imageResource = imageResource;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public void setId(int id) {
        this.id = id;
    }
}
