package FindingGene.src;

public class App {
    
    public String getGeneFromDNA(String dnaStrand) {
        String gene = null;
        int startCodonIndex = 0;
        int endCodonIndex = 0;

        // Find the first and last instances of the start/end codons
        startCodonIndex = dnaStrand.indexOf("ATG");
        endCodonIndex = dnaStrand.indexOf("TAA", startCodonIndex);

        // Error Check: if either index is negative, then return error
        if (startCodonIndex < 0 || endCodonIndex < 0) {
            return "invalid start/end cod";
        }

        // Get the gene. The +3 offset is to account for the length of the end codon
        gene = dnaStrand.substring(startCodonIndex, endCodonIndex + 3);

        // Error Check: If the gene is not made up of codons, then return error
        if (gene.length() % 3 != 0) {
            return "invalid dnaStrand";
        }

        // Return the gene
        return gene;
    }
    
    public static void main(String[] args) throws Exception {
        String dnaStrand = "TACGATATGACTACGTTVAGCATAATACTAGATCGCT";
        String gene = null;
        App app = new App();
        gene = app.getGeneFromDNA(dnaStrand);
        System.out.println("Gene: " + gene);
        System.out.println("Length: " + gene.length());
    }
}


/*

Nucleotide: Single character in string represented by A, T, C, G
Codon: Three character string
Start Codon: Three character represented by ATG
End Codon: Three character represented by TAA
Gene: Everything between Start and End Codon, including both codons


*/