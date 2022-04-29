package src;

import java.io.File;
import org.apache.commons.csv.CSVRecord;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class BabyNames {
    String filePath = "Java_Programming_ Solving_Problems_with_Software/week4/course_project_files";
    String currentFilePath = filePath + "/us_babynames_test/";
    boolean DEBUG = true;
    int nameHeader = 0;
    int genderHeader = 1;
    int birthCountHeader = 2;


    public static void main(String[] args) throws Exception {
        BabyNames bn = new BabyNames();
        bn.testGetTotalBirthsRankedHigher();
    }

   // Prints the total number of births 
    public void getTotalBirths(FileResource fr) {
        int numBorn = 0;
        int totalBirths = 0;
        int totalGirl = 0;
        int totalBoy = 0;

        // Loop through the records of the passed in file
        for (CSVRecord currentRecord : fr.getCSVParser(false)) {
            // Track numBirths
            numBorn = Integer.parseInt(currentRecord.get(birthCountHeader));
            totalBirths += numBorn;

            // Track number of male/female births
            if (currentRecord.get(genderHeader).equals("F")) {
                totalGirl++;
            } else {
                totalBoy++;
            }

        }

        // Print out the totals based on birth count for a given file (year)
        System.out.println("Total Births: " + totalBirths);
        System.out.println("Total Girl Names: " + totalGirl);
        System.out.println("Total Boy Names: " + totalBoy);
        System.out.println("Total Names: " + (totalGirl + totalBoy));
    }

    // Returns the total births for a matching name and gender
    public int getTotalBirths(String name, String gender, FileResource fr) {

        // Loop through the records of the passed in file
        for (CSVRecord currentRecord : fr.getCSVParser(false)) {
            
            // If the current record matches the passed in details
            if (currentRecord.get(nameHeader).equals(name) && currentRecord.get(genderHeader).contains(gender)) {
                return Integer.parseInt(currentRecord.get(birthCountHeader));
            }
        }
        return -1;
    } 

    // Returns the rank of the name in the file for the given gender
    public int getRank(Integer year, String name, String gender) {
        int rankMale = 0;
        int rankFemale = 0;
        String yearBorn = String.valueOf(year);

        // Load in the file
        FileResource fr = new FileResource();
        // FileResource fr = new FileResource(currentFilePath + "yob" + yearBorn + "short.csv");

        for (CSVRecord currentRecord : fr.getCSVParser(false)) {

            // Increment rank based on gender
            if (currentRecord.get(genderHeader).contains("M")) {
                rankMale++;
            } else {
                rankFemale++;
            }

            // Check all the records for the passed in name and matching gender
            if (currentRecord.get(nameHeader).equals(name) && currentRecord.get(genderHeader).contains(gender)) {
                // Check the passed in gener, return appropriate rank
                if (gender.equals("M")) {
                    return rankMale;
                } else {
                    return rankFemale;
                }
            }
        }
        // If the name is not in the file, return -1
        return -1;
    }

    // Returns the rank of the name in the file for the given gender
    public int getRank(Integer year, String name, String gender, FileResource fr) {
        int rankMale = 0;
        int rankFemale = 0;

        for (CSVRecord currentRecord : fr.getCSVParser(false)) {

            // Increment rank based on gender
            if (currentRecord.get(genderHeader).contains("M")) {
                rankMale++;
            } else {
                rankFemale++;
            }

            // Check all the records for the passed in name and matching gender
            if (currentRecord.get(nameHeader).equals(name) && currentRecord.get(genderHeader).contains(gender)) {
                // Check the passed in gener, return appropriate rank
                if (gender.equals("M")) {
                    return rankMale;
                } else {
                    return rankFemale;
                }
            }
        }
        // If the name is not in the file, return -1
        return -1;
    }

    // Returns the name of the person in the file at this rank, for the given gender
    public String getName(Integer year, Integer rank, String gender) {
        int rankMale = 0;
        int rankFemale = 0;
        String yearBorn = String.valueOf(year);

        // Load in the file
        FileResource fr = new FileResource();
        // FileResource fr = new FileResource(currentFilePath + "yob" + yearBorn + "short.csv");

        // Loop through the current file
        for (CSVRecord currentRecord : fr.getCSVParser(false)) {

            // Increment rank based on gender
            if (currentRecord.get(genderHeader).contains("M")) {
                rankMale++;
            } else {
                rankFemale++;
            }

            // Check all the records for the passed in gender and matching rank
            if (currentRecord.get(genderHeader).contains(gender) && 
                (rankMale == rank || rankFemale == rank)) {
               return currentRecord.get(nameHeader);
            }
        }
        return "NO NAME";
    }

    // Returns the name of the person in the file at this rank, for the given gender
    public String getName(Integer rank, String gender, FileResource fr) {
        int rankMale = 0;
        int rankFemale = 0;

        // Loop through the current file
        for (CSVRecord currentRecord : fr.getCSVParser(false)) {

            // Increment rank based on gender
            if (currentRecord.get(genderHeader).contains("M")) {
                rankMale++;
            } else {
                rankFemale++;
            }

            // Check all the records for the passed in gender and matching rank
            if (currentRecord.get(genderHeader).contains(gender) && 
                (rankMale == rank || rankFemale == rank)) {
               return currentRecord.get(nameHeader);
            }
        }
        return "NO NAME";
    }

    // Returns what name would have been if born in a different year, based on the same popularity
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
       int rank = 0;
       String newName = null;

        // Get the rank of the passed in name for the year passed in
       rank = getRank(year, name, gender);

       // Find the name in the desired new year with matching rank
       newName = getName(newYear, rank, gender);

       // Print out the result
       System.out.println("New Name: " + newName);
    }

    // Use range of files to return the year with the highest rank for given name and gender
    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int bankYear = 2147483647;
        int currentYear = 0;
        int bankRank = 2147483647;
        int currentRank = 0;

        // Open a folder to select multiple .csv files with weather data
        DirectoryResource dr = new DirectoryResource();
                
        // Loop through each file in the directory
        for (File f : dr.selectedFiles()) {

            // Load in the specific file and set it up with the CSV parser
            FileResource fr = new FileResource(f);

            // Get the current year from the loaded file
            currentYear = Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));

            // Get the rank of the passed in name
            currentRank = getRank(currentYear, name, gender, fr);
            if (DEBUG) { System.out.println("Rank for " + name + " is " + currentRank + " in " + currentYear); }

            // Next iteration if rank wasn't found
            if (currentRank == -1) { continue; }

            // If currentRank is less than the bank rank, replace it
            if (currentRank < bankRank) {
                bankRank = currentRank;
                bankYear = currentYear;
            }

            // If it enounters a new rank that is the same, take the lower year
            if ((currentRank == bankRank)) {
                if (currentYear < bankYear) {
                    bankYear = currentYear;
                }
            }
        }

        if (DEBUG) { System.out.println("Highest rank for " + name + " is " + bankRank + " in " + bankYear); }
        return bankYear;
    }

    // Use range of files to return the average rank of the name and gender
    public double getAverageRank(String name, String gender) {
        int currentYear = 0;
        int countOfYears = 0;
        int currentRank = 0;
        int totalRank = 0;
        
        // Open a folder to select multiple .csv files with weather data
        DirectoryResource dr = new DirectoryResource();
                
        // Loop through each file in the directory
        for (File f : dr.selectedFiles()) {

            // Load in the specific file and set it up with the CSV parser
            FileResource fr = new FileResource(f);

            // Get the current year from the loaded file
            currentYear = Integer.parseInt(f.getName().replaceAll("[^0-9]", ""));

            // Search the current file for the name and gender and return its rank
            currentRank = getRank(currentYear, name, gender, fr);
            if (DEBUG) { System.out.println("Rank for " + name + " is " + currentRank + " in " + currentYear); }

            // If the rank was found
            if (currentRank != -1) {
                // Increment the number of years that the name has been in 
                countOfYears++;

                // Keep a running total of ranks
                totalRank += currentRank;

            }
        }
        if (DEBUG) { System.out.println("Total rank: " + totalRank); }
        if (DEBUG) { System.out.println("Total years: " + countOfYears); }

        // Check the average, make sure it's not 0
        if (totalRank != 0 || countOfYears != 0) {
            return (double)totalRank / (double)countOfYears;
        } else {
            return -1;
        }
    }

    // Returns the total births of those names with the same gender and same year who are ranked higher
    public int getTotalBirthsRankedHigher(Integer year, String name, String gender) {
        int totalBirths = 0;
        int currentRank = 0;
        int currentBirths = 0;
        String currentName = name;

        // Ask user to load in a file
        FileResource fr = new FileResource();

        // Get the current rank of passed in name
        currentRank = getRank(year, name, gender, fr);
        if (DEBUG) { System.out.println("Rank for " + name + " is " + currentRank + " in " + year); }

        // If the person isn't found, return -1
        if (currentRank == -1) { return -1;}

        // Loop through the higher ranks keeping the total of the number of births
        for (int i = currentRank - 1; i > 0; i--) {
            
            // Find name of the current ranked person
            currentName = getName(i, gender, fr);
            if (DEBUG) { System.out.println("Rank for " + currentName + " is " + i + " in " + year); }

            // Find the total births for the current ranked person
            currentBirths = getTotalBirths(currentName, gender, fr);
            if (DEBUG) { System.out.println("Total births for " + currentName + " is " + currentBirths + " in " + year); }

            // Get the total birth count for each person who has a higher rank
            totalBirths += currentBirths;
            if (DEBUG) { System.out.println("Total births for higher ranks: " + totalBirths); }
        }

        // Return the final total births for higher ranks
        return totalBirths;
    }

    // Tester Methods
    public void testTotalBirths() {
        // Create a file resource to pull in baby names
        FileResource fr = new FileResource();
        getTotalBirths(fr);
    }

    public void testGetRank() {
        System.out.println("Rank: " + getRank(1971, "Frank", "M"));
    }

    public void testGetName() {
        System.out.println("Name: " + getName(1980, 350, "F"));
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014,"M");
    }

    public void testYearOfHighestRank() {
        int year = yearOfHighestRank("Mich", "M");
        System.out.println("Mich is ranked highest in year " + year);
    }
    
    public void testGetAverageRank() {
        System.out.println("Average rank is " + getAverageRank("Robert", "M"));
    }
    
    public void testGetTotalBirthsRankedHigher() {
        System.out.println("Total Births ranked higher is " + getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }

}