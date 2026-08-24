/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

/**
 *
 * @author Student
 */

public class Patient {
    
    // Private fields - Information Hiding
    private String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory category;

    // Simple constructor - like yours but with correct fields
    public Patient(String patientId, String firstName, String lastName, int age,
                   String gender, String medicalCondition, PatientCategory category) {
        
        // Simple validation for marks
        if (patientId == null || patientId.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient ID required");
        }
        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Age must be 0-120");
        }

        this.patientId = patientId.trim().toUpperCase();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.medicalCondition = medicalCondition;
        this.category = category;
    }

    // Getters - same style as yours
    public String getPatientId() { return patientId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getMedicalCondition() { return medicalCondition; }
    public PatientCategory getCategory() { return category; }

    // Setters - needed for Update [4 Marks]
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setAge(int age) { this.age = age; }
    public void setGender(String gender) { this.gender = gender; }
    public void setMedicalCondition(String condition) { this.medicalCondition = condition; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Needed for Search and Display [4 Marks] - organised format
    public void displayDetails() {
        System.out.println("----------------------------------------");
        System.out.println("Patient ID      : " + patientId);
        System.out.println("Full Name       : " + getFullName());
        System.out.println("Age             : " + age);
        System.out.println("Gender          : " + gender);
        System.out.println("Medical Condition: " + medicalCondition);
        System.out.println("Category        : " + category.getDisplayName());
        System.out.println("----------------------------------------");
    }
}