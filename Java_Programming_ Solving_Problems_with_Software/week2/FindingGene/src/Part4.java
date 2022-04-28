package FindingGene.src;

import edu.duke.URLResource;

public class Part4 {
    public void readYoutubeWebLinks() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.lines()) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Exception {
        Part4 part = new Part4();
        part.readYoutubeWebLinks();
    }
}
