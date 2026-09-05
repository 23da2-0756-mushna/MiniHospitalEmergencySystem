package hospital;
public class TreatmentStack {

    private class Node {
        String treatmentRecord;
        Node next;

        Node(String treatmentRecord) {
            this.treatmentRecord = treatmentRecord;
        }
    }

    private Node top;

    public void push(String treatmentRecord) {

        Node newNode = new Node(treatmentRecord);

        newNode.next = top;

        top = newNode;
    }

    public String pop() {

        if (top == null) {
            System.out.println("Treatment history is empty.");
            return null;
        }

        String record = top.treatmentRecord;

        top = top.next;

        return record;
    }

    public void displayTreatments() {

        if (top == null) {
            System.out.println("Treatment history is empty.");
            return;
        }

        Node current = top;

        System.out.println("\n--- Treatment History ---");

        while (current != null) {

            System.out.println(current.treatmentRecord);

            current = current.next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }
}