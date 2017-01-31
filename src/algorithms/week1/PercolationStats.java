package algorithms.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by java on 1/29/17.
 */
public class PercolationStats {

    private double[] probabilities;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        probabilities = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            probabilities[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public static void main(String[] args) {
        List<String> lines = null;
        try {
            String fileName = "/Users/java/Desktop/Coursera/Algorithms-1/week1/testdata/input20.txt";
            lines = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int n = Integer.parseInt(lines.get(0));
        Percolation percolation = new Percolation(n);
        for (int i = 1; i < lines.size(); i++) {
            String[] numbers = lines.get(i).split(" ");
            percolation.open(Integer.parseInt(numbers[2]), Integer.parseInt(numbers[4]));
        }
        PercolationVisualizer.draw(percolation, n);
    }

//    public static void main(String[] args) {
//        if (args.length == 2) {
//            int n = Integer.parseInt(args[0]);
//            int T = Integer.parseInt(args[1]);
//            PercolationStats percolationStats = new PercolationStats(n, T);
//            System.out.println("mean = " + percolationStats.mean());
//            System.out.println("stddev = " + percolationStats.stddev());
//            System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " +
//                    percolationStats.confidenceHi() + "]");
//        }
//
//    }

    public double mean() {
        return StdStats.mean(probabilities);
    }

    public double stddev() {
        return StdStats.stddev(probabilities);
    }

    public double confidenceLo() {
        double confidenceLo = mean() - ((1.96 * stddev()) / Math.sqrt(probabilities.length));
        return confidenceLo;
    }

    public double confidenceHi() {
        double confidenceLo = mean() + ((1.96 * stddev()) / Math.sqrt(probabilities.length));
        return confidenceLo;
    }
}
