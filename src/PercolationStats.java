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

    /** Constant of confidence 95%. */
    private static final double CONFIDENCE_95 = 1.96;
    /** Probability of each trial. */
    private final double[] probability;
    /** Number of trials to try for percolation. */
    private final int ntrials;

    /**
     * Creating percolation object for any trials.
     *
     * @param n      size of array
     * @param trials number of trials
     */
    public PercolationStats(final int n, final int trials) {
        if (trials <= 0) {
            throw new IllegalArgumentException("No trials to prove.");
        }

        // Percolation p;
        this.ntrials = trials;
        probability = new double[this.ntrials];

        // Testing nuber of trials for some matrix.
        for (int ii = 0; ii < trials; ii++) {
            Percolation p = new Percolation(n);
            // When percolates return b
            while (!p.percolates()) {
                final int row = StdRandom.uniform(1, n + 1);
                final int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            probability[ii] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    /**
     * Sample mean of percolation threshold.
     *
     * @return mean
     */
    public double mean() {
        return StdStats.mean(probability);
    }

    /**
     * Sample standard deviation of percolation threshold.
     *
     * @return Standard Desviation
     */
    public double stddev() {
        return StdStats.stddev(probability);
    }

    /**
     * low endpoint of 95% confidence interval.
     *
     * @return limit of confidence lower
     */
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(ntrials));
    }

    /**
     * Higer endpoint of 95% confidence interval.
     *
     * @return limit of confidence higer
     */
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(ntrials));
    }

    /**
     * Test client.
     *
     * @param args arguments in
     */
    public static void main(final String[] args) {
        final int size = Integer.parseInt(args[0]);
        final int trials = Integer.parseInt(args[1]);
        final PercolationStats ps = new PercolationStats(size, trials);
        final String confidence = "[" + ps.confidenceLo()
                + ", " + ps.confidenceHi() + "]";
        StdOut.println("mean\t= " + ps.mean());
        StdOut.println("stddev\t= " + ps.stddev());
        StdOut.println("95% confidence interval\t = " + confidence);
    }

}
