//import Percolation.*;

/**
 * Stats makes statictics for a lot of n-by-n matrix and 
 * provides mean, standard desviation, confidence for 95% 
 * for low and high.
 * @author AndresPinzon
 */

public class PercolationStats {

    Percolation p;

    public PercolationStats() {

    }

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        for (int ii = 0; ii < trials; ii++){
            p = new Percolation(n);

        }
        // 
    }

    // sample mean of percolation threshold
    public double mean() {
        return 0.0;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 0.0;
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
        // final PercolationStats ps = new PercolationStats(1, 3);
    }

}
