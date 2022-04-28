package FindingGene.src;

public class ImprovedGeneFinder {
    boolean DEBUG = false;

    /*
        Introducing new stop codons:
            TGA
            TAG
            TAA
    */

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
        // Creating test cases for error checking. 
        // Only one test case should pass

        // Failure: No ATG
        String testCase1 = "TAGCTGCTAGCTCAGTGACTAAGCTGCA";
        // Failure: No TAA
        String testCase2 = "TATGTCTGCTAGCTC";
        // Failure: No ATG or TAA
        String testCase3 = "TAGCTGCTAGCTCAGTGACTAGCTGCA";
        // Failure: ATG and TAA but not multiple of 3
        String testCase4 = "TATGCTGCTAGCTCAGTGACTAAGCTGCA";
        // Success: Proper ATG and TAA and multiple of 3
        String testCase5 = "TATGCTGCTAGCTCAGTAACTGAGCTGCA";
        // Success: ATG, TAA, and multiple of 3, but first TAA is fake
        String testCase6 = "AAAAAATATGTCTGCTAAGCTCTAATGCAAG";
        // Success: ATG, TAA, multiple of 3, first and second TAA is fake
        String testCase7 = "TTTTGGGGGATGTACTATAATCATAAGTAAGGG";
        // Success: New end codon TGA
        String testCase8 = "GATGGACTGAAATAAGTAAGAT";

        // Print the DNA string in pairs with the search result
        System.out.println("");
        System.out.println("");
        System.out.println("");
        // No ATG
        System.out.println("Test Case 1: No ATG");
        System.out.println("DNA Strand: " + testCase1);
        System.out.println("Gene: " + findAdvancedGene(testCase1));
        System.out.println("");
        // No TAA
        System.out.println("Test Case 2: No TAA");
        System.out.println("DNA Strand: " + testCase2);
        System.out.println("Gene: " + findAdvancedGene(testCase2));
        System.out.println("");
        // No ATG or TAA
        System.out.println("Test Case 3: No ATG or TAA");
        System.out.println("DNA Strand: " + testCase3);
        System.out.println("Gene: " + findAdvancedGene(testCase3));
        System.out.println("");
        // ATG and TAA but not multiple of 3
        System.out.println("Test Case 4: ATG and TAA but not multiple of 3");
        System.out.println("DNA Strand: " + testCase4);
        System.out.println("Gene: " + findAdvancedGene(testCase4));
        System.out.println("");
        // Proper ATG and TAA and multiple of 3
        System.out.println("Test Case 5: ATG, TAA, and multiple of 3");
        System.out.println("DNA Strand: " + testCase5);
        System.out.println("Gene: " + findAdvancedGene(testCase5));
        System.out.println("");
        // ATG, TAA, and multiple of 3, but first TAA is fake
        System.out.println("Test Case 6: ATG, TAA, and multiple of 3 but first TAA is fake");
        System.out.println("DNA Strand: " + testCase6);
        System.out.println("Gene: " + findAdvancedGene(testCase6));
        System.out.println("");
        // ATG, TAA, multiple of 3, first and second TAA is fake
        System.out.println("Test Case 7: ATG, TAA, multiple of 3, first and second TAA is fake");
        System.out.println("DNA Strand: " + testCase7);
        System.out.println("Gene: " + findAdvancedGene(testCase7));
        System.out.println("");
        // New end codon TGA
        System.out.println("Test Case 8: New end codon TGA");
        System.out.println("DNA Strand: " + testCase8);
        System.out.println("Gene: " + findAdvancedGene(testCase8));
        System.out.println("");
    }

    public static void main(String[] args) throws Exception {
        ImprovedGeneFinder gf = new ImprovedGeneFinder();
        gf.testAdvancedGene();
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


