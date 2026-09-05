package hospital;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HospitalSystem {

    private static Scanner scanner = new Scanner(System.in);

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();

    // Each patient ID has its own visit history
    private static Map<Integer, VisitHistory> visitHistories = new HashMap<>();

    public static void main(String[] args) {

        int choice;

        do {

            displayMenu();

            choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerPatient();
                    break;

                case 2:
                    searchPatient();
                    break;

                case 3:
                    deletePatient();
                    break;

                case 4:
                    displayPatients();
                    break;

                case 5:
                    addEmergencyPatient();
                    break;

                case 6:
                    treatNextPatient();
                    break;

                case 7:
                    emergencyQueue.displayQueue();
                    break;

                case 8:
                    addTreatment();
                    break;

                case 9:
                    removeLatestTreatment();
                    break;

                case 10:
                    treatmentStack.displayTreatments();
                    break;

                case 11:
                    addVisit();
                    break;

                case 12:
                    searchVisit();
                    break;

                case 13:
                    removeVisit();
                    break;

                case 14:
                    displayVisitHistory();
                    break;

                case 0:
                    System.out.println("\nThank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n==============================================");
        System.out.println("       MINI HOSPITAL EMERGENCY SYSTEM");
        System.out.println("==============================================");

        System.out.println("1.  Register Patient");
        System.out.println("2.  Search Patient");
        System.out.println("3.  Delete Patient");
        System.out.println("4.  Display All Patients");

        System.out.println("----------------------------------------------");

        System.out.println("5.  Add Patient to Emergency Queue");
        System.out.println("6.  Treat Next Patient");
        System.out.println("7.  Display Emergency Queue");

        System.out.println("----------------------------------------------");

        System.out.println("8.  Add Treatment Record");
        System.out.println("9.  Remove Latest Treatment");
        System.out.println("10. Display Treatment History");

        System.out.println("----------------------------------------------");

        System.out.println("11. Add Patient Visit");
        System.out.println("12. Search Visit");
        System.out.println("13. Remove Visit");
        System.out.println("14. Display Visit History");

        System.out.println("----------------------------------------------");

        System.out.println("0.  Exit");

        System.out.println("==============================================");
    }

    // ==========================================
    // PATIENT BST OPERATIONS
    // ==========================================

    private static void registerPatient() {

        System.out.println("\n--- Register Patient ---");

        int id = readInt("Enter Patient ID: ");

        if (patientBST.search(id) != null) {
            System.out.println("Patient ID already exists.");
            return;
        }

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        int age = readInt("Enter Age: ");

        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();

        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(
                id,
                name,
                age,
                contact,
                condition
        );

        patientBST.insert(patient);

        // Create visit history for this patient
        visitHistories.put(id, new VisitHistory());

        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {

        System.out.println("\n--- Search Patient ---");

        int id = readInt("Enter Patient ID: ");

        Patient patient = patientBST.search(id);

        if (patient != null) {

            System.out.println("\nPatient found:");
            System.out.println(patient);

        } else {

            System.out.println("Patient not found.");
        }
    }

    private static void deletePatient() {

        System.out.println("\n--- Delete Patient ---");

        int id = readInt("Enter Patient ID: ");

        Patient patient = patientBST.search(id);

        if (patient == null) {

            System.out.println("Patient not found.");
            return;
        }

        patientBST.delete(id);

        visitHistories.remove(id);

        System.out.println("Patient deleted successfully.");
    }

    private static void displayPatients() {

        System.out.println("\n--- All Patients ---");

        patientBST.inOrder();
    }

    // ==========================================
    // EMERGENCY QUEUE OPERATIONS
    // ==========================================

    private static void addEmergencyPatient() {

        System.out.println("\n--- Add Patient to Emergency Queue ---");

        int id = readInt("Enter Patient ID: ");

        Patient patient = patientBST.search(id);

        if (patient == null) {

            System.out.println("Patient not found. Register patient first.");

            return;
        }

        emergencyQueue.enqueue(patient);

        System.out.println(
                "Patient added to emergency queue successfully."
        );
    }

    private static void treatNextPatient() {

        System.out.println("\n--- Treat Next Patient ---");

        Patient patient = emergencyQueue.dequeue();

        if (patient == null) {
            return;
        }

        System.out.println("Now treating:");
        System.out.println(patient);

        System.out.println("Treatment completed.");
    }

    // ==========================================
    // TREATMENT STACK OPERATIONS
    // ==========================================

    private static void addTreatment() {

        System.out.println("\n--- Add Treatment Record ---");

        int patientId = readInt("Enter Patient ID: ");

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        System.out.print("Enter treatment record: ");

        String treatment = scanner.nextLine();

        String record =
                "Patient ID: " + patientId +
                " | Patient: " + patient.getPatientName() +
                " | Treatment: " + treatment;

        treatmentStack.push(record);

        System.out.println("Treatment record added.");
    }

    private static void removeLatestTreatment() {

        System.out.println("\n--- Remove Latest Treatment ---");

        String record = treatmentStack.pop();

        if (record != null) {

            System.out.println("Removed treatment:");
            System.out.println(record);
        }
    }

    // ==========================================
    // VISIT LINKED LIST OPERATIONS
    // ==========================================

    private static VisitHistory getVisitHistory(int patientId) {

        if (!visitHistories.containsKey(patientId)) {

            visitHistories.put(
                    patientId,
                    new VisitHistory()
            );
        }

        return visitHistories.get(patientId);
    }

    private static void addVisit() {

        System.out.println("\n--- Add Patient Visit ---");

        int patientId = readInt("Enter Patient ID: ");

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        int visitId = readInt("Enter Visit ID: ");

        System.out.print("Enter Visit Date: ");
        String date = scanner.nextLine();

        System.out.print("Enter Doctor Name: ");
        String doctor = scanner.nextLine();

        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();

        Visit visit = new Visit(
                visitId,
                date,
                doctor,
                diagnosis,
                treatment
        );

        VisitHistory history = getVisitHistory(patientId);

        history.addVisit(visit);

        System.out.println("Visit added successfully.");
    }

    private static void searchVisit() {

        System.out.println("\n--- Search Visit ---");

        int patientId = readInt("Enter Patient ID: ");

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        int visitId = readInt("Enter Visit ID: ");

        VisitHistory history = getVisitHistory(patientId);

        Visit visit = history.searchVisit(visitId);

        if (visit != null) {

            System.out.println("\nVisit found:");
            System.out.println(visit);

        } else {

            System.out.println("Visit not found.");
        }
    }

    private static void removeVisit() {

        System.out.println("\n--- Remove Visit ---");

        int patientId = readInt("Enter Patient ID: ");

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        int visitId = readInt("Enter Visit ID: ");

        VisitHistory history = getVisitHistory(patientId);

        boolean removed = history.removeVisit(visitId);

        if (removed) {

            System.out.println("Visit removed successfully.");

        } else {

            System.out.println("Visit not found.");
        }
    }

    private static void displayVisitHistory() {

        System.out.println("\n--- Display Patient Visit History ---");

        int patientId = readInt("Enter Patient ID: ");

        Patient patient = patientBST.search(patientId);

        if (patient == null) {

            System.out.println("Patient not found.");

            return;
        }

        VisitHistory history = getVisitHistory(patientId);

        history.displayHistory();
    }

    // ==========================================
    // INPUT VALIDATION
    // ==========================================

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }
}