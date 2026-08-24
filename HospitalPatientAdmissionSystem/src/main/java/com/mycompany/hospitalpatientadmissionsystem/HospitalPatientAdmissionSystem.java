/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hospitalpatientadmissionsystem;

/**
 *
 * @author Student
 */

import java.util.Scanner;

public class HospitalPatientAdmissionSystem {

    public static void main(String[] args) {

        HospitalSystem hospital = new HospitalSystem();
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            
            System.out.println("========================================");
            System.out.println("   HOSPITAL PATIENT ADMISSION SYSTEM");
            System.out.println("========================================");
            
            while (running) {
                
                System.out.println("\n========== MAIN MENU ==========");
                System.out.println("1. Register Patient");
                System.out.println("2. Search Patient");
                System.out.println("3. Update Patient");
                System.out.println("4. Delete Patient");
                System.out.println("5. Allocate Bed");
                System.out.println("6. Release Bed");
                System.out.println("7. Display Available Beds");
                System.out.println("8. Display Occupied Beds");
                System.out.println("9. Display Ward Layout");
                System.out.println("10. Display Reports");
                System.out.println("11. Sort Patients by Surname");
                System.out.println("12. Sort Patients by Patient ID");
                System.out.println("0. Exit");
                System.out.println("==============================");
                
                System.out.print("Enter your choice: ");
                
                String choice = scanner.nextLine();
                
                try {
                    
                    switch (choice) {
                        
                        case "1" -> registerPatient(scanner, hospital);
                            
                        case "2" -> searchPatient(scanner, hospital);
                            
                        case "3" -> updatePatient(scanner, hospital);
                            
                        case "4" -> deletePatient(scanner, hospital);
                            
                        case "5" -> allocateBed(scanner, hospital);
                            
                        case "6" -> releaseBed(scanner, hospital);
                            
                        case "7" -> hospital.displayAvailableBeds();
                            
                        case "8" -> hospital.displayOccupiedBeds();
                            
                        case "9" -> hospital.displayWardLayout();
                            
                        case "10" -> hospital.generateReports();
                            
                        case "11" -> {
                            hospital.sortPatientsBySurname();
                            System.out.println("Patients sorted by surname.");
                        }
                            
                        case "12" -> {
                            hospital.sortPatientsById();
                            System.out.println("Patients sorted by Patient ID.");
                        }
                            
                        case "0" -> {
                            running = false;
                            System.out.println("Thank you for using the Hospital Patient Admission System.");
                        }
                            
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                    
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    // Register a patient
    private static void registerPatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.print("Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Medical Condition: ");
        String condition = scanner.nextLine();

        System.out.println("Category:");
        System.out.println("1. Inpatient");
        System.out.println("2. Outpatient");
        System.out.println("3. Emergency");

        System.out.print("Choose category: ");
        String categoryChoice = scanner.nextLine();

        switch (categoryChoice) {
            case "1" ->                 {
                    Inpatient patient = new Inpatient(
                            id,
                            firstName,
                            lastName,
                            age,
                            gender,
                            condition
                    );      hospital.registerPatient(patient);
                }
            case "2" ->                 {
                    Patient patient = new Patient(
                            id,
                            firstName,
                            lastName,
                            age,
                            gender,
                            condition,
                            PatientCategory.OUTPATIENT
                    );      hospital.registerPatient(patient);
                }
            case "3" ->                 {
                    Patient patient = new Patient(
                            id,
                            firstName,
                            lastName,
                            age,
                            gender,
                            condition,
                            PatientCategory.EMERGENCY
                    );      hospital.registerPatient(patient);
                }
            default -> System.out.println("Invalid category.");
        }
    }

    // Search for a patient
    private static void searchPatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        Patient patient = hospital.searchPatient(id);

        if (patient != null) {
            patient.displayDetails();
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Update patient
    private static void updatePatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        System.out.print("New First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("New Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("New Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("New Gender: ");
        String gender = scanner.nextLine();

        System.out.print("New Medical Condition: ");
        String condition = scanner.nextLine();

        boolean updated = hospital.updatePatient(
                id,
                firstName,
                lastName,
                age,
                gender,
                condition
        );

        if (updated) {
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Delete patient
    private static void deletePatient(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();

        boolean deleted = hospital.deletePatient(id);

        if (deleted) {
            System.out.println("Patient deleted successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    // Allocate bed
    private static void allocateBed(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine();

        System.out.print("Bed ID: ");
        String bedId = scanner.nextLine();

        hospital.allocateBedToInpatient(
                patientId,
                bedId
        );
    }

    // Release bed
    private static void releaseBed(
            Scanner scanner,
            HospitalSystem hospital) {

        System.out.print("Bed ID: ");
        String bedId = scanner.nextLine();

        hospital.releaseBed(bedId);
    }
}