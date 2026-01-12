import java.util.Arrays;
import java.util.Scanner;

class ClinicTest {
    void main() {
        Scanner scanner = new Scanner(System.in);
        HospitalManager manager = new HospitalManager();
        while (true) {
            System.out.print("> ");
            String cmd = scanner.next();

            switch (cmd) {
                case "app": // doctor, patient, start, end
                    manager.addAppointment(scanner.next(), scanner.next(),
                            scanner.next() + " " + scanner.next(),
                            scanner.next() + " " + scanner.next());
                    break;
                case "view-pat":// patient
                    manager.displayPatientAppointments(scanner.next());
                    break;
                case "view-doc": //doctor
                    manager.displayDoctorCalendar(scanner.next());
                    break;
                case "prescribe": // patient, med1,med2...,doctor
                    manager.addPrescription(scanner.next(), Arrays.asList(scanner.next().split(",")), scanner.next());
                    break;
                case "search-med": //medicine
                    manager.displayPatientsByMedicine(scanner.next());
                    break;
                case "exit"://exit
                    return;
                default:
                    System.out.println("Invalid command");
                break;
            }
        }
    }
}
