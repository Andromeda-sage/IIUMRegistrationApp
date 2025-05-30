package com.mycompany.iiumregistrationapp;

public class Main { 
	public static void main(String[] args) { 
    	Student s1 = new Student("2420919", "Ali", "Ahmad", "0123456789"); 
    	s1.registerCourse("BICS 1304"); 
    	s1.viewCourse(); 
  
    	Course c1 = new Course("BICS 1304", "OOP", 3); 
    	c1.displayInfo(); 
  
    	Lecturer l1 = new Lecturer("10567", "Dr.", "Dini Oktarina", "0198765432"); 
    	l1.addToSection("BICS 1304"); 
    	l1.viewAssignedCourse(); 
  
    	Registration r1 = new Registration("R001", "2420919", "BICS 1304", "2025-05-02"); 
    	r1.confirmRegistration(); 
    	r1.displayInfo(); 
  
    	Section sect = new Section("SECT 01", "BICS 1304", "Monday", "10AM", "ICT LAB 6"); 
    	sect.displayInfo(); 
	} 
} 

