package FindingGene.src;

import edu.duke.URLResource;

public class Part4 {

    public void readYoutubeWebLinks() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        int youtubeIndex = 0;
        int firstQuoteIndex = 0;
        int secondQuoteIndex = 0;
        String presearchString = null;

        // Loop through all words in the site
        for (String s : ur.lines()) {

            // Set a copy of the current string so that it can be read in after. 
            // We need to capture all cases of youtube, then restore the original string
            presearchString = s;

            s = s.toLowerCase();

            // If the word contains “youtube.com”
            youtubeIndex = s.indexOf("youtube.com");
            if (youtubeIndex != -1) {

                // Restore the original string
                s = presearchString;

                // Find the start of the link using " on the left
                firstQuoteIndex = s.lastIndexOf("\"", youtubeIndex);
                if (firstQuoteIndex != -1) {
                    
                    // Find the last " of the link on the right
                    secondQuoteIndex = s.indexOf("\"", firstQuoteIndex + 1);
                    if (secondQuoteIndex != -1) {
                    
                        // Return from the first quote to the end
                        System.out.println(s.substring(firstQuoteIndex + 1, secondQuoteIndex));
                    }
                }
            }
            
        }
    }

    public void testingBullshit() {
        URLResource file = new  URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
   	    
        for (String item : file.words()) {
            String itemLower = item.toLowerCase();
            int pos = itemLower.indexOf("youtube.com");
            if (pos != -1) {
                int beg = item.lastIndexOf("\"",pos);
                int end = item.indexOf("\"", pos+1);
                System.out.println(item.substring(beg+1,end));
            }
   	    }
    }

    public static void main(String[] args) throws Exception {
        Part4 part = new Part4();
        //part.readYoutubeWebLinks();
        part.testingBullshit();
    }
}
