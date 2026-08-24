/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

/**
 *
 * @author Student
 */
import java.util.ArrayList;
import java.util.Comparator;

public class HospitalSystem {

    private ArrayList<Patient> patients;
    private final Ward ward;

    public HospitalSystem() {
        patients = new ArrayList<>();
        ward = new Ward();
    }

    // ================================
    // PATIENT REGISTRATION
    // ================================

    public void registerPatient(Patient patient) {

        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }

        // Prevent duplicate Patient IDs
        if (searchPatient(patient.getPatientId()) != null) {
            throw new IllegalArgumentException(
                    "Patient ID already exists: " + patient.getPatientId()
            );
        }

        patients.add(patient);

        System.out.println("Patient registered: "
                + patient.getFullName());
    }

    // ================================
    // SEARCH PATIENT
    // ================================

    public Patient searchPatient(String patientId) {

        if (patientId == null) {
            return null;
        }

        for (Patient patient : patients) {

            if (patient.getPatientId()
                    .equalsIgnoreCase(patientId.trim())) {

                return patient;
            }
        }

        return null;
    }

    // ================================
    // UPDATE PATIENT
    // ================================

    public boolean updatePatient(String patientId,
                                 String firstName,
                                 String lastName,
                                 int age,
                                 String gender,
                                 String medicalCondition) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setMedicalCondition(medicalCondition);

        return true;
    }

    // ================================
    // DELETE PATIENT
    // ================================

    public boolean deletePatient(String patientId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            return false;
        }

        // Do not delete an inpatient while a bed is allocated
        if (patient instanceof Inpatient) {

            Inpatient inpatient = (Inpatient) patient;

            if (inpatient.hasBedAllocated()) {
                throw new IllegalStateException(
                        "Release the patient's bed before deleting."
                );
            }
        }

        patients.remove(patient);

        return true;
    }

    // ================================
    // BED MANAGEMENT
    // ================================

    public void allocateBedToInpatient(String patientId, String bedId) {

        Patient patient = searchPatient(patientId);

        if (patient == null) {
            System.out.println("Patient not found: " + patientId);
            return;
        }

        if (!(patient instanceof Inpatient)) {
            System.out.println(
                    "Only inpatients can be allocated a hospital bed."
            );
            return;
        }

        Inpatient inpatient = (Inpatient) patient;

        ward.allocateBed(bedId, inpatient);
    }

    public void releaseBed(String bedId) {
        ward.releaseBed(bedId);
    }

    public void displayAvailableBeds() {
        ward.displayAvailableBeds();
    }

    public void displayOccupiedBeds() {
        ward.displayOccupiedBeds();
    }

    public void displayWardLayout() {
        ward.displayCompleteLayout();
    }

    public Ward getWard() {
        return ward;
    }

    // ================================
    // SORTING
    // ================================

    public void sortPatientsBySurname() {

        patients.sort(
                Comparator.comparing(
                        Patient::getLastName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );
    }

    public void sortPatientsById() {

        patients.sort(
                Comparator.comparing(
                        Patient::getPatientId,
                        String.CASE_INSENSITIVE_ORDER
                )
        );
    }

    // ================================
    // REPORTS
    // ================================

    public void displayAllPatients() {

        System.out.println("\n========== ALL REGISTERED PATIENTS ==========");

        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
        } else {
            for (Patient patient : patients) {
                patient.displayDetails();
            }
        }

        System.out.println("=============================================");
    }

    public void displayAllAvailableBeds() {
        ward.displayAvailableBeds();
    }

    public void displayAllOccupiedBeds() {
        ward.displayOccupiedBeds();
    }

    public void displayTotalPatients() {

        System.out.println(
                "\nTotal Registered Patients: " + patients.size()
        );
    }

    public void displayTotalOccupiedBeds() {

        int occupiedBeds = 0;

        for (Patient patient : patients) {

            if (patient instanceof Inpatient) {

                Inpatient inpatient = (Inpatient) patient;

                if (inpatient.hasBedAllocated()) {
                    occupiedBeds++;
                }
            }
        }

        System.out.println(
                "\nTotal Occupied Beds: " + occupiedBeds
        );
    }

    public void displayOccupancyPercentage() {

        int occupiedBeds = 0;

        for (Patient patient : patients) {

            if (patient instanceof Inpatient
                    && ((Inpatient) patient).hasBedAllocated()) {

                occupiedBeds++;
            }
        }

        double occupancyPercentage =
                (occupiedBeds / 20.0) * 100;

        System.out.println(
                "\nWard Occupancy: "
                + occupancyPercentage + "%"
        );
    }

    public void generateReports() {

        System.out.println("\n========================================");
        System.out.println("          HOSPITAL REPORTS");
        System.out.println("========================================");

        displayAllPatients();
        displayAllAvailableBeds();
        displayAllOccupiedBeds();
        displayTotalPatients();
        displayTotalOccupiedBeds();
        displayOccupancyPercentage();

        System.out.println("========================================");
    }
}