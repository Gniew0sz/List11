import java.util.Arrays;
import java.util.Scanner;

class ClinicTest {
    void main() {
        RecordParser recordParser = new RecordParser("data.csv");
        recordParser.parseAndReport();

    }
}
