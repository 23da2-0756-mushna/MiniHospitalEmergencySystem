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