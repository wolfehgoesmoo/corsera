package perimeterAssignment.src;

public class Part1 {
    
    public String findSimpleGene(String dna) {
        String gene = null;
        int startCodonIndex = 0;
        int endCodonIndex = 0;

        // Find the first and last instances of the start/end codons
        startCodonIndex = dna.indexOf("ATG");
        endCodonIndex = dna.indexOf("TAA", startCodonIndex);

        // Error Check: if either index is negative, then return error
        if (startCodonIndex < 0 || endCodonIndex < 0) {
            return "invalid start/end cod";
        }

        // Get the gene. The +3 offset is to account for the length of the end codon
        gene = dna.substring(startCodonIndex, endCodonIndex + 3);

        // Error Check: If the gene is not made up of codons, then return error
        if (gene.length() % 3 != 0) {
            return "invalid gene";
        } else {
            // Return the gene
            return gene;
        }
    }
}
