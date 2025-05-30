package com.mycompany.iiumregistrationapp;

import java.util.ArrayList; 
import java.util.List; 
  
public class Student extends Person { 
	private String studID; 
	private ArrayList<String> registeredCourses; 
  
	public Student(String studID, String firstName, String lastName, String phoneNo) { 
    	super(firstName, lastName, phoneNo); 
    	this.studID = studID; 
    	this.registeredCourses = new ArrayList(); 
	} 


	public String getStudID() {
        return studID;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }




  
	public void registerCourse(String courseCode) { 
    	if (!registeredCourses.contains(courseCode)) { 
        	registeredCourses.add(courseCode); 
        	System.out.println(courseCode + " registered successfully."); 
    	} 
	} 
  
	public void dropCourse(String courseCode) { 
    	if (registeredCourses.remove(courseCode)) { 
        	System.out.println(courseCode + " dropped successfully."); 
    	} 
	} 
  
	@Override 
	public void viewCourse() { 
    	System.out.println("Courses for student " + studID + ": " + registeredCourses); 
	} 
  
	@Override 
	public void displayInfo() { 
    	System.out.println("Student: " + firstName + " " + lastName + " (" + studID + ")"); 
	} 
	 public ArrayList<String> getRegisteredCourses() {
        return registeredCourses;
    }


} 

