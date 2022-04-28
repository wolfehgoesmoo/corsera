package FindingGene.src;

import edu.duke.URLResource;

public class Part4 {
    
    public void readYoutubeWebLinks() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        int youtubeIndex = 0;
        int firstQuoteIndex = 0;
        int secondQuoteIndex = 0;

        // Loop through all words in the site
        for (String s : ur.lines()) {
            
            // If the word contains “youtube.com”
            youtubeIndex = s.indexOf("youtube.com");
            if (youtubeIndex != -1) {

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

    public static void main(String[] args) throws Exception {
        Part4 part = new Part4();
        part.readYoutubeWebLinks();
    }
}
