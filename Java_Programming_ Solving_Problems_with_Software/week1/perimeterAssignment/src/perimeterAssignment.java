package perimeterAssignment.src;
import java.io.File;

import edu.duke.DirectoryResource;
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

        // Get the shapes perimeter, it's number of points, 
        // it's average length, the longest length of any given side, and the largestX value for
        // all points.
        double length = getPerimeter(s);
        int totalPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double longestLength = getLargestSide(s);
        double largestX = getLargestX(s);

        // Output the perimeter, number of points, average length, longest length, 
        // and largest x value to the user
        System.out.println("Shape Perimeter: " + length);
        System.out.println("Shape Total Points: " + totalPoints);
        System.out.println("Average Length: " + averageLength);
        System.out.println("Longest Length: " + longestLength);
        System.out.println("Largest X: " + largestX);
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

    public double getLargestSide(Shape s) {
        double longestLength = 0.0;
        Point prevPt = s.getLastPoint();

        // Loop through each point in the shape
        for (Point currPt : s.getPoints()) {

            // Get the distance (length) between last point and current point
            double currDist = prevPt.distance(currPt);

            // Compare to stored longestLength to current length
            if (currDist > longestLength) {
                // If the current length is greater, replace the longestLength value
                longestLength = currDist;
            }

            // Update the last point with the current point
            prevPt = currPt;
        }
        // Return the longestLength
        return longestLength;
    }

    public double getLargestX(Shape s) {
        double largestX = 0.0;

        // Loop over all points
        for (Point currPt : s.getPoints()) {

            // Compare current largestX to currentX
            if (currPt.getX() > largestX) {
                
                // If it is greater, replace the current
                largestX = currPt.getX();
            }
        }

        // Return the largest
        return largestX;
    }
    
    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0.0;
        double length = 0.0;

        // Select multiple files
        DirectoryResource dr = new DirectoryResource();

        // Loop through each file in the directory
        for (File f : dr.selectedFiles()) {

            // Open the current file
            FileResource fr = new FileResource(f);

            // Create a new shape based on the current file
            Shape s = new Shape(fr);

            // Calculate the perimeter of the shape
            length = getPerimeter(s);

            // If the current perimeter is larger than the stored
            if (length > largestPerimeter) {

                // Update the stored perimeter to be the current
                largestPerimeter = length;
            }
        }
        // Return the largest perimeter
        return largestPerimeter;
    }

    public void testPerimeterMultipleFiles() {
        // Get the largestPerimeter of all files
        double largestPerimeter = getLargestPerimeterMultipleFiles();

        // Display it to the user
        System.out.println("Largest Perimeter Of Files: = " + largestPerimeter);
    }

    public String getFileWithLargestPerimeter() {
        double largestPerimeter = 0.0;
        double length = 0.0;
        File fileWithLargestPerimeter = null;

        // Select multiple files
        DirectoryResource dr = new DirectoryResource();

        // Loop through all files in the directory
        for (File f : dr.selectedFiles()) {

            // Open the current file
            FileResource fr = new FileResource(f);

            // Create a new shape based on the current file
            Shape s = new Shape(fr);

            // Calculate the perimeter for current shape
            length = getPerimeter(s);

            // If it's the largest perimeter
            if (length > largestPerimeter) {

                // Update both the stored longest perimeter to the current perimeter, 
                // and update the file name to the new perimeter's source file name
                largestPerimeter = length;
                fileWithLargestPerimeter = f;
            }
        }
        // Return the file name of the largest perimeter
        return fileWithLargestPerimeter.getName();
    }
    public static void main (String[] args) {
        // Create a new perimeterAssignment object
        perimeterAssignment pr = new perimeterAssignment();

        // Call the perimeterAssignment's method to test getting the perimeter of a shape
        pr.testPerimeter();
    }
}