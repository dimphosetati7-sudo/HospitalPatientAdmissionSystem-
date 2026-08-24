/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
import com.mycompany.hospitalpatientadmissionsystem.HospitalSystem;
import com.mycompany.hospitalpatientadmissionsystem.Inpatient;
import com.mycompany.hospitalpatientadmissionsystem.Patient;
import com.mycompany.hospitalpatientadmissionsystem.PatientCategory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalSystemTest {

    // 1. Test registering a patient
    @Test
    public void testRegisterPatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P100",
                "John",
                "Mokoena",
                30,
                "Male",
                "Fever",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        Patient found = hospital.searchPatient("P100");

        assertNotNull(found);
        assertEquals("John", found.getFirstName());
        assertEquals("Mokoena", found.getLastName());
    }

    // 2. Test searching for a patient
    @Test
    public void testSearchPatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P101",
                "Mary",
                "Dlamini",
                25,
                "Female",
                "Headache",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        Patient result = hospital.searchPatient("P101");

        assertNotNull(result);
        assertEquals("P101", result.getPatientId());
    }

    // 3. Test updating patient details
    @Test
    public void testUpdatePatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P102",
                "Peter",
                "Smith",
                40,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        boolean updated = hospital.updatePatient(
                "P102",
                "Peter",
                "Jones",
                41,
                "Male",
                "Pneumonia"
        );

        assertTrue(updated);
        assertEquals("Jones",
                hospital.searchPatient("P102").getLastName());
        assertEquals(41,
                hospital.searchPatient("P102").getAge());
    }

    // 4. Test deleting a patient
    @Test
    public void testDeletePatient() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient = new Patient(
                "P103",
                "Sarah",
                "Brown",
                32,
                "Female",
                "Migraine",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient);

        boolean deleted = hospital.deletePatient("P103");

        assertTrue(deleted);
        assertNull(hospital.searchPatient("P103"));
    }

    // 5. Test allocating a bed
    @Test
    public void testAllocateBed() {

        HospitalSystem hospital = new HospitalSystem();

        Inpatient patient = new Inpatient(
                "P104",
                "Alice",
                "Molefe",
                28,
                "Female",
                "Surgery"
        );

        hospital.registerPatient(patient);

        hospital.allocateBedToInpatient("P104", "B01");

        assertEquals("B01", patient.getBedNumber());
        assertTrue(patient.hasBedAllocated());
    }

    // 6. Test releasing a bed
    @Test
    public void testReleaseBed() {

        HospitalSystem hospital = new HospitalSystem();

        Inpatient patient = new Inpatient(
                "P105",
                "David",
                "Mokoena",
                50,
                "Male",
                "Surgery"
        );

        hospital.registerPatient(patient);

        hospital.allocateBedToInpatient("P105", "B02");

        hospital.releaseBed("B02");

        assertFalse(patient.hasBedAllocated());
        assertNull(patient.getBedNumber());
    }

    // 7. Test preventing duplicate Patient IDs
    @Test
    public void testDuplicatePatientId() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient1 = new Patient(
                "P106",
                "John",
                "Smith",
                30,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P106",
                "Peter",
                "Jones",
                40,
                "Male",
                "Fever",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient1);

        assertThrows(
                IllegalArgumentException.class,
                () -> hospital.registerPatient(patient2)
        );
    }

    // 8. Test preventing allocation of an occupied bed
    @Test
    public void testOccupiedBed() {

        HospitalSystem hospital = new HospitalSystem();

        Inpatient patient1 = new Inpatient(
                "P107",
                "Patient",
                "One",
                30,
                "Male",
                "Surgery"
        );

        Inpatient patient2 = new Inpatient(
                "P108",
                "Patient",
                "Two",
                35,
                "Female",
                "Surgery"
        );

        hospital.registerPatient(patient1);
        hospital.registerPatient(patient2);

        hospital.allocateBedToInpatient("P107", "B03");
        hospital.allocateBedToInpatient("P108", "B03");

        assertEquals("B03", patient1.getBedNumber());
        assertNull(patient2.getBedNumber());
    }

    // 9. Test preventing allocation when all beds are occupied
    @Test
    public void testAllBedsOccupied() {

        HospitalSystem hospital = new HospitalSystem();

        // Fill all 20 beds
        for (int i = 1; i <= 20; i++) {

            String patientId = String.format("P%03d", i);

            String bedId = String.format("B%02d", i);

            Inpatient patient = new Inpatient(
                    patientId,
                    "Patient",
                    "Number" + i,
                    30,
                    "Male",
                    "Treatment"
            );

            hospital.registerPatient(patient);

            hospital.allocateBedToInpatient(
                    patientId,
                    bedId
            );
        }

        // Try to allocate another patient
        Inpatient extraPatient = new Inpatient(
                "P999",
                "Extra",
                "Patient",
                25,
                "Female",
                "Treatment"
        );

        hospital.registerPatient(extraPatient);

        hospital.allocateBedToInpatient(
                "P999",
                "B01"
        );

        assertFalse(extraPatient.hasBedAllocated());
    }

    // 10. Test sorting patients by surname
    @Test
    public void testSortPatientsBySurname() {

        HospitalSystem hospital = new HospitalSystem();

        Patient patient1 = new Patient(
                "P110",
                "John",
                "Zulu",
                30,
                "Male",
                "Flu",
                PatientCategory.OUTPATIENT
        );

        Patient patient2 = new Patient(
                "P111",
                "Mary",
                "Adams",
                25,
                "Female",
                "Fever",
                PatientCategory.OUTPATIENT
        );

        hospital.registerPatient(patient1);
        hospital.registerPatient(patient2);

        hospital.sortPatientsBySurname();

        assertEquals(
                "Adams",
                hospital.searchPatient("P111").getLastName()
        );
    }
}