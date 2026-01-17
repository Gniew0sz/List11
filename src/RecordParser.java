import java.io.File;
import java.util.regex.Pattern;

public class RecordParser {
    private File file;
    private static final Pattern DATE_PATTERN = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})");
    private static final Pattern AGE_PATTERN = Pattern.compile("Patient is (\\d+) years old");
    private static final Pattern PHONE_PATTERN = Pattern.compile("Cell: \\b(\\d{9,11})\\b");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("Email: ([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})");
    private static final Pattern DOCTOR_PATTERN = Pattern.compile("Doctor: ([^,]+, [^,.]+)");
    private static final Pattern MEDS_PATTERN = Pattern.compile("Patient takes: ?\\s*([^,]+(?:,\\s*[^,]+)*)");

    public RecordParser(String pathName) {
        file = new File(pathName);

    }
}
