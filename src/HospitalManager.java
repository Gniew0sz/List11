import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalManager {
    private Map<String, Doctor> doctors = new HashMap<>();
    private Map<String, Patient> patients = new HashMap<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Helper to find or create entities
    public void seedData(Doctor d, Patient p) {
        doctors.put(d.getName(), d);
        patients.put(p.getName(), p);
    }

    public void addAppointment(String docName, String patName, String startStr, String endStr) {
        Doctor doc = doctors.get(docName);
        Patient pat = patients.get(patName);

        if (doc == null || pat == null) {
            System.out.println("Error: Doctor or Patient not found.");
            return;
        }

        Appointment app = new Appointment(
                LocalDateTime.parse(startStr, formatter),
                LocalDateTime.parse(endStr, formatter)
        );

        if (doc.addAppointment(app)) {
            pat.addAppointment(app);
            System.out.println("Success: Appointment scheduled.");
        } else {
            System.out.println("Error: Doctor has an overlapping appointment.");
        }
    }


    public void displayPatientAppointments(String name) {
        Patient p = patients.get(name);
        if (p != null) {
            System.out.println("Appointments for " + name + ":");
            p.getAppointments().forEach(Appointment::getAppointment);
        }
    }


    public void displayDoctorCalendar(String name) {
        Doctor d = doctors.get(name);
        if (d != null) {
            System.out.println("Calendar for Dr. " + name + ":");
            // Since appointments is package-private in your code:
            d.appointments.forEach(Appointment::getAppointment);
        }
    }


    public void addPrescription(String patName, List<String> treatments,String docName) {
        Patient p = patients.get(patName);
        Doctor d =doctors.get(docName);
        if (p != null) {
            p.addPrescription(new Prescription(new ArrayList<>(treatments),d));
            System.out.println("Prescription added.");
        }
    }


    public void displayPatientPrescriptions(String name) {
        Patient p = patients.get(name);
        if (p != null) {
            p.getPrescriptions().forEach(Prescription::getTreatments);
        }
    }


    public void displayPatientsByMedicine(String medicine) {
        System.out.println("Patients taking " + medicine + ":");
        // Logic: Scan all patients for the medicine
        for (Patient p : patients.values()) {
            for (Prescription pr : p.getPrescriptions()) {
                if (pr.treatments.contains(medicine)) {
                    System.out.println("- Patient: " + p.getName());
                    // Note: In your current code, Prescription doesn't store the Doctor. 
                    // To satisfy "name of prescribing doctor", you'd need to update 
                    // the Prescription class to include a 'String doctorName' field.
                }
            }
        }
    }
}