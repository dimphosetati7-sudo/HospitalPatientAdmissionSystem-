/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

/**
 *
 * @author Student
 */
public enum PatientCategory {

    EMERGENCY("Emergency"),
    INPATIENT("Inpatient"),
    OUTPATIENT("Outpatient");

    private final String displayName;

    PatientCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}