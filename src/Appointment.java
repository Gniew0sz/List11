import java.time.LocalDateTime;

public class Appointment {
    public Appointment(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }
    private LocalDateTime start;
    private LocalDateTime end;

    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    public void setEnd(LocalDateTime    end) {
        this.end = end;
    }
    public LocalDateTime getStart() {
        return start;
    }
    public LocalDateTime getEnd() {
        return end;
    }
    public void getAppointment() {
        System.out.println("Appointment: "+this.start+" - "+this.end);
    }
}
