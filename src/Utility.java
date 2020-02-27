import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    // Method to read a file from a path and get List of String[] as output
    public static List<String[]> readFile(String path) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        List<String[]> output = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] record = line.split(cvsSplitBy);
                output.add(record);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return output;
    }

    // Method to filter valid records from the list of record
    public static List<String[]> filterValidRecords(List<String[]> records) {
        List<String[]> validRecords = new ArrayList<>(records);
        for (String[] record:
             records) {
            if (record.length != 4) {
                if (record.length == 3 && record[0].contains(" ")) {
                    String[] arr = record[0].split(" ");
                    String tRecord[] = new String[4];
                    tRecord[0] = arr[0];
                    tRecord[1] = arr[1];
                    tRecord[2] = record[1];
                    tRecord[3] = record[2];
                    validRecords.add(tRecord);
                    validRecords.remove(record);
                } else
                    validRecords.remove(record);
            }

            for (int i = 2; i < record.length; i++) {
                if ((record[i].trim().charAt(0) == '(') || (Character.isDigit(record[i].trim().charAt(0)))) {
                    record[i] = record[i].replaceAll("[\\s()-]", "");
                    if (record[i].length() != 10)
                        validRecords.remove(record);
                }

            }
        }
        return validRecords;
    }

    //Method to write string in a file
    public static void writeOutputInFile(String outputString) {

        try {
            Files.write(Paths.get("/Users/aagarwal327/Downloads/output.txt"),outputString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
