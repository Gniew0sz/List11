import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Program to parse patient visit data from a CSV file.
 * Performs validation, regex data extraction, and generates a final report.
 * @author Marcin Mieszała
 */
public class RecordParser {
    private File file;

    // Regex Definitions with capturing groups ()
    private static final Pattern DATE_PATTERN = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})");
    private static final Pattern AGE_PATTERN = Pattern.compile("Patient is (\\d+) years old");
    private static final Pattern PHONE_PATTERN = Pattern.compile("Cell: (\\+\\d-\\d{3}-\\d{3}-\\d{4})");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("Email: ([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})");
    private static final Pattern DOCTOR_PATTERN = Pattern.compile("Doctor: ([^,]+, [^,.]+)");
    private static final Pattern MEDS_PATTERN = Pattern.compile("Patient takes: ?\\s*([^,]+(?:,\\s*[^,]+)*)");
    /**
     * Constructor initializes the file path.
     * @param pathName Path to the input CSV file.
     */
    public RecordParser(String pathName) {
        this.file = new File(pathName);
    }

    /**
     * Main method to process the file line by line.
     */
    public void parseAndReport() {
        int recordCount = 0;
        int emptyLines = 0;
        int patientsWithMeds = 0;
        List<String> dates = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();


                if (line.isEmpty()|| !line.matches(".*[a-zA-Z0-9].*")) { //For some reason line.isEmpty() didn't work on its own and returned a record with all of its values set to "not found" I think it has to do with an unknown symbol that each line starts with because my regex for date also didn't work until I removed ^ from it
                    emptyLines++;
                    System.out.println("[Warning] Rejected an empty line.");
                    continue;
                }

                recordCount++;
                System.out.println("\n--- Record #" + recordCount + " ---");


                String date = extract(DATE_PATTERN, line);
                if (!date.equals("not found")) dates.add(date);

                String age = extract(AGE_PATTERN, line);
                String phone = extract(PHONE_PATTERN, line);
                String email = extract(EMAIL_PATTERN, line);
                String doctor = extract(DOCTOR_PATTERN, line);
                String meds = extract(MEDS_PATTERN, line);


                //My regex kept including every word after "Patient takes" and I tried fixing this with a so called lookout but I couldn't make it work for some reason so that's why this exists
                if (!meds.equals("not found")) {
                    String[] keywords = {", Email:", ", Doctor:", ", Cell:"};
                    for (String keyword : keywords) {
                        int index = meds.indexOf(keyword);
                        if (index != -1) {

                            meds = meds.substring(0, index);
                        }
                    }
                    patientsWithMeds++;

                }
                System.out.println("Date: " + date);
                System.out.println("Age: " + age);
                System.out.println("Phone: " + phone);
                System.out.println("Email: " + email);
                System.out.println("Doctor: " + doctor);
                System.out.println("Medications: " + meds);
            }


            printSummary(recordCount, emptyLines, patientsWithMeds, dates);

        } catch (FileNotFoundException e) {
            System.err.println("Error: Could not find file " + file.getName());
        }
    }

    /**
     * Helper method to extract data using Regex groups.
     * @param pattern The regex pattern to use.
     * @param line The string to search.
     * @return The identified element or "not found".
     */
    private String extract(Pattern pattern, String line) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "not found";
    }

    /**
     * Prints the final statistics of the parsing process.
     */
    private void printSummary(int total, int empty, int withMeds, List<String> dates) {
        System.out.println("\n========================================");
        System.out.println("FINAL REPORT");
        System.out.println("========================================");
        System.out.println("Total records processed: " + total);
        System.out.println("Empty lines rejected: " + empty);
        System.out.println("Patients taking medication: " + withMeds);

        if (!dates.isEmpty()) {
            Collections.sort(dates);
            System.out.println("Date range: " + dates.get(0) + " to " + dates.get(dates.size() - 1));
        }
        System.out.println("========================================");
    }

}