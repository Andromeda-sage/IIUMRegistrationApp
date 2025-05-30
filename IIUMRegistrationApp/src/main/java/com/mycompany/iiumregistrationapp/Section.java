package com.mycompany.iiumregistrationapp;

public class Section implements Manageable { 
	private String sectNo; 
	private String courseCode; 
	private String sectDay; 
	private String sectTime; 
	private String sectVenue; 
  
	public Section(String sectNo, String courseCode, String sectDay, String sectTime, String sectVenue) { 
    	this.sectNo = sectNo; 
    	this.courseCode = courseCode; 
    	this.sectDay = sectDay; 
    	this.sectTime = sectTime; 
    	this.sectVenue = sectVenue; 
	} 
  
	public void addSection() { 
    	System.out.println("Section " + sectNo + " added."); 
	} 
        
        public String getSection() {
        return sectNo;
        }
  
	public void updateSection(String newDay, String newTime, String newVenue) { 
    	this.sectDay = newDay; 
    	this.sectTime = newTime; 
    	this.sectVenue = newVenue; 
    	System.out.println("Section " + sectNo + " updated."); 
	} 
  
	public void deleteSection() { 
    	System.out.println("Section " + sectNo + " deleted."); 
	} 
  
	@Override 
	public void displayInfo() { 
    	System.out.println("Section " + sectNo + " for " + courseCode + " on " + sectDay + " at " + sectTime + " in " + sectVenue); 
	} 
} 

