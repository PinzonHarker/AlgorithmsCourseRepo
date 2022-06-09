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

    /** Probability of each trial.*/
    private final double[] probability;
    /** Number of trials to try for percolation.*/
    private final int trials;
    /** Constant of confidence 95%. */
    private static final double CONFIDENCE_95 = 1.96;

    /**
     * Creating percolation object for any trials.
     * @param n size of array
     * @param ntrials number of trials
     */
    public PercolationStats(final int n, final int ntrials) {
        this.trials = ntrials;
        if (this.trials <= 0) {
            throw new IllegalArgumentException("No trials to prove.");
        }

        Percolation p;
        probability = new double[this.trials];

        // Testing nuber of trials for some matrix.
        for (int ii = 0; ii < trials; ii++) {
            p = new Percolation(n);
            int count = 0;
            // When percolates return b
            while (!p.percolates()) {
                final int row = StdRandom.uniform(1, n + 1);
                final int col = StdRandom.uniform(1, n + 1);
                if (p.isFull(row, col)) {
                    p.open(row, col);
                    count++;
                }
            }
            probability[ii] = count / (n * n + 0.0);
        }
    }

    /** Sample mean of percolation threshold.
     *
     * @return mean
    */
    public double mean() {
        return StdStats.mean(probability);
    }

    /** Sample standard deviation of percolation threshold.
     *
     * @return Standard Desviation
    */
    public double stddev() {
        return StdStats.stddev(probability);
    }

    /**  low endpoint of 95% confidence interval.
     *
     * @return limit of confidence lower
    */
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    /**  Higer endpoint of 95% confidence interval.
     *
     * @return limit of confidence higer
    */
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(trials));
    }

    /**
     * Test client.
     * @param args arguments in
     */
    public static void main(final String[] args) {
        final int size = Integer.parseInt(args[0]);
        final int trials = Integer.parseInt(args[1]);
        final PercolationStats ps = new PercolationStats(size, trials);
        StdOut.println("mean\t= " + ps.mean());
        StdOut.println("stddev\t= " + ps.stddev());
        StdOut.println("confidenceHi\t= " + ps.confidenceHi());
        StdOut.println("confidenceLo\t= " + ps.confidenceLo());
    }

}
