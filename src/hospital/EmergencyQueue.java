package hospital;

public class EmergencyQueue {

    private class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node front;
    private Node rear;

    public void enqueue(Patient patient) {

        Node newNode = new Node(patient);

        if (rear == null) {
            front = rear = newNode;
            return;
        }

        rear.next = newNode;
        rear = newNode;
    }

    public Patient dequeue() {

        if (front == null) {
            System.out.println("Emergency queue is empty.");
            return null;
        }

        Patient patient = front.patient;

        front = front.next;

        if (front == null) {
            rear = null;
        }

        return patient;
    }

    public void displayQueue() {

        if (front == null) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        Node current = front;

        System.out.println("\n--- Emergency Waiting Queue ---");

        while (current != null) {

            System.out.println(current.patient);

            current = current.next;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }
}