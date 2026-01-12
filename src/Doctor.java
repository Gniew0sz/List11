import java.util.ArrayList;

public class Doctor extends Staff{
    ArrayList<Appointment> appointments = new ArrayList<>();
    public boolean addAppointment(Appointment appointment) {
        for(Appointment a : this.appointments){
            if(overlap(appointment,a)){
                return  false;
            }
        }
        this.appointments.add(appointment);
        return true;
    }
    private boolean overlap(Appointment a, Appointment b) {
        return a.getStart().isBefore(b.getEnd())
                && b.getStart().isBefore(a.getEnd());
    }
    @Override
    public void performDuties(){
        System.out.println("Performing doctor duties");
    }
    void performDuties(Patient patient,String... treatments) {
        ArrayList<String> treatmentsList = new ArrayList<>();

        for(String treatment: treatments){
            treatmentsList.add(treatment);
        }
        patient.addPrescription(new Prescription(treatmentsList,this));

    }
    void prescribeMedicine(String medicine){
        System.out.println("Prescribing medicine "+medicine);
    }
    public Doctor(String name, int age, String role) {
        super(name,age,role);

    }
}
