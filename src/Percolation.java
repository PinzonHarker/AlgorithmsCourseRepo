
/**
* Percolation of a simplificated grid system with LxL large.
* Filling all the sites and opening what we want. For Coursera
* Algorithms Course - Princeton University.
*
* @since 1.0
* @author Andrés Pinzón
* @version 1.0
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Finds percolation with begin and end points, and a matrix with sites blocked
 * and open.
 * It takes WeightedQuickUnionUF for UnionFind Algorithm.
 *
 * @author AndresPinzon
 */

public class Percolation {

    /**
     * State of all points given by {@code true} or {@code false},
     * open or closed resepectively.
     */
    private final boolean[] state;

    /** Data Structure for Union-Find problem. */
    private final WeightedQuickUnionUF qu;

    /** Size of a length of matrix of open sites {@code state}. */
    private final int size; // L length of matrix LxL

    /** Counts number of open sites. */
    private int countOpenSites;

    /**
     * Creates n-by-n grid (0 to n) with all sites blocked (false).
     *
     * @param n : number of columns and rows
     */
    public Percolation(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be > 0");
        }
        this.size = n;
        this.state = new boolean[n * n];
        this.qu = new WeightedQuickUnionUF(n * n + 2);
        countOpenSites = 0;

        // State false in all boxes of matrix except virtual
        // points at the begin and end
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.state[i * size + j] = false;
            }
        }

    }

    /**
     * Opens the site (row, col) if it is not open already.
     * Must be between 1-n, otherwise returns false.
     *
     * @param row of site.
     * @param col of site.
     */
    public void open(final int row, final int col) {
        validateSite(row, col);
        final int index = index(row, col);

        // if already open, stop
        if (isOpen(row, col)) {
            return;
        }

        // Opening the site and count.
        state[index] = true;
        countOpenSites++;

        // Connect it to all of its adjacent open sites.
        if (isOnGrid(row + 1, col) && isOpen(row + 1, col)) { // down
            qu.union(index, index + size);
        }
        if (isOnGrid(row - 1, col) && isOpen(row - 1, col)) { // up
            qu.union(index, index - size);
        }
        if (isOnGrid(row, col + 1) && isOpen(row, col + 1)) { // right
            qu.union(index, index + 1);
        }
        if (isOnGrid(row, col - 1) && isOpen(row, col - 1)) { // left
            qu.union(index, index - 1);
        }

        // Testing if row 0 and row n are available for connect them.
        if (row == 1) {
            qu.union(index(1, col), size * size);
        }
        if (row == size) {
            qu.union(index(row, col), size * size + 1);
        }
    }

    /**
     * Verifies if is it open.
     *
     * @param row It is the row parameter.
     * @param col It is the col parameter.
     * @return true if it is open. False otherwise.
     */
    public boolean isOpen(final int row, final int col) {
        validateSite(row, col);
        return state[index(row, col)];
    }

    /**
     * is the site (row, col) full?
     *
     * @param row It is the row parameter.
     * @param col It is the col parameter.
     * @return true if it is open. False otherwise.
     */
    public boolean isFull(final int row, final int col) {
        validateSite(row, col);
        return !isOpen(row, col);
    }

    /**
     * returns the number of open sites.
     *
     * @return number of open sites in grid.
     */
    public int numberOfOpenSites() {
        return countOpenSites;
    }

    /**
     * Validating row and column of argument used for different
     * points on Grid.
     *
     * @param row It is the row parameter.
     * @param col It is the col parameter.
     */
    private void validateSite(final int row, final int col) {
        if (!isOnGrid(row, col)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }

    /**
     * Calculates index of a matrix with row and col.
     *
     * @param row It is the row parameter.
     * @param col It is the col parameter.
     * @return index
     */
    private int index(final int row, final int col) {
        final int index = (row - 1) * size + (col - 1);
        return index;
    }

    /**
     * Verifies if the point given is on grid.
     *
     * @param row It is the row parameter.
     * @param col It is the col parameter.
     * @return condition
     */
    private boolean isOnGrid(final int row, final int col) {
        int shiftRow = row - 1;
        int shiftCol = col - 1;
        return (shiftRow >= 0
                && shiftCol >= 0 && shiftRow < size && shiftCol < size);
    }

    /**
     * If the system connects two end points, percolates, returns true.
     * Depends on Matrix manipulation.
     *
     * @return true if percolates, false otherwise
     */
    public boolean percolates() {
        return qu.find(size * size) == qu.find(size * size + 1);
    }

    /**
     * Shows the matrix for percolation in object.
     */
    private void show() {
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                if (isFull(i, j)) {
                    StdOut.print("-");
                } else if (isOpen(i, j)) {
                    StdOut.print("+");
                }
                StdOut.print("\t");
            }
            StdOut.println();
        }
    }

    /**
     * Test client.
     *
     * @param args for parameters not used by then, but probably.
     */
    public static void main(final String[] args) {
        StdOut.print("Size of matrix: ");
        final int l = StdIn.readInt();
        final Percolation per = new Percolation(l);
        do {
            StdOut.print("i (1 to " + (l) + ")= ");
            final int p = StdIn.readInt();
            StdOut.print("j (1 to " + (l) + ")= ");
            final int q = StdIn.readInt();
            per.open(p, q);

            // show() method include in main for not making troubles with API
            per.show();
            // show also Id array in QU, with two end points

            for (int j = 0; j < per.size * per.size + 2; j++) {
                StdOut.print(per.qu.find(j) + " ");
            }
            StdOut.println();
            StdOut.println("\n The system percolates?: " + per.percolates());
            StdOut.println("There are "
                    + per.numberOfOpenSites() + " open sites.");
        } while (!StdIn.isEmpty());

    }
}
