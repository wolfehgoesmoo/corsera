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

        // Get the shapes perimeter and it's number of points
        double length = getPerimeter(s);
        int totalPoints = getNumPoints(s);

        // Output the perimeter and number of points to the user
        System.out.println("Shape Perimeter: " + length);
        System.out.println("Shape Total Points: " + totalPoints);
    }

    public int getNumPoints(Shape s) {
        int totalPoints = 0;

        // Loop through each point in the shape
        for (Point currentPoint : s.getPoints()) {
            // Increment based on the current point
            totalPoints++;
        }
        // Return the number of points
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        // Return the total perimeter divided by its number of points
        return getPerimeter(s) / getNumPoints(s);
    }

    public static void main (String[] args) {
        // Create a new perimeterAssignment object
        perimeterAssignment pr = new perimeterAssignment();

        // Call the perimeterAssignment's method to test getting the perimeter of a shape
        pr.testPerimeter();
    }
}