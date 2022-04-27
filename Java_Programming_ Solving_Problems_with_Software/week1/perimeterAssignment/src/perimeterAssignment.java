package perimeterAssignment.src;
import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;

public class perimeterAssignment {

    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        // Create a file open dialog box to select a text file with shape data
        FileResource fr = new FileResource();

        // Load in the shape data to create a new shape object
        Shape s = new Shape(fr);

        // Find the loaded shapes perimeter
        double length = getPerimeter(s);

        // Output the perimeter to the user
        System.out.println("perimeter = " + length);
    }

    public static void main (String[] args) {
        // Create a new perimeterAssignment object
        perimeterAssignment pr = new perimeterAssignment();

        // Call the perimeterAssignment's method to test getting the perimeter of a shape
        pr.testPerimeter();
    }
}