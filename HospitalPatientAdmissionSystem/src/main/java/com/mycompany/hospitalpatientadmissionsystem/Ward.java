/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalpatientadmissionsystem;

/**
 *
 * @author Student
 */

public class Ward {

    private final Bed[] beds;

    // Hospital ward contains exactly 20 beds
    public Ward() {
        beds = new Bed[20];

        for (int i = 0; i < beds.length; i++) {
            String bedId = String.format("B%02d", i + 1);
            beds[i] = new Bed(bedId);
        }
    }

    // Find a bed by its ID
    private Bed findBed(String bedId) {

        if (bedId == null) {
            return null;
        }

        for (Bed bed : beds) {
            if (bed.getBedId().equalsIgnoreCase(bedId.trim())) {
                return bed;
            }
        }

        return null;
    }

    // Allocate a bed to an inpatient
    public void allocateBed(String bedId, Inpatient inpatient) {

        if (inpatient == null) {
            System.out.println("Only inpatients can be allocated a bed.");
            return;
        }

        Bed bed = findBed(bedId);

        if (bed == null) {
            System.out.println("Bed " + bedId + " does not exist.");
            return;
        }

        if (bed.isOccupied()) {
            System.out.println("Bed " + bedId + " is already occupied.");
            return;
        }

        // Check whether the inpatient already has a bed
        if (inpatient.hasBedAllocated()) {
            System.out.println(inpatient.getFullName()
                    + " already has a bed: "
                    + inpatient.getBedNumber());
            return;
        }

        bed.assignPatient(inpatient);
        inpatient.setBedNumber(bed.getBedId());

        System.out.println("Bed " + bed.getBedId()
                + " allocated to " + inpatient.getFullName());
    }

    // Release a bed when patient is discharged
    public void releaseBed(String bedId) {

        Bed bed = findBed(bedId);

        if (bed == null) {
            System.out.println("Bed " + bedId + " does not exist.");
            return;
        }

        if (!bed.isOccupied()) {
            System.out.println("Bed " + bed.getBedId()
                    + " is already available.");
            return;
        }

        Inpatient patient = bed.getPatient();

        patient.setBedNumber(null);
        bed.releaseBed();

        System.out.println("Bed " + bed.getBedId()
                + " has been released.");
    }

    // Display available beds
    public void displayAvailableBeds() {

        System.out.println("\n========== AVAILABLE BEDS ==========");

        boolean found = false;

        for (Bed bed : beds) {
            if (!bed.isOccupied()) {
                System.out.print(bed.getBedId() + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No beds available.");
        } else {
            System.out.println();
        }

        System.out.println("====================================");
    }

    // Display occupied beds
    public void displayOccupiedBeds() {

        System.out.println("\n========== OCCUPIED BEDS ==========");

        boolean found = false;

        for (Bed bed : beds) {
            if (bed.isOccupied()) {
                System.out.println(
                        bed.getBedId() + " - "
                        + bed.getPatient().getFullName()
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No beds are currently occupied.");
        }

        System.out.println("===================================");
    }

    // Display complete 4 x 5 ward layout
    public void displayCompleteLayout() {

        System.out.println("\n========== COMPLETE WARD LAYOUT ==========");

        for (int i = 0; i < beds.length; i++) {

            Bed bed = beds[i];

            if (bed.isOccupied()) {
                System.out.print(
                        "[" + bed.getBedId() + " - "
                        + bed.getPatient().getFirstName() + "]\t"
                );
            } else {
                System.out.print(
                        "[" + bed.getBedId() + " - Available]\t"
                );
            }

            // 5 beds per row
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }

        System.out.println("===========================================");
    }

    // Check whether all 20 beds are occupied
    public boolean areAllBedsOccupied() {

        for (Bed bed : beds) {
            if (!bed.isOccupied()) {
                return false;
            }
        }

        return true;
    }
}