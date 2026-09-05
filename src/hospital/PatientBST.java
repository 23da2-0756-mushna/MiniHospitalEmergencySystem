package hospital;
public class PatientBST {

    private class Node {
        Patient patient;
        Node left;
        Node right;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node root;

    public void insert(Patient patient) {
        root = insertRecursive(root, patient);
    }

    private Node insertRecursive(Node current, Patient patient) {

        if (current == null) {
            return new Node(patient);
        }

        if (patient.getPatientId() < current.patient.getPatientId()) {
            current.left = insertRecursive(current.left, patient);

        } else if (patient.getPatientId() > current.patient.getPatientId()) {
            current.right = insertRecursive(current.right, patient);

        } else {
            System.out.println("Patient ID already exists.");
        }

        return current;
    }

    public Patient search(int patientId) {

        Node result = searchRecursive(root, patientId);

        if (result != null) {
            return result.patient;
        }

        return null;
    }

    private Node searchRecursive(Node current, int patientId) {

        if (current == null ||
                current.patient.getPatientId() == patientId) {
            return current;
        }

        if (patientId < current.patient.getPatientId()) {
            return searchRecursive(current.left, patientId);
        }

        return searchRecursive(current.right, patientId);
    }

    public void delete(int patientId) {
        root = deleteRecursive(root, patientId);
    }

    private Node deleteRecursive(Node current, int patientId) {

        if (current == null) {
            return null;
        }

        if (patientId < current.patient.getPatientId()) {

            current.left = deleteRecursive(current.left, patientId);

        } else if (patientId > current.patient.getPatientId()) {

            current.right = deleteRecursive(current.right, patientId);

        } else {

            // Case 1: No child
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: One right child
            if (current.left == null) {
                return current.right;
            }

            // Case 2: One left child
            if (current.right == null) {
                return current.left;
            }

            // Case 3: Two children
            Node successor = findMin(current.right);

            current.patient = successor.patient;

            current.right = deleteRecursive(
                    current.right,
                    successor.patient.getPatientId()
            );
        }

        return current;
    }

    private Node findMin(Node current) {

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void inOrder() {

        if (root == null) {
            System.out.println("No patients registered.");
            return;
        }

        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node current) {

        if (current != null) {

            inOrderRecursive(current.left);

            System.out.println(current.patient);

            inOrderRecursive(current.right);
        }
    }
}