/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

/**
 *
 * @author Student
 */

public class Bed {

    private final String bedId;
    private Inpatient patient;

    public Bed(String bedId) {
        this.bedId = bedId;
        this.patient = null;
    }

    public String getBedId() {
        return bedId;
    }

    public Inpatient getPatient() {
        return patient;
    }

    public boolean isOccupied() {
        return patient != null;
    }

    public void assignPatient(Inpatient patient) {
        this.patient = patient;
    }

    public void releaseBed() {
        this.patient = null;
    }

    public void displayBed() {
        if (isOccupied()) {
            System.out.println(bedId + " - OCCUPIED - "
                    + patient.getFullName());
        } else {
            System.out.println(bedId + " - AVAILABLE");
        }
    }
}