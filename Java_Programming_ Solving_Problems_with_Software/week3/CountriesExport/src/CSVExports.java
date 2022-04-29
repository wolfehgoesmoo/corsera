import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;

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
                //System.out.println(currentRecord);
            }
 
        }
        // Check to see if the country information was found
        if (countryInfo != null) {
            return countryInfo; 
        } else {
            return "NOT FOUND"; 
        }
    }

    // Prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        String currentRecord = null;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("Country") + ": " + 
                            record.get("Exports") + ": " + 
                            record.get("Value (dollars)");

            // Only print out records related to the passed in country
            if (currentRecord.contains(exportItem1) && currentRecord.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    // Returns the number of countries that export exportItem
    public int numberOfExporters(CSVParser parser, String exportItem) {
        String currentRecord = null;
        int numCountries = 0;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("Country") + ": " + 
                            record.get("Exports") + ": " + 
                            record.get("Value (dollars)");

            // Only print out records related to the passed in country
            if (currentRecord.contains(exportItem)) {
                numCountries++;
            }
        }
        return numCountries;
    }
    
    // Prints the names of countries and their Value amount for all countries whose Value (dollars)
    // string is longer than the amount string.
    public void bigExporters(CSVParser parser, String amount) {
        String currentRecord = null;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("Country") + " " + 
                            record.get("Value (dollars)");

            // If more value is more than what's passed in amount wise
            if (strAmountToLong(record.get("Value (dollars)")) > strAmountToLong(amount)) {
                System.out.println(currentRecord);
            }
        }
    }

    public double dayHighestTemp(CSVParser parser) {
        String currentRecord = null;
        double dayHighestTemp = 0.0;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("DateUTC") + 
                            record.get("TemperatureF");

            // Log if the highest temp for the current record
            if (Double.parseDouble(record.get("TemperatureF")) > dayHighestTemp) {
                dayHighestTemp = Double.parseDouble(record.get("TemperatureF"));
            }
        }
        return dayHighestTemp;
    }

    // public dayHighestAvgTemp() {

    // }


    // Testing methods
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        // System.out.println(countryInfo(parser, "Nauru"));
        // listExportersTwoProducts(parser, "fish", "nuts");
        // System.out.println(numberOfExporters(parser, "sugar"));
        // bigExporters(parser, "$999,999,999,999");
        System.out.println(dayHighestTemp(parser));
    }

    // Helper methods
    public long strAmountToLong(String amount) {
        String moneyAsString = null;

        // Convert the amount of money to a long
        moneyAsString = amount.replace("$","");
        moneyAsString = moneyAsString.replace(",","");
        return Long.parseLong(moneyAsString);
    }
}