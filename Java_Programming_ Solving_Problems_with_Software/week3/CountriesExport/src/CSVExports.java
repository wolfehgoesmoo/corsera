import java.io.File;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.DirectoryResource;
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

    public CSVRecord dayHighestTemp(CSVParser parser) {
        String currentRecord = null;
        double dayHighestTemp = 0.0;
        CSVRecord hottestRecord = null;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("DateUTC") + 
                            record.get("TemperatureF");

            // Check to make sure temperature is a number
            if (record.get("TemperatureF").equals("-9999")) {
                continue;
            }

            // Log if the highest temp for the current record
            if (Double.parseDouble(record.get("TemperatureF")) > dayHighestTemp) {
                dayHighestTemp = Double.parseDouble(record.get("TemperatureF"));
                hottestRecord = record;
            }
        }
        return hottestRecord;
    }

    public CSVRecord dateRangeHighestTemp() {
        CSVRecord hottestRecord = null;
        CSVParser parser = null;
        double dayHighestTemp = 0.0;
        
        // Open a folder to select multiple .csv files with weather data
        DirectoryResource dr = new DirectoryResource();
        
        // Loop through each file in the directory
        for (File f : dr.selectedFiles()) {

            // Load in the specific file and set it up with the CSV parser
            FileResource fr = new FileResource(f);
            parser = fr.getCSVParser();

            // Get the highest temperature for current day (file)
            CSVRecord currentHighestDayTemp = dayHighestTemp(parser);

            // If it is higher than the current highest temperature'd day
            if (Double.parseDouble(currentHighestDayTemp.get("TemperatureF")) > dayHighestTemp) {
                // Then update the highest temp and record
                dayHighestTemp = Double.parseDouble(currentHighestDayTemp.get("TemperatureF"));
                hottestRecord = currentHighestDayTemp;
            }
        }

        return hottestRecord;
    }

    public CSVRecord dayLowestTemp(CSVParser parser) {
        String currentRecord = null;
        double dayLowestTemp = 200.0;
        CSVRecord coldestRecord = null;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("DateUTC") + 
                            record.get("TemperatureF");

            // Check to make sure temperature is a number
            if (record.get("TemperatureF").equals("-9999")) {
                continue;
            }

            // Log if the highest temp for the current record
            if (Double.parseDouble(record.get("TemperatureF")) < dayLowestTemp) {
                dayLowestTemp = Double.parseDouble(record.get("TemperatureF"));
                coldestRecord = record;
            }
        }
        return coldestRecord;
    }

    public CSVRecord dateRangeLowestTemp() {
        CSVRecord coldestRecord = null;
        CSVParser parser = null;
        double dayLowestTemp = 200.0;
        
        // Open a folder to select multiple .csv files with weather data
        DirectoryResource dr = new DirectoryResource();
        
        // Loop through each file in the directory
        for (File f : dr.selectedFiles()) {

            // Load in the specific file and set it up with the CSV parser
            FileResource fr = new FileResource(f);
            parser = fr.getCSVParser();

            // Get the highest temperature for current day (file)
            CSVRecord currentLowestDayTemp = dayLowestTemp(parser);

            // If it is higher than the current highest temperature'd day
            if (Double.parseDouble(currentLowestDayTemp.get("TemperatureF")) < dayLowestTemp) {
                // Then update the highest temp and record
                dayLowestTemp = Double.parseDouble(currentLowestDayTemp.get("TemperatureF"));
                coldestRecord = currentLowestDayTemp;
            }
        }
        return coldestRecord;
    }

    public CSVRecord dayLowestHumidity(CSVParser parser) {
        String currentRecord = null;
        double dayLowestHumidity = 200.0;
        CSVRecord humidRecord = null;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("DateUTC") + 
                            record.get("Humidity");

            // Check to make sure humidity is a number
            if (record.get("Humidity").equals("N/A")) {
                continue;
            }

            // Log if the highest temp for the current record
            if (Double.parseDouble(record.get("Humidity")) < dayLowestHumidity) {
                dayLowestHumidity = Double.parseDouble(record.get("Humidity"));
                humidRecord = record;
            }
        }
        return humidRecord;
    }

    public CSVRecord dateRangeLowestHumidity() {
        CSVRecord humidRecord = null;
        CSVParser parser = null;
        double dayLowestHumidity = 200.0;
        
        // Open a folder to select multiple .csv files with weather data
        DirectoryResource dr = new DirectoryResource();
        
        // Loop through each file in the directory
        for (File f : dr.selectedFiles()) {

            // Load in the specific file and set it up with the CSV parser
            FileResource fr = new FileResource(f);
            parser = fr.getCSVParser();

            // Get the highest humidity for current day (file)
            CSVRecord currentLowestDayHumidity = dayLowestHumidity(parser);

            // If it is higher than the current highest humidity'd day
            if (Double.parseDouble(currentLowestDayHumidity.get("Humidity")) < dayLowestHumidity) {
                // Then update the highest temp and record
                dayLowestHumidity = Double.parseDouble(currentLowestDayHumidity.get("Humidity"));
                humidRecord = currentLowestDayHumidity;
            }
        }
        return humidRecord;
    }

    public double dayAvgTemp(CSVParser parser) {
        String currentRecord = null;
        int countOfRecords = 0;
        double totalTemp = 0.0;

        // Loop through each record
        for (CSVRecord record : parser) {
            currentRecord = record.get("DateUTC") + 
                            record.get("TemperatureF");

            // Check to make sure temperature is a number
            if (record.get("TemperatureF").equals("-9999")) {
                continue;
            }
            
            // Sum together the total temperature across all records
            totalTemp = totalTemp + Double.parseDouble(record.get("TemperatureF"));

            // Increment the number of records
            countOfRecords++;
        }
        return totalTemp / (double)countOfRecords;
    }

    public double avgTempHighHumidity(CSVParser parser, double value) {
        int countOfRecords = 0;
        double totalTemp = 0.0;

        // Loop through each record
        for (CSVRecord record : parser) {

            // Check to make sure temperature is a number
            if (record.get("TemperatureF").equals("-9999") || 
                record.get("Humidity").equals("N/A")) {
                continue;
            }

            // If the humidity specified is greater than humidity of the day
            if (Double.parseDouble(record.get("Humidity")) >= value) {

                // Sum together the total temperature across all records
                totalTemp = totalTemp + Double.parseDouble(record.get("TemperatureF"));

                // Increment the number of records
                countOfRecords++;
            }
        }
        return totalTemp / (double)countOfRecords;
    }

    // Testing methods
    public void tester() {
        
        // FileResource fr = new FileResource();
        // CSVParser parser = fr.getCSVParser();

        // Exports
        // System.out.println(countryInfo(parser, "Nauru"));
        // listExportersTwoProducts(parser, "cotton", "flowers");
        // System.out.println(numberOfExporters(parser, "cocoa"));
        // bigExporters(parser, "$999,999,999,999");

        // Hot
        // CSVRecord dayHighestTemp = dayHighestTemp(parser);
        // System.out.println("Hottest Temp: " + dayHighestTemp.get("TemperatureF"));
        // CSVRecord dayHighestTemp = dateRangeHighestTemp();
        // System.out.println("Hottest Temp: " + dayHighestTemp.get("TemperatureF") + 
        //                    " was on " + dayHighestTemp.get("DateUTC"));

        // Cold
        // CSVRecord dayLowestTemp = dayLowestTemp(parser);
        // System.out.println("Coldest Temp: " + dayLowestTemp.get("TemperatureF"));
        CSVRecord dayLowestTemp = dateRangeLowestTemp();
        System.out.println("Lowest Temp: " + dayLowestTemp.get("TemperatureF") + 
                           " was on " + dayLowestTemp.get("DateUTC"));

        // Humidity
        // CSVRecord dayLowestHumidity = dayLowestHumidity(parser);
        // System.out.println("Lowest Humidity: " + dayLowestHumidity.get("Humidity") + 
        //                    " was on " + dayLowestHumidity.get("DateUTC"));
        // CSVRecord dayLowestHumidity = dateRangeLowestHumidity();
        // System.out.println("Lowest Humidity: " + dayLowestHumidity.get("Humidity") + 
        //                    " was on " + dayLowestHumidity.get("DateUTC"));


        // Average
        // System.out.println("Average temp: " + dayAvgTemp(parser));
        // System.out.println("Average temp with humidity over 80: " + avgTempHighHumidity(parser, 80));
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