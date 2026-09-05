package hospital;
import java.util.Scanner;

public class HospitalSystem {

    private static Scanner scanner = new Scanner(System.in);

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();

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
                    patientBST.inOrder();
                    break;

                case 0:
                    System.out.println("Thank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }

    private static void displayMenu() {

        System.out.println("\n======================================");
        System.out.println(" MINI HOSPITAL EMERGENCY SYSTEM");
        System.out.println("======================================");
        System.out.println("1. Register Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display Patients");
        System.out.println("0. Exit");
        System.out.println("======================================");
    }

    private static void registerPatient() {

        int id = readInt("Enter Patient ID: ");

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

        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {

        int id = readInt("Enter Patient ID to search: ");

        Patient patient = patientBST.search(id);

        if (patient != null) {
            System.out.println("\nPatient found:");
            System.out.println(patient);
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void deletePatient() {

        int id = readInt("Enter Patient ID to delete: ");

        Patient patient = patientBST.search(id);

        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        patientBST.delete(id);

        System.out.println("Patient deleted successfully.");
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                int value = Integer.parseInt(scanner.nextLine());

                return value;

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }
}