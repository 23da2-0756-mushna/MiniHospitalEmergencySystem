package hospital;

public class HospitalSystemTest {

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("   MINI HOSPITAL SYSTEM - TESTING");
        System.out.println("==========================================");

        testPatientBST();
        testEmergencyQueue();
        testTreatmentStack();
        testVisitHistory();

        System.out.println("\n==========================================");
        System.out.println("       ALL TESTS COMPLETED");
        System.out.println("==========================================");
    }

  
    // TEST 1 - PATIENT BST
   

    private static void testPatientBST() {

        System.out.println("\n------------------------------------------");
        System.out.println("TEST 1: PATIENT BST");
        System.out.println("------------------------------------------");

        PatientBST bst = new PatientBST();

        Patient patient1 = new Patient(
                103,
                "Ahmed",
                30,
                "0771234567",
                "Fever"
        );

        Patient patient2 = new Patient(
                101,
                "Sara",
                25,
                "0777654321",
                "Headache"
        );

        Patient patient3 = new Patient(
                105,
                "John",
                40,
                "0712345678",
                "Injury"
        );

        System.out.println("\nAdding patients...");

        bst.insert(patient1);
        bst.insert(patient2);
        bst.insert(patient3);

        System.out.println("Patients added successfully.");

        // Test inorder traversal
        System.out.println("\nIn-order traversal:");
        bst.inOrder();

        // Test search
        System.out.println("\nSearching for Patient ID 101:");

        Patient foundPatient = bst.search(101);

        if (foundPatient != null) {
            System.out.println("PASS - Patient found:");
            System.out.println(foundPatient);
        } else {
            System.out.println("FAIL - Patient not found.");
        }

        // Test search for non-existing patient
        System.out.println("\nSearching for Patient ID 999:");

        Patient notFound = bst.search(999);

        if (notFound == null) {
            System.out.println("PASS - Non-existing patient correctly not found.");
        } else {
            System.out.println("FAIL - Unexpected patient found.");
        }

        // Test deletion
        System.out.println("\nDeleting Patient ID 103...");

        bst.delete(103);

        Patient deletedPatient = bst.search(103);

        if (deletedPatient == null) {
            System.out.println("PASS - Patient deleted successfully.");
        } else {
            System.out.println("FAIL - Patient was not deleted.");
        }

        System.out.println("\nBST after deletion:");
        bst.inOrder();
    }

    
    // TEST 2 - EMERGENCY QUEUE
   

    private static void testEmergencyQueue() {

        System.out.println("\n------------------------------------------");
        System.out.println("TEST 2: EMERGENCY QUEUE");
        System.out.println("------------------------------------------");

        EmergencyQueue queue = new EmergencyQueue();

        Patient patient1 = new Patient(
                201,
                "Kamal",
                35,
                "0711111111",
                "Fever"
        );

        Patient patient2 = new Patient(
                202,
                "Nimal",
                28,
                "0722222222",
                "Injury"
        );

        Patient patient3 = new Patient(
                203,
                "Fathima",
                32,
                "0733333333",
                "Pain"
        );

        System.out.println("\nAdding patients to emergency queue...");

        queue.enqueue(patient1);
        queue.enqueue(patient2);
        queue.enqueue(patient3);

        System.out.println("Patients added successfully.");

        System.out.println("\nCurrent queue:");
        queue.displayQueue();

        // Test FIFO
        System.out.println("\nTesting FIFO order...");

        Patient firstPatient = queue.dequeue();

        if (firstPatient != null &&
                firstPatient.getPatientId() == 201) {

            System.out.println(
                    "PASS - First patient was treated first: "
                            + firstPatient.getPatientId()
            );

        } else {

            System.out.println("FAIL - FIFO order incorrect.");
        }

        System.out.println("\nQueue after dequeue:");
        queue.displayQueue();

        // Test empty queue handling
        System.out.println("\nTesting remaining patients:");

        queue.dequeue();
        queue.dequeue();

        if (queue.isEmpty()) {
            System.out.println("PASS - Queue is empty.");
        } else {
            System.out.println("FAIL - Queue should be empty.");
        }

        System.out.println("\nTesting dequeue on empty queue:");

        queue.dequeue();
    }

    
    // TEST 3 - TREATMENT STACK
    

    private static void testTreatmentStack() {

        System.out.println("\n------------------------------------------");
        System.out.println("TEST 3: TREATMENT STACK");
        System.out.println("------------------------------------------");

        TreatmentStack stack = new TreatmentStack();

        System.out.println("\nAdding treatment records...");

        stack.push("Patient 301 - Fever treatment");
        stack.push("Patient 302 - Injury treatment");
        stack.push("Patient 303 - Headache treatment");

        System.out.println("Treatment records added.");

        System.out.println("\nCurrent treatment history:");
        stack.displayTreatments();

        // Test LIFO
        System.out.println("\nTesting LIFO order...");

        String latestTreatment = stack.pop();

        if (latestTreatment != null &&
                latestTreatment.contains("Patient 303")) {

            System.out.println(
                    "PASS - Latest treatment removed first:"
            );

            System.out.println(latestTreatment);

        } else {

            System.out.println("FAIL - LIFO order incorrect.");
        }

        System.out.println("\nRemaining treatment history:");
        stack.displayTreatments();

        // Remove remaining treatments
        stack.pop();
        stack.pop();

        if (stack.isEmpty()) {
            System.out.println("\nPASS - Stack is empty.");
        } else {
            System.out.println("\nFAIL - Stack should be empty.");
        }

        System.out.println("\nTesting pop on empty stack:");

        stack.pop();
    }

   
    // TEST 4 - VISIT HISTORY
    

    private static void testVisitHistory() {

        System.out.println("\n------------------------------------------");
        System.out.println("TEST 4: PATIENT VISIT HISTORY");
        System.out.println("------------------------------------------");

        VisitHistory history = new VisitHistory();

        Visit visit1 = new Visit(
                1,
                "2026-09-01",
                "Dr. Silva",
                "Fever",
                "Medication"
        );

        Visit visit2 = new Visit(
                2,
                "2026-09-03",
                "Dr. Perera",
                "Headache",
                "Painkiller"
        );

        Visit visit3 = new Visit(
                3,
                "2026-09-05",
                "Dr. Fernando",
                "Injury",
                "Bandage"
        );

        // Test add
        System.out.println("\nAdding visits...");

        history.addVisit(visit1);
        history.addVisit(visit2);
        history.addVisit(visit3);

        System.out.println("Visits added successfully.");

        // Test display
        System.out.println("\nPatient visit history:");

        history.displayHistory();

        // Test search
        System.out.println("\nSearching for Visit ID 2:");

        Visit foundVisit = history.searchVisit(2);

        if (foundVisit != null) {

            System.out.println("PASS - Visit found:");
            System.out.println(foundVisit);

        } else {

            System.out.println("FAIL - Visit not found.");
        }

        // Test non-existing visit
        System.out.println("\nSearching for Visit ID 99:");

        Visit notFoundVisit = history.searchVisit(99);

        if (notFoundVisit == null) {

            System.out.println(
                    "PASS - Non-existing visit correctly not found."
            );

        } else {

            System.out.println("FAIL - Unexpected visit found.");
        }

        // Test remove
        System.out.println("\nRemoving Visit ID 2...");

        boolean removed = history.removeVisit(2);

        if (removed) {

            System.out.println("PASS - Visit removed successfully.");

        } else {

            System.out.println("FAIL - Visit was not removed.");
        }

        System.out.println("\nVisit history after removal:");

        history.displayHistory();

        // Test remove non-existing visit
        System.out.println("\nRemoving non-existing Visit ID 99:");

        boolean removedAgain = history.removeVisit(99);

        if (!removedAgain) {

            System.out.println(
                    "PASS - Non-existing visit handled correctly."
            );

        } else {

            System.out.println(
                    "FAIL - Non-existing visit should not be removed."
            );
        }
    }
}