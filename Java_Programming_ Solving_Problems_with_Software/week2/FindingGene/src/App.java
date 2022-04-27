package FindingGene.src;

public class App {
    
    public String getGeneFromDNA(String dnaStrand) {
        int startCodonIndex = 0;
        int endCodonIndex = 0;

        // Find the first and last instances of the start/end codons
        startCodonIndex = dnaStrand.indexOf("ATG");
        endCodonIndex = dnaStrand.indexOf("TAA", startCodonIndex);

        // Return the gene. The +3 offset is to account for the length of the end codon
        return dnaStrand.substring(startCodonIndex, endCodonIndex + 3);
    }
    
    public static void main(String[] args) throws Exception {
        String dnaStrand = "TACGATATGACTACGTTAGCATAACTAGATCGCT";
        String gene = null;
        App app = new App();
        gene = app.getGeneFromDNA(dnaStrand);
        System.out.println(gene);
    }
}


/*

Nucleotide: Single character in string represented by A, T, C, G
Codon: Three character string
Start Codon: Three character represented by ATG
End Codon: Three character represented by TAA
Gene: Everything between Start and End Codon, including both codons


*/