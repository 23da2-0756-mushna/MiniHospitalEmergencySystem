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
}