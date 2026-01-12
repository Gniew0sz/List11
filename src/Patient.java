import java.util.ArrayList;

public class Patient extends Person implements Treatable {
    private String condition;
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Prescription> prescriptions = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }
    public ArrayList<Prescription> getPrescriptions(){
        return prescriptions;
    }
    public void receiveTreatment(){
        System.out.println("Recieved treatment");
    }
    public Patient(String name, int age){
        super(name,age);
    }
}
