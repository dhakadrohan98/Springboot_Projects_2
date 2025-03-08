import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void readFile() {
        // Define the file path (relative to project root)
        String fileName = "sample.jil";

        try {
            // Read the entire file into a String
            String jilContent = Files.readString(Paths.get(fileName));
            System.out.println("JIL File Content:\n" + jilContent);
        } catch (IOException e) {
            System.err.println("Error reading JIL file: " + e.getMessage());
        }
    }

    public static void createFile() {
        // Define JIL content
        String jilContent = "insert_job: sample_job\n" +
                "job_type: CMD\n" +
                "command: echo \"Hello, AutoSys!\"\n" +
                "machine: localhost\n" +
                "owner: user@localhost\n" +
                "start_times: \"10:00\"\n" +
                "description: \"This is a sample JIL job\"";

        // File name
        String fileName = "sample.jil";

        // Write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(jilContent);
            System.out.println("JIL file created successfully: " + fileName);
        } catch (IOException e) {
            System.err.println("Error creating JIL file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        readFile();
    }
}