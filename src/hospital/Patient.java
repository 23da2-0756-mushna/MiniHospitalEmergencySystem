package hospital;
public class Patient {

    private int patientId;
    private String patientName;
    private int age;
    private String contactNumber;
    private String medicalCondition;

    public Patient(int patientId, String patientName, int age,
                   String contactNumber, String medicalCondition) {

        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
    }
}

