package src;

import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class BabyNames {
    public static void main(String[] args) throws Exception {
        BabyNames bn = new BabyNames();
        bn.testTotalBirths();
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











    // Tester Methods
    public void testTotalBirths() {
        // Create a file resource to pull in baby names
        FileResource fr = new FileResource();
        getTotalBirthsPerFile(fr);
    }



    /*
        Note: Baby name files are laid out as "Name" | "Gender" | "Count"
                                                0          1         2

    */

}
