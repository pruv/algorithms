import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by java on 2/11/17.
 */
public class FastCollinearPoints {

    private List<LineSegment> lineSegments = new ArrayList<>();

//    public static void main(String[] args) {
//
////        Point p1 = new Point(9000, 10000);
////        Point p2 = new Point(18000, 10000);
////        Point p3 = new Point(32000, 10000);
////        Point p4 = new Point(21000, 10000);
////        Point p5 = new Point(1234, 5678);
////        Point p6 = new Point(14000, 10000);
////        Point[] points = {p1, p2, p3, p4, p5, p6};
//
//        //(3000, 4000), (6000, 7000), (14000, 15000), (20000, 21000)
////        Point p1 = new Point(10000, 0);
////        Point p2 = new Point(0, 10000);
////        Point p3 = new Point(3000, 7000);
////        Point p4 = new Point(7000, 3000);
////        Point p5 = new Point(20000, 21000);
////        Point p6 = new Point(3000, 4000);
////        Point p7 = new Point(14000, 15000);
////        Point p8 = new Point(6000, 7000);
////        Point[] points = {p1, p2, p3, p4, p5, p6, p7, p8};
//
//        Point p1 = new Point(16000, 16000);
//        Point p2 = new Point(10000, 10000);
//        Point p3 = new Point(20000, 20000);
//        Point[] points = {p1};
//        FastCollinearPoints fcp = new FastCollinearPoints(points);
//
//        System.out.println("Size: " + fcp.numberOfSegments());
//        for (LineSegment ls : fcp.lineSegments) {
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new NullPointerException();
            }
        }
        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);
        for (int i = 1; i < copy.length; i++) {
            if (copy[i].compareTo(copy[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
        if (copy.length < 4) {
            return;
        }
        findCollinearPoints(copy);
    }

    private void findCollinearPoints(Point[] points) {
        Point[] copy = Arrays.copyOf(points, points.length);
        for (int i = 0; i < points.length; i++) {
            List<Point> collinearPoints = new ArrayList<>();
            Point p = points[i];
            Arrays.sort(copy, p.slopeOrder());
            double prevSlope = p.slopeTo(copy[1]);
            collinearPoints.add(copy[1]);
            for (int j = 2; j < copy.length; j++) {
                double currentSlope = p.slopeTo(copy[j]);
                if (prevSlope == currentSlope) {
                    collinearPoints.add(copy[j]);
                } else {
                    collinearPoints.add(p);
                    if (collinearPoints.size() > 3) {
                        addLineSegment(collinearPoints);
                    }
                    collinearPoints.clear();
                    collinearPoints.add(copy[j]);
                }
                prevSlope = currentSlope;
            }
            collinearPoints.add(p);
            if (collinearPoints.size() > 3) {
                addLineSegment(collinearPoints);
            }
        }
    }

    private void addLineSegment(List<Point> collinearPoints) {
        Point[] cpArray = collinearPoints.toArray(new Point[collinearPoints.size()]);
        Arrays.sort(cpArray);
        LineSegment lineSegment = new LineSegment(cpArray[0], cpArray[cpArray.length - 1]);
        boolean exists = false;
        for (LineSegment ls : lineSegments) {
            if (lineSegment.toString().equalsIgnoreCase(ls.toString())) {
                exists = true;
            }
        }
        if (!exists) {
            lineSegments.add(lineSegment);
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}
