package com.mycompany.iiumregistrationapp;

public class Registration implements Manageable { 
	private String regID; 
	private String studID; 
	private String courseCode; 
	private String regDate; 
	private String regStatus; 
  
	public Registration(String regID, String studID, String courseCode, String regDate) { 
    	this.regID = regID; 
    	this.studID = studID; 
    	this.courseCode = courseCode; 
    	this.regDate = regDate; 
    	this.regStatus = "Pending"; 
	} 
	 public String getRegID() {
        return regID;
    }
    
     public String getStudID() {
        return studID;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public String getRegDate() {
        return regDate;
    }
    
    public String getRegStatus() {
        return regStatus;
    }




  
	public void confirmRegistration() { 
    	regStatus = "Confirmed"; 
    	System.out.println("Registration confirmed for " + courseCode); 
	} 
  
	public void cancelRegistration() { 
    	regStatus = "Cancelled"; 
    	System.out.println("Registration cancelled for " + courseCode); 
	} 
  
	@Override 
	public void displayInfo() { 
    	System.out.println("RegID: " + regID + " | Student: " + studID + " | Course: " + courseCode + " | Status: " + regStatus); 
	} 
} 

