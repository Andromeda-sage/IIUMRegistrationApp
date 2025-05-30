package com.mycompany.iiumregistrationapp;

public class Course implements Manageable { 
	private String courseCode; 
	private String courseName; 
	private int courseChr; 
  
	public Course(String courseCode, String courseName, int courseChr) { 
    	this.courseCode = courseCode; 
    	this.courseName = courseName; 
    	this.courseChr = courseChr; 
	} 
	public String getCourseCode() {
        return courseCode;
    }


    public String getCourseName() {
        return courseName;
    }
    
     public int getCreditHours() {
        return courseChr;
    }


  
	public void addCourse() { 
    	System.out.println("Course " + courseCode + " added."); 
	} 
  
	public void deleteCourse() { 
    	System.out.println("Course " + courseCode + " deleted."); 
	} 
  
	public void modifyCourse(String newName, int newChr) { 
    	this.courseName = newName; 
    	this.courseChr = newChr; 
    	System.out.println("Course modified."); 
	} 
  
	@Override 
	public void displayInfo() { 
    	System.out.println(courseCode + " - " + courseName + " (" + courseChr + " CHR)"); 
	} 

} 

