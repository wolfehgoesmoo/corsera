package FindingGene.src;

public class Part3 {
    
    public boolean twoOccurrences(String stringa, String stringb) {
        int match = -1;

        // Search for a first occurrence of stringa in stringb
        match = stringb.indexOf(stringa);

        // If there is no match, return false
        if (match == -1) { 
            return false; 
        } else {
            // If there is a match
            // Search for the second occurrence of stringa in stringb
            // Search beyond the first match
            match = stringb.indexOf(stringa, match + stringa.length());

            // If there's no match, return false
            if (match == -1) { 
                return false;
            } else {
                System.out.println("True, found second occurrence at: " + match);
                return true;
            }
        }
    }

    public static void main(String[] args) {
        Part3 p = new Part3();
        if (p.twoOccurrences("a", "appla")) {
            System.out.println("True");
        }
    }
}
