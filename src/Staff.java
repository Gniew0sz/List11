public abstract class Staff extends Person{
    private String role;
    public abstract void performDuties();
    public Staff(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }
    public void createAppointment(Patient patient, Appointment appointment) {
        patient.addAppointment(appointment);
    }
}
