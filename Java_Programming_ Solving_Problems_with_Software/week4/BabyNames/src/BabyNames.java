package src;

import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class BabyNames {
    String filePath = "Java_Programming_ Solving_Problems_with_Software/week4/course_project_files";
    String currentFilePath = filePath + "/us_babynames_test/";


    public static void main(String[] args) throws Exception {
        BabyNames bn = new BabyNames();
        bn.testGetRank();
    }

   
    public void getTotalBirthsPerFile(FileResource fr) {
        int numBorn = 0;
        int totalBirths = 0;
        int totalGirl = 0;
        int totalBoy = 0;

        // Loop through the records of the passed in file
        for (CSVRecord currentRecord : fr.getCSVParser(false)) {
            // Track numBirths
            numBorn = Integer.parseInt(currentRecord.get(2));
            totalBirths += numBorn;

            // Track number of male/female births
            if (currentRecord.get(1).equals("F")) {
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

    // Returns the rank of the name in the file for the given gender
    public int getRank(Integer year, String name, String gender) {
        int rankMale = 0;
        int rankFemale = 0;
        String yearBorn = String.valueOf(year);

        // Load in the file
        FileResource fr = new FileResource(currentFilePath + "yob" + yearBorn + "short.csv");
        // FileResource fr = new FileResource(currentFilePath + "yob2012short.csv");

        for (CSVRecord currentRecord : fr.getCSVParser(false)) {

            // Increment rank based on gender
            if (currentRecord.get(1).contains("M")) {
                rankMale++;
            } else {
                rankFemale++;
            }

            // Check all the records for the passed in name and matching gender
            if (currentRecord.get(0).contains(name) && currentRecord.get(1).contains(gender)) {
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









    // Tester Methods
    public void testTotalBirths() {
        // Create a file resource to pull in baby names
        FileResource fr = new FileResource();
        getTotalBirthsPerFile(fr);
    }

    public void testGetRank() {
        System.out.println("Rank: " + getRank(2012, "Mason", "F"));
    }


    /*
        Note: Baby name files are laid out as "Name" | "Gender" | "Count"
                                                0          1         2

    */

}
