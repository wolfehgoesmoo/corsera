package FindingGene.src;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class ImprovedGeneFinder {
    boolean DEBUG = false;

    public static void main(String[] args) throws Exception {
        // Print a couple of blank lines to separate from running blah
        System.out.println("");
        System.out.println("");

        ImprovedGeneFinder gf = new ImprovedGeneFinder();
        gf.testProcessGenes();
    }

    public StorageResource findAdvancedGenes(String dna) {
        int startCodonIndex = 0;
        int stopCodonIndex = 0;
        int currentStartIndex = 0;
        String currentGene = null;
        StorageResource genes = new StorageResource();
        boolean caseIsLower = false;

        // Determine current case state of dna, then convert to upper case
        caseIsLower = isLowerCase(dna);
        dna = dna.toUpperCase();

        // Loop through the dna strand for each occurrence of the start codon "ATG" until
        // we've gone through the entire strand and there are no more starting codon sequences.
        while (true) {
            
            // Find the start codon
            startCodonIndex = findStartCodon(dna, currentStartIndex, "ATG");
            if (DEBUG) { System.out.println("Start codon: " + startCodonIndex); }

            // If there is no start codon, exit loop
            if (startCodonIndex == -1) { break; }

            // Get the stop codon
            stopCodonIndex = getStopCodon(dna, startCodonIndex);
            if (DEBUG) { System.out.println("Stop codon: " + stopCodonIndex); }

            // If there is no stop codon, exit loop
            if (stopCodonIndex == -1) { break; }

            // Capture the gene from current start/stop codons (+3 is for codon length offset)
            currentGene = dna.substring(startCodonIndex, stopCodonIndex + 3);
            if (DEBUG) { System.out.println("Gene: " + currentGene); }
            
            // Add the currentGene to the list of genes
            genes.add(currentGene);

            // Set the next index to be after the end of the first discovered gene
            currentStartIndex = (stopCodonIndex + 3);
            if (DEBUG) { System.out.println("New starting index: " + currentStartIndex); }
        }
        // Return the genes
        return genes;
    }

    public int findStartCodon(String dna, int startCodonIndex, String startCodon) {

        // Search for the next start codon
        startCodonIndex = dna.indexOf(startCodon, startCodonIndex);
        if (DEBUG) { System.out.println("Current start codon index: " + startCodonIndex); }

        // If there isn't a start codon, then return -1, otherwise return the current start codon index
        if (startCodonIndex < 0) { 
            if (DEBUG) { System.out.println("No start codon found"); }
            return -1;
        } else {
            if (DEBUG) { System.out.println("Used start codon index: " + startCodonIndex); }
            return startCodonIndex;
        }

    }

    public String findAdvancedGene(String dna) {
        String gene = null;
        String startCodon = "ATG";
        int startCodonIndex = -1;
        int stopCodonIndex = -1;
        boolean caseIsLower = false;

        // Determine current case state of dna, then convert to upper case
        caseIsLower = isLowerCase(dna);
        dna = dna.toUpperCase();

        // Find the index of the start codon
        startCodonIndex = dna.indexOf(startCodon);
        if (DEBUG) { System.out.println("Used start codon index: " + startCodonIndex); }

        // Check to make sure there is a start codon in the dna strand
        if (startCodonIndex < 0) {
            return "";
        }
        
        // Get the index of the stop codon
        stopCodonIndex = getStopCodon(dna, startCodonIndex);
        if (DEBUG) { System.out.println("Used stop codon index: " + stopCodonIndex); }

        // Check to make sure there is a stop codon in the dna strand
        if (stopCodonIndex < 0) {
            return "";
        }

        // Capture the gene using codons as start/stop markers. 
        // +3 is to count for the offset of the length of the stop codon
        gene = dna.substring(startCodonIndex, stopCodonIndex + 3);
        if (DEBUG) { System.out.println("Used Gene: " + gene); }
        if (DEBUG) { System.out.println("Gene Length: " + gene.length()); }

        // Return it with proper casing
        if (caseIsLower) {
            // Return the gene in lower case
            return gene.toLowerCase();
        } else {
            // Return the gene in upper case
            return gene.toUpperCase();
        }
    }

    public int getStopCodon(String dna, int startCodonIndex) {
        int stopCodonIndex = 0;
        int minCodonIndex = dna.length();

        String[] stopCodon = new String[3];
        stopCodon[0] = "TAA";
        stopCodon[1] = "TGA";
        stopCodon[2] = "TAG";

        // Loop through each stop codon type
        for (int i = 0; i < stopCodon.length; i++) {

            // Fetch the first gene based on the stop codon
            stopCodonIndex = findStopCodon(dna, startCodonIndex, stopCodon[i]);

            // If it's the smallest gene and there was a returned index
            if ((stopCodonIndex < minCodonIndex) && (stopCodonIndex != -1)) {

                // Update the minCodonIndex to the new smallest stop codon index
                minCodonIndex = stopCodonIndex;
            }
        }

        // If there is a valid minCodonIndex, return it
        if (minCodonIndex != dna.length()) {
            return minCodonIndex;
        } else {
            return -1;
        }
    }

    public int findStopCodon(String dna, int startCodonIndex, String stopCodon) {
        int currentStopIndex = startCodonIndex;
        String gene = null;
        
        while (true) {
            
            // Search for the next stop codon
            currentStopIndex = dna.indexOf(stopCodon, currentStopIndex + 1);
            if (DEBUG) { System.out.println("Current stop codon index: " + currentStopIndex); }

            // Determine if there is a stop codon in the dna strand
            if (currentStopIndex < 0) {
                return -1;
            }

            // Capture the current gene using the start/stop codons
            gene = dna.substring(startCodonIndex, currentStopIndex + stopCodon.length());
            if (DEBUG) { System.out.println("Current gene found: " + gene); }
            if (isValidGene(gene)) {
                return currentStopIndex;
            } 
        }
    }

    public boolean isValidGene(String gene) {
        if (DEBUG) { System.out.println("Gene length: " + gene.length()); }
        // If the gene is valid
        if (gene.length() % 3 == 0) {
            if (DEBUG) { System.out.println("Gene length is valid"); }
            return true;
        } else {
            if (DEBUG) { System.out.println("Gene length is invalid"); }
            return false;
        }
    }
    
    public int howMany(String stringa, String stringb) {
        int countofStringaInb = 0;
        int index = 0;
        
        // Loop across stringb, find each occurance of string a in it
        while (true) {
            // Check for the next occurance of stringa in stringb
            index = stringb.indexOf(stringa, index);
            // if (DEBUG) { System.out.println("current index: " + index); }
            
            // If there isn't one, exit
            if (index == -1) {
                break;
            } else {
                // Otherwise increment the number of occurrences
                countofStringaInb++;
                // And then start the next iteration from after our last found occurrence
                index = index + stringa.length();
            }
        }
           
        return countofStringaInb;
    }

    public int countGenes(String dna) {
        StorageResource resultGenes = findAdvancedGenes(dna);
        return resultGenes.size();
    }

    public double cgRatio(String dna) {
        double cgRatio = 0.0;
        int cCount = 0;
        int gCount = 0;
        double dnaLength = dna.length();

        // Get the total number of cytosine and guanine nucleotides
        cCount = howMany("C", dna);
        if (DEBUG) { System.out.println("C: " + cCount); }
        gCount = howMany("G", dna);
        if (DEBUG) { System.out.println("G: " + gCount); }
        if (DEBUG) { System.out.println("Length: " + dnaLength); }

        // Create the ratio
        cgRatio = (cCount + gCount) / dnaLength;
        if (DEBUG) { System.out.println("ratio: " + cgRatio); }

        return cgRatio;
    }

    public int countCTG(String dna) {
        return howMany("CTG", dna);
    }
    
    public void processGenes(StorageResource sr) {
        int count = 0;

        // Print all the Strings in sr that are longer than 9 characters
        for (String currentString : sr.data()) {
            if (currentString.length() > 60) {
                System.out.println("Longer than 60 characters: " + currentString);
            }
        }
        System.out.println("----------------------------------------------------------------");

        // Print the number of Strings in sr that are longer than 9 characters
        for (String currentString : sr.data()) {
            if (currentString.length() > 60) {
                count++;
            }
        }
        System.out.println("Total number of strings longer than 60 characters: " + count);
        count = 0;
        System.out.println("----------------------------------------------------------------");

        // Print the Strings in sr whose C-G-ratio is higher than 0.35
        for (String currentString : sr.data()) {
            if (cgRatio(currentString) > 0.35) {
                System.out.println("C-G Ratio Higher than 0.35: " + currentString);
            }
        }
        System.out.println("----------------------------------------------------------------");

        // Print the number of strings in sr whose C-G-ratio is higher than 0.35
        for (String currentString : sr.data()) {
            if (cgRatio(currentString) > 0.35) {
                count++;
            }
        }
        System.out.println("Total number of strings with C-G ratio higher than 0.35: " + count);
        count = 0;
        System.out.println("----------------------------------------------------------------");

        // Print the length of the longest gene in sr
        for (String currentString : sr.data()) {
            if (currentString.length() > count) {
                count = currentString.length();
            }
        }
        System.out.println("Longest length of a gene: " + count);
        System.out.println("----------------------------------------------------------------");
    }

    // Testing methods
    public void testAdvancedGene() {
        String currentGene = null;
        String[] testCase = new String[8];
        testCase[0] = "TAGCTGCTAGCTCAGTGACTAAGCTGCA";      // Failure: No ATG
        testCase[1] = "TATGTCTGCTAGCTC";                   // Failure: No TAA
        testCase[2] = "TAGCTGCTAGCTCAGTGACTAGCTGCA";       // Failure: No ATG or TAA
        testCase[3] = "TATGCTGCTAGCTCAGTGTCTAAGCTGCA";     // Failure: ATG and TAA but not multiple of 3
        testCase[4] = "TATGCTGCTAGCTCAGTAACTGAGCTGCA";     // Success: Proper ATG and TAA and multiple of 3
        testCase[5] = "AAAAAATATGTCTGCTAAGCTCTAATGCAAG";   // Success: ATG, TAA, and multiple of 3, but first TAA is fake
        testCase[6] = "TTTTGGGGGATGTACTATAATCATAAGTAAGGG"; // Success: ATG, TAA, multiple of 3, first and second TAA is fake
        testCase[7] = "GATGGACTGAAATAAGTAAGAT";            // Success: New end codon TGA

        String[] resultSet = new String[8];
        resultSet[0] = "";
        resultSet[1] = "";
        resultSet[2] = "";
        resultSet[3] = "";
        resultSet[4] = "ATGCTGCTAGCTCAGTAA";
        resultSet[5] = "ATGTCTGCTAAGCTCTAA";
        resultSet[6] = "ATGTACTATAATCATAAGTAA";
        resultSet[7] = "ATGGACTGA";

        // Loop through all test cases
        for (int i = 0; i < testCase.length; i++) {
            
            // Find the gene in the DNA strand
            currentGene = findAdvancedGene(testCase[i]);

            // Determine if it matches the result set
            if (currentGene.equals(resultSet[i])) {
                // Passed
            } else {
                System.out.println("Test Case: " + i + " failed!");
                System.out.println("DNA Strand: " + testCase[i]);
                System.out.println("Returned Gene: " + currentGene);
                System.out.println("Expected Gene: " + resultSet[i]);
                System.out.println("");
            }
        }
        System.out.println("All tests finished.");
        // Print a couple of blank lines to separate from new line
        System.out.println("");
        System.out.println("");
    }

    public void testGetMultipleGenesInDNAStrand(String dna) {
        // Starting Strand
        // String[] dnaStrand = new String[1];
        // dnaStrand[0] = "AATGCTAACTAGCTGACTAAT";

        // Result Genes
        // String[] resultSet = new String[3];
        // resultSet[0] = "ATGxxxTAA";
        // resultSet[1] = "ATGyyyTAG";
        // resultSet[2] = "ATGvvvxtgabbhhhTGA";

        // Return all captured genes
        StorageResource resultGenes = findAdvancedGenes(dna);
        System.out.println("Genes found in DNA Strand: " + dna);
        printStorageResource(resultGenes);
        System.out.println("Number of genes found: " + resultGenes.size());

        // Compare captured results vs expected results
        // for (int i = 0; i < resultGenes.length; i++) {
        //     if (resultGenes[i].equals(resultSet[i])) {
        //         // Pass
        //     } else {
        //         // Fail
        //         System.out.println("Failed on genes: " + resultGenes[i]);
        //     }
        // }
        // System.out.println("All tests complete");
    }

    public void testHowMany() {
        System.out.println("There are " + howMany("GAA", "ATGAACGAATTGAATC") + " occurrences of GAA in ATGAACGAATTGAATC");
        System.out.println("There are " + howMany("AA", "ATAAAA") + " occurrences of AA in ATAAAA");
    }

    public void testCountGenes() {
        System.out.println("Genes count: " + countGenes("TACGATGGACTACGTCAGCTAGTXTATGCTACTACGCTGCATGTAACTAAGCATGCTAGCATGCA"));
    }

    public void testCGRatio() {
        System.out.println("cgRatio: " + cgRatio("ATGCCATAG"));
    }
    
    public void testCountCTG() {
        System.out.println("CTG Count: " + countCTG("ATGCTGCTCGCTGCTCGCTCGTGCGTCGGCTTGCTGCATAG"));
    }
    
    public void testProcessGenes() {
        StorageResource sr = new StorageResource();

        // Read in a file containing DNA information, convert it to a string
        FileResource fr = new FileResource();
        String dna = fr.asString();

        // Create a resource with all genes from the dna strand
        sr = findAdvancedGenes(dna);

        // Sneak peak into the genes first
        testGetMultipleGenesInDNAStrand(dna);

        // Then process them
        processGenes(sr);
        // System.out.println("Count: " + countCTG(dna));
    }

    public void printDNA() {
        String finalString = "";
        int count = 0;

        // Open the file
        FileResource fr = new FileResource();

        // Read it in as a string
        String dna = fr.asString();
        
        // Convert it to a char array
        char[] chars = dna.toCharArray();
 
        // Loop through each character
        for (char ch: chars) {
            
            // If count is 3 characters, then add a space
            if (count == 3) {
                finalString = finalString + " ";
                finalString = finalString + ch;
                count = 0;
            } else {
                finalString = finalString + ch;
            }
            count++;
        }
        System.out.println(finalString.toUpperCase());
    }

    // Helper methods
    public boolean isLowerCase(String s) {
        if (s == s.toLowerCase()) {
            return true;
        } else {
            return false;
        }
    }

    public String[] appendStringToArray(String stringToAdd, String[] originalArray) {
        int originalArraySize = originalArray.length;
        int newArraySize = originalArraySize + 1;

        // Create a new array with one additional element
        String[] newArray = new String[newArraySize];

        // Loop over the original array to populate the new array with original values
        for (int i = 0; i < originalArraySize; i++) {
            newArray[i] = originalArray[i];
        }

        // Add the new element to the array
        newArray[newArraySize - 1] = stringToAdd;

        // Return the new array
        return newArray;
    }

    public void printArray(String[] stringArray) {
        // Check to make sure there are elements in the array
        if (stringArray.length > 0) {

            // Then loop through it
            for (int i = 0; i < stringArray.length; i++) {
                
                // Print out the element if it's not null
                if (stringArray[i] != null) {
                    System.out.println(stringArray[i]);
                }
            }
        }
    }

    public void printStorageResource(StorageResource sr) {
        for (String currentString : sr.data()) {
            System.out.println(currentString);
        }
    }
}