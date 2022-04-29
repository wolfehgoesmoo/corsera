

import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;
import edu.duke.StorageResource;

public class CSVExports {
    public static void main(String[] args) throws Exception {
        System.out.println("Testing CSV");
        /*
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord record : parser) {
                String s = record.get("Name");

                if (s.contains("wwww"))
            }
        */
    }

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
    }

    // Testing methods

}