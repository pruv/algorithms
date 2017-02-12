import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by java on 2/11/17.
 */
public class BruteCollinearPoints {

//    public static void main(String[] args) {
//        Point p1 = new Point(19000, 10000);
//        Point p2 = new Point(18000, 10000);
//        Point p3 = new Point(32000, 10000);
//        Point p4 = new Point(21000, 10000);
//        Point p5 = new Point(1234, 5678);
//        Point p6 = new Point(14000, 10000);
//        Point[] points = {p1, p2, p3, p4, p5, p6};
//        BruteCollinearPoints bcp = new BruteCollinearPoints(points);
//
//        System.out.println("Size: " + bcp.numberOfSegments());
//        for (LineSegment ls : bcp.lineSegments) {
//            System.out.println(ls);
//        }
//
//    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private List<LineSegment> lineSegments = new ArrayList<>();
    private int index;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new NullPointerException();
            }
        }
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        findCollinearPoints(points);
    }

    private void findCollinearPoints(Point[] points) {

        for (int i = 0; i < points.length - 4; i++) {
            for (int j = i + 1; j < points.length - 3; j++) {
                for (int k = j + 1; k < points.length - 2; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point p1 = points[i];
                        Point p2 = points[j];
                        Point p3 = points[k];
                        Point p4 = points[l];
                        double p1p2Slope = p1.slopeTo(p2);
                        double p2p3Slope = p2.slopeTo(p3);
                        double p3p4Slope = p3.slopeTo(p4);
                        if ((p1p2Slope == p2p3Slope && p2p3Slope == p3p4Slope)) {
                            System.out.println(p1 + ", " + p2 + ", " + p3 + ", " + p4);
                            lineSegments.add(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
