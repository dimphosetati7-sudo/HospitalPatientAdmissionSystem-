/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

/**
 *
 * @author Student
 */

public class Inpatient extends Patient {

    private int wardNumber;
    private String bedNumber;

    // Constructor with ward and bed
    public Inpatient(String patientId, String firstName, String lastName,
                     int age, String gender, String medicalCondition,
                     int wardNumber, String bedNumber) {

        super(patientId, firstName, lastName, age, gender,
              medicalCondition, PatientCategory.INPATIENT);

        this.wardNumber = wardNumber;

        if (bedNumber == null || bedNumber.trim().isEmpty()) {
            this.bedNumber = null;
        } else {
            this.bedNumber = bedNumber.trim().toUpperCase();
        }
    }

    // Constructor without a bed
    public Inpatient(String patientId, String firstName, String lastName,
                     int age, String gender, String medicalCondition) {

        this(patientId, firstName, lastName, age, gender,
             medicalCondition, 1, null);
    }

    // Getters
    public int getWardNumber() {
        return wardNumber;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    // Setter
    public void setBedNumber(String bedNumber) {
        if (bedNumber == null || bedNumber.trim().isEmpty()) {
            this.bedNumber = null;
        } else {
            this.bedNumber = bedNumber.trim().toUpperCase();
        }
    }

    // Check if a bed has been allocated
    public boolean hasBedAllocated() {
        return bedNumber != null;
    }

    // Display inpatient details
    @Override
    public void displayDetails() {

        super.displayDetails();

        System.out.println("Ward Number     : " + wardNumber);

        if (hasBedAllocated()) {
            System.out.println("Bed Number      : " + bedNumber);
        } else {
            System.out.println("Bed Number      : Not Allocated");
        }

        System.out.println("----------------------------------------");
    }
}