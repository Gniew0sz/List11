public class Receptionist extends Staff{
    @Override
    public void performDuties() {
        System.out.println("Performing receptionist duties");
    }
    public Receptionist(String name, int age, String role) {
        super(name,age,role);

    }
}
