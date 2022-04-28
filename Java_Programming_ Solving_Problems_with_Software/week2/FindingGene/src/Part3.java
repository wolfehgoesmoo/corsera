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
            match = stringb.indexOf(stringa, match + 1);

            // If there's no match, return false
            if (match == -1) { 
                return false;
            } else {
                return true;
            }
        }
    }

    public void testingTwoOccurences() {
        Part3 p = new Part3();
        // Testing twoOccurrences with 4 test cases

        // Fail, no first occurrence
        System.out.println("Stringa: a");
        System.out.println("Stringb: bunny");
        if (p.twoOccurrences("a", "bunny")) {
            System.out.println("True");
        }

        // Fail, no second occurrence
        System.out.println("Stringa: a");
        System.out.println("Stringb: apple");
        if (p.twoOccurrences("a", "apple")) {
            System.out.println("True");
        }

        // Success, two occurrences
        System.out.println("Stringa: a");
        System.out.println("Stringb: karate");
        if (p.twoOccurrences("a", "karate")) {
            System.out.println("True");
        }

        // Success, more than two occurrences
        System.out.println("Stringa: a");
        System.out.println("Stringb: banana");
        if (p.twoOccurrences("a", "banana")) {
            System.out.println("True");
        }
    }

    public String lastPart(String stringa, String stringb) {
        int match = -1;

        // Search for a first occurrence of stringa in stringb
        match = stringb.indexOf(stringa);

        // If there is no match, return stringb
        if (match == -1) { 
            return stringb;
        } else {
            // return the end of stringa in stringb until the end of stringb
            return stringb.substring(match + stringa.length());
        }
    }
    
    public void testingLastPart() {
        Part3 p = new Part3();

        // Testing lastPart with 2 test cases

        // Success, stringa in stringb
        System.out.println("The part after the string an in banana is " +
            p.lastPart("an", "banana"));

        // Failure, stringa not in stringb
        System.out.println("The part after the string zoo in forest is " +
            p.lastPart("zoo", "forest"));
    }
    
    public static void main(String[] args) {
        Part3 p = new Part3();
        p.testingTwoOccurences();
        //p.testingLastPart();
    }
}
