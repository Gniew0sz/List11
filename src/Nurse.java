public class Nurse extends Staff{
    @Override
    public void performDuties() {
        System.out.println("Performing nurse duties");
    }
    void checkVitals(Patient patient){
        System.out.print("Checking vitals of ");
        patient.introduce();
    }
    public Nurse(String name, int age, String role) {
        super(name,age,role);

    }
}
