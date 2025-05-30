package com.mycompany.iiumregistrationapp;

public abstract class Person implements Manageable { 
	protected String firstName; 
	protected String lastName; 
	protected String phoneNo; 
  
	public Person(String firstName, String lastName, String phoneNo) { 
    	this.firstName = firstName; 
    	this.lastName = lastName; 
    	this.phoneNo = phoneNo; 
	} 
  
	public abstract void viewCourse(); 
} 

