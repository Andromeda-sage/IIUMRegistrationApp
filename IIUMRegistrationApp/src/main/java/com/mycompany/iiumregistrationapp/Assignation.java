package com.mycompany.iiumregistrationapp;

public class Assignation implements Manageable {
    private String assID;
    private String lectID;
    private String courseCode;
    private String assDate;
    private String assStatus;

    public Assignation(String assID, String lectID, String courseCode, String assDate) {
        this.assID = assID;
        this.lectID = lectID;
        this.courseCode = courseCode;
        this.assDate = assDate;
        this.assStatus = "Pending";
    }

    public void confirmAssignation() {
        assStatus = "Confirmed";
        System.out.println("Assignation confirmed for " + courseCode);
    }

    public void cancelAssignation() {
        assStatus = "Cancelled";
        System.out.println("Assignation cancelled for " + courseCode);
    }

    public String getAssID() {
        return assID;
    }

    public String getLectID() {
        return lectID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getAssDate() {
        return assDate;
    }

    public String getAssStatus() {
        return assStatus;
    }

    @Override
    public void displayInfo() {
        System.out.println("AssID: " + assID +
                           " | Lecturer: " + lectID +
                           " | Course: " + courseCode +
                           " | Date: " + assDate +
                           " | Status: " + assStatus);
    }
}
