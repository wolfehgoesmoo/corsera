package FindingGene.src;

public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String gene = null;
        int startCodonIndex = 0;
        int endCodonIndex = 0;
        String caseState = null;

        // Determine current case state of dna
        if (dna == dna.toLowerCase()) {
            // Set state as lower
            caseState = "lower";
        } else {
            // Set state as upper
            caseState = "upper";
        }

        // Convert all input to upper case
        dna = dna.toUpperCase();
        startCodon = startCodon.toUpperCase();
        stopCodon = stopCodon.toUpperCase();

        // Find the first and last instances of the start/end codons
        startCodonIndex = dna.indexOf(startCodon);
        endCodonIndex = dna.indexOf(stopCodon, startCodonIndex);

        // Error Check: if either index is negative, then return error
        if (startCodonIndex < 0 || endCodonIndex < 0) {
            return "";
        }

        // Get the gene. The +3 offset is to account for the length of the end codon
        gene = dna.substring(startCodonIndex, endCodonIndex + 3);

        // Error Check: If the gene is not made up of codons, then return error
        if (gene.length() % 3 != 0) {
            return "";
        } 
        
        // Check the case to return the gene as
        if (caseState.equals("lower")) {
            // Return the gene in lower case
            return gene.toLowerCase();
        } else {
            // Return the gene in upper case
            return gene.toUpperCase();
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
        System.out.println("Result: " + findSimpleGene(testCase1, "ATG", "TAA"));
        // No TAA
        System.out.println("Gene: " + testCase2);
        System.out.println("Result: " + findSimpleGene(testCase2, "ATG", "TAA"));
        // No ATG or TAA
        System.out.println("Gene: " + testCase3);
        System.out.println("Result: " + findSimpleGene(testCase3, "ATG", "TAA"));
        // ATG and TAA but not multiple of 3
        System.out.println("Gene: " + testCase4);
        System.out.println("Result: " + findSimpleGene(testCase4, "ATG", "TAA"));
        // Proper ATG and TAA and multiple of 3
        //testCase5 = testCase5.toLowerCase();
        System.out.println("Gene: " + testCase5);
        System.out.println("Result: " + findSimpleGene(testCase5, "ATG", "TAA"));
    }

    public static void main(String[] args) throws Exception {
        Part2 p = new Part2();
        p.testSimpleGene();
    }
}
