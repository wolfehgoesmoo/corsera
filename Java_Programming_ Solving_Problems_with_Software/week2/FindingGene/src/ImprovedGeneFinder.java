package FindingGene.src;

public class ImprovedGeneFinder {
    
    public String findAdvancedGene(String dna, String startCodon, String stopCodon) {
        String gene = null;
        int startCodonIndex = 0;
        int stopCodonIndex = 0;
        boolean caseIsLower = false;
        ImprovedGeneFinder gf = new ImprovedGeneFinder();

        // Determine current case state of dna
        caseIsLower = gf.isLowerCase(dna);
  
        // Convert all input to upper case
        dna = dna.toUpperCase();
        startCodon = startCodon.toUpperCase();
        stopCodon = stopCodon.toUpperCase();

        // Find the index of the start of the gene
        startCodonIndex = dna.indexOf(startCodon);
        // System.out.println("Start codon index: " + startCodonIndex);

        // Check to make sure there is a start codon in the dna strand
        if (startCodonIndex < 0) {
            return "";
        }

        // Assign the index of the stop codon to the start codon so that as it loops it starts
        // looking for a stop codon after the first found start codon
        stopCodonIndex = startCodonIndex;

        while (true) {
            // Search for the next stop codon
            stopCodonIndex = dna.indexOf(stopCodon, stopCodonIndex + 1);
            //System.out.println("Stop codon index: " + stopCodonIndex);

            // Determine if there is a stop codon in the dna strand
            if (stopCodonIndex < 0) {
                return "";
            }

            // Capture the current gene using the start/stop codons
            gene = dna.substring(startCodonIndex, stopCodonIndex + startCodon.length());
            //System.out.println("Gene found: " + gene);

            // If the gene is valid
            if (gene.length() % 3 == 0) {
                // Return it with proper casing
                if (caseIsLower) {
                    // Return the gene in lower case
                    return gene.toLowerCase();
                } else {
                    // Return the gene in upper case
                    return gene.toUpperCase();
                }
            } 
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

        // Print the DNA string in pairs with the search result
        // No ATG
        // System.out.println("Test Case 1: No ATG");
        System.out.println("DNA Strand: " + testCase1);
        System.out.println("Gene: " + findAdvancedGene(testCase1, "ATG", "TAA"));
        // No TAA
        // System.out.println("Test Case 2: No TAA");
        System.out.println("DNA Strand: " + testCase2);
        System.out.println("Gene: " + findAdvancedGene(testCase2, "ATG", "TAA"));
        // No ATG or TAA
        // System.out.println("Test Case 3: No ATG or TAA");
        System.out.println("DNA Strand: " + testCase3);
        System.out.println("Gene: " + findAdvancedGene(testCase3, "ATG", "TAA"));
        // ATG and TAA but not multiple of 3
        // System.out.println("Test Case 4: ATG and TAA but not multiple of 3");
        System.out.println("DNA Strand: " + testCase4);
        System.out.println("Gene: " + findAdvancedGene(testCase4, "ATG", "TAA"));
        // Proper ATG and TAA and multiple of 3
        // System.out.println("Test Case 5: ATG, TAA, and multiple of 3");
        System.out.println("DNA Strand: " + testCase5);
        System.out.println("Gene: " + findAdvancedGene(testCase5, "ATG", "TAA"));
        // ATG, TAA, and multiple of 3, but first TAA is fake
        // System.out.println("Test Case 6: ATG, TAA, and multiple of 3 but first TAA is fake");
        System.out.println("DNA Strand: " + testCase6);
        System.out.println("Gene: " + findAdvancedGene(testCase6, "ATG", "TAA"));
        // ATG, TAA, multiple of 3, first and second TAA is fake
        // System.out.println("Test Case 7: ATG, TAA, multiple of 3, first and second TAA is fake");
        System.out.println("DNA Strand: " + testCase7);
        System.out.println("Gene: " + findAdvancedGene(testCase7, "ATG", "TAA"));
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
