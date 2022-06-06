import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Stats makes statictics for a lot of n-by-n matrix and
 * provides mean, standard desviation, confidence for 95%
 * for low and high.
 * 
 * 
 * 
 * @author AndresPinzon
 */

public class PercolationStats {

    private final double probability[];
    private final int trials;
    private final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        Percolation p;
        this.trials = trials;
        probability = new double [trials];
        if (trials < 0) {
            throw new IllegalArgumentException("No trials to prove.");
        } 
        // Testing nuber of trials for some matrix.
        for (int ii = 0; ii < trials; ii++) {
            p = new Percolation(n);
            int count = 0;
            // When percolates return b
            while (!p.percolates()) {
                final int r1 = StdRandom.uniform(n);
                final int r2 = StdRandom.uniform(n);
                if (p.isFull(r1, r2)) {
                    p.open(r1, r2);
                    count++;
                }
            }
            probability[ii] = count / (n * n + 0.0);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(probability);
    }
    

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(probability);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * Math.sqrt(stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * Math.sqrt(stddev()) / Math.sqrt(trials));
    }

    // test client (see below)
    public static void main(String args[]) {
        final int size = Integer.parseInt(args[0]);
        final int trials = Integer.parseInt(args[1]);
        final PercolationStats ps = new PercolationStats(size, trials);
        StdOut.println("mean\t= " + ps.mean());
        StdOut.println("stddev\t= " + ps.stddev());
        StdOut.println("confidenceHi\t= " + ps.confidenceHi());
        StdOut.println("confidenceLo\t= " + ps.confidenceLo());
    }

}
