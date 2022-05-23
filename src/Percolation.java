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

    public boolean begin = true;
    public boolean end = true;
    public boolean[] state;
    public WeightedQuickUnionUF qu;
    public int size; // L lenght of matrix LxL
    public int index;

    /**
     * Creates n-by-n grid (0 to n) with all sites blocked (false).
     * 
     * @param n : number of columns and rows
     */
    public Percolation(int n) {
        this.size = n;
        this.state = new boolean[n * n];
        this.qu = new WeightedQuickUnionUF(n * n + 2);

        // State false in all boxes of matrix except virtual points at the begin and end
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.state[i * size + j] = false;
            }
        }

    }

    /**
     * Opens the site (row, col) if it is not open already. Must be between 1-n,
     * otherwise returns false.
     * 
     * @param row of site.
     * @param col of site.
     */
    public void open(int row, int col) {
        index = row * size + col;
        state[index] = true;

        // Connect it to all of its adjacent open sites.
        if (isOpen(row + 1, col)) { // down
            qu.union(index, index + size);
        }
        if (isOpen(row - 1, col)) { // up
            qu.union(index, index - size);
        }
        if (isOpen(row, col + 1)) { // right
            qu.union(index, index + 1);
        }
        if (isOpen(row, col - 1)) { // left
            qu.union(index, index - 1);
        }

        // Testing if row 0 and row n are available for connect them.
        if (row == 0) {
            qu.union(col, size * size);
        }
        if (row == size - 1) {
            qu.union(row * size + col, size * size + 1);
        }
    }

    /**
     * @param row
     * @param col
     * @return true if it is open. False otherwise.
     */
    public boolean isOpen(int row, int col) {
        try {
            return state[row * size + col];
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * is the site (row, col) full?
     * 
     * @param row
     * @param col
     * @return true if it is open. False otherwise.
     */
    public boolean isFull(int row, int col) {
        return !isOpen(row, col);
    }

    /**
     * returns the number of open sites.
     * 
     * @return number of open sites in grid.
     */
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < size * size; i++) {
            if (state[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * If the system connects two end points, percolates, returns true. Depends on
     * Matrix manipulation.
     * 
     * @return true if percolates, false otherwise
     */
    public boolean percolates() {
        return qu.find(size * size) == qu.find(size * size + 1);
    }

    /**
     * Test client.
     * 
     * @param args
     */
    public static void main(String[] args) {
        StdOut.print("Size of matrix: ");
        final int l = StdIn.readInt();
        final Percolation per = new Percolation(l);
        do {
            StdOut.print("i (0 to " + (l - 1) + ")= ");
            final int p = StdIn.readInt();
            StdOut.print("j (0 to " + (l - 1) + ")= ");
            final int q = StdIn.readInt();
            per.open(p, q);

            // show() method include in main for not making troubles with API
            for (int i = 0; i < per.size; i++) {
                for (int j = 0; j < per.size; j++) {
                    if (per.isFull(i, j)) {
                        StdOut.print("-");
                    } else {
                        StdOut.print("+");
                    }
                    StdOut.print("\t");
                }
                StdOut.println();
            }
            // show also Id array in QU, with two end points
            for (int j = 0; j < per.size * per.size + 2; j++) {
                StdOut.print(per.qu.find(j) + " ");
            }
            StdOut.println();
        } while (!StdIn.isEmpty());

        StdOut.println("\n The system percolates?: " + per.percolates());
        StdOut.println("There are " + per.numberOfOpenSites() + " open sites.");
    }
}
