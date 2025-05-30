package com.mycompany.iiumregistrationapp;

import java.util.ArrayList; 
import java.util.List; 
  
public class Lecturer extends Person { 
	private String lectID; 
	private List<Course> assignedCourses; 
  
	public Lecturer(String lectID, String firstName, String lastName, String phoneNo) { 
    	super(firstName, lastName, phoneNo); 
    	this.lectID = lectID; 
    	this.assignedCourses = new ArrayList<>(); 
	} 
  
	public void addLecturer() { 
    	System.out.println("Lecturer " + lectID + " added."); 
	} 

        public void assignCourse(Course course) {
            if (!assignedCourses.contains(course)) {
                assignedCourses.add(course);
                }
            }        
        
        public String getLectID() {
        return lectID;
        }
        
        public List<Course> getAssignedCourses() { 
    	return assignedCourses; 
	} 
        
        public void addToSection(Course course) {
            if (!assignedCourses.contains(course)) {
                assignedCourses.add(course);
                System.out.println("Assigned to course " + course.getCourseCode());
                }
            }
 
  
        public void viewAssignedCourse() {
            System.out.println("Courses assigned to Lecturer " + lectID + ":");
            for (Course c : assignedCourses) {
                System.out.println(" - " + c.getCourseCode() + ": " + c.getCourseName()); // Assuming getCourseName() exists
                }
            }

  
	@Override 
	public void viewCourse() { 
    	viewAssignedCourse(); 
	} 
  
	@Override 
	public void displayInfo() { 
    	System.out.println("Lecturer: " + firstName + " " + lastName + " (" + lectID + ")"); 
	} 

    void addToSection(String bics_1304) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
} 


