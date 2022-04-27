package perimeterAssignment.src;

public class Part2 {
    public String findSimpleGene(String dna) {
        String gene = null;
        int startCodonIndex = 0;
        int endCodonIndex = 0;

        // Find the first and last instances of the start/end codons
        startCodonIndex = dna.indexOf("ATG");
        endCodonIndex = dna.indexOf("TAA", startCodonIndex);

        // Error Check: if either index is negative, then return error
        if (startCodonIndex < 0 || endCodonIndex < 0) {
            return "";
        }

        // Get the gene. The +3 offset is to account for the length of the end codon
        gene = dna.substring(startCodonIndex, endCodonIndex + 3);

        // Error Check: If the gene is not made up of codons, then return error
        if (gene.length() % 3 != 0) {
            return "";
        } else {
            // Return the gene
            return gene;
        }
    }

    public void testSimpleGene() {
        // Creating test cases for error checking. 
        // Only one test case should pass

        // No ATG
        String testCase1 = "TAGCTGCTAGCTCAGTGACTAAGCTGCA";
        // No TAA
        String testCase2 = "TATGTCTGCTAGCTC";
        // No ATG or TAA
        String testCase3 = "TAGCTGCTAGCTCAGTGACTAGCTGCA";
        // ATG and TAA but not multiple of 3
        String testCase4 = "TATGCTGCTAGCTCAGTGACTAAGCTGCA";
        // Proper ATG and TAA and multiple of 3
        String testCase5 = "TATGCTGCTAGCTCAGTAACTGAGCTGCA";

        // Print the DNA string in pairs with the search result
        // No ATG
        System.out.println("Gene: " + testCase1);
        System.out.println("Result: " + findSimpleGene(testCase1));
        // No TAA
        System.out.println("Gene: " + testCase2);
        System.out.println("Result: " + findSimpleGene(testCase2));
        // No ATG or TAA
        System.out.println("Gene: " + testCase3);
        System.out.println("Result: " + findSimpleGene(testCase3));
        // ATG and TAA but not multiple of 3
        System.out.println("Gene: " + testCase4);
        System.out.println("Result: " + findSimpleGene(testCase4));
        // Proper ATG and TAA and multiple of 3
        System.out.println("Gene: " + testCase5);
        System.out.println("Result: " + findSimpleGene(testCase5));
    }
}
