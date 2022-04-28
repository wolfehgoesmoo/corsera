package FindingGene.src;

public class ImprovedGeneFinder {
    boolean DEBUG = false;

    public static void main(String[] args) throws Exception {
        ImprovedGeneFinder gf = new ImprovedGeneFinder();
        gf.testAdvancedGene();
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


        // Print a couple of blank lines to separate from running blah
        System.out.println("");
        System.out.println("");

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

    public void testMultipleGenesInSingleDNAStrand() {
        String[] dnaStrand = new String[0];
        dnaStrand[0] = "xxxATGxxxTAAxxxATGyyyTAGxxx";
        String[] resultSet = new String[1];
        resultSet[0] = "ATGxxxTAA";
        resultSet[1] = "ATGyyyTAG";
    }

    // Helper methods
    public boolean isLowerCase(String s) {
        if (s == s.toLowerCase()) {
            return true;
        } else {
            return false;
        }
    }
}


