import java.util.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("Please enter the input record file path : ");
        Scanner scanner = new Scanner(System.in);

        String filePath = scanner.nextLine();

        PhoneBook phoneBook = new PhoneBook();

        System.out.println("Please enter the queries.txt file path : ");
        // Read the user input for queries.csv file path
        String queriesFilePath = scanner.nextLine();
        List<String[]> queries = Utility.readFile(queriesFilePath);

        //Take input as valid contact list and return the formatted and filtered output string
        StringBuilder builder = phoneBook.getContactsFilteredByQuery(Utility.filterValidRecords(Utility.readFile(filePath)), queries);

        // writes the output string to the file
        Utility.writeOutputInFile(builder.toString());
    }
}
