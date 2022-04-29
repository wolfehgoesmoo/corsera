import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;
import edu.duke.StorageResource;

public class CSVExports {
    public static void main(String[] args) throws Exception {
        System.out.println("");
        System.out.println("");
        
        
        CSVExports csv = new CSVExports();
        csv.tester();
        /*
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord record : parser) {
                String s = record.get("Name");

                if (s.contains("wwww"))

                Reset parser by using: parser = fr.getCSVParser();
            }
        */
    }

    public String countryInfo(CSVParser parser, String country) {
        String currentRecord = null;
        String countryInfo = null;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("Country") + ": " + 
                            record.get("Exports") + ": " + 
                            record.get("Value (dollars)");

            // Only print out records related to the passed in country
            if (currentRecord.contains(country)) {
                
                // Update the countries info
                countryInfo = currentRecord;
                System.out.println(currentRecord);
            }
 
        }
        // Check to see if the country information was found
        if (countryInfo != null) {
            return countryInfo; 
        } else {
            return "NOT FOUND"; 
        }
    }

    // Testing methods
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String countryInfo = countryInfo(parser, "China");

        System.out.println(countryInfo);
    }
}