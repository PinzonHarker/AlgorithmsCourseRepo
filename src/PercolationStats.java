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

    Percolation p;
    double probability[];
    int count;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        probability = new double [trials];
        if (trials < 0) {
            throw new IllegalArgumentException("No trials to prove.");
        } 
        // Testing nuber of trials for some matrix.
        for (int ii = 0; ii < trials; ii++) {
            p = new Percolation(n);
            count = 0;
            // When percolates return b
            while (!p.percolates()) {
                final int r1 = StdRandom.uniform(n);
                final int r2 = StdRandom.uniform(n);
                if (p.isFull(r1, r2)){
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
        return 0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0.0;
    }

    // test client (see below)
    public static void main(String[] args) {
        final int size = Integer.parseInt(args[0]);
        final int trials = Integer.parseInt(args[1]);
        final PercolationStats ps = new PercolationStats(size, trials);
        StdOut.println("mean \t= " + ps.mean());
        StdOut.println("stddev \t= " + ps.stddev());
        
    }

}
