package com.mycompany.iiumregistrationapp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class FileHandler {

    // Save list of registrations to CSV file
    public static void saveRegistrationsToFile(List<Registration> registrations, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Registration r : registrations) {
                String line = String.join(",",
                        r.getRegID(),
                        r.getStudID(),
                        r.getCourseCode(),
                        r.getRegDate(),
                        r.getRegStatus(),
                        r.getSection() != null ? r.getSection() : ""
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Registrations saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving registrations: " + e.getMessage());
        }
    }

    // Load list of registrations from CSV file
    public static List<Registration> loadRegistrationsFromFile(String filename) {
        List<Registration> registrations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",", -1); // keep empty tokens

                if (tokens.length >= 5) {
                    String regID = tokens[0];
                    String studID = tokens[1];
                    String courseCode = tokens[2];
                    String regDate = tokens[3];
                    String regStatus = tokens[4];
                    String section = tokens.length > 5 ? tokens[5] : null;

                    Registration r = new Registration(regID, studID, courseCode, regDate, section);

                    // Set status manually if not default
                    if (!"Pending".equalsIgnoreCase(regStatus)) {
                        if ("Confirmed".equalsIgnoreCase(regStatus)) {
                            r.confirmRegistration();
                        } else if ("Cancelled".equalsIgnoreCase(regStatus)) {
                            r.cancelRegistration();
                        }
                    }

                    registrations.add(r);
                }
            }

            System.out.println("Registrations loaded from " + filename);

        } catch (IOException e) {
            System.err.println("Error loading registrations: " + e.getMessage());
        }

        return registrations;
    }
}


