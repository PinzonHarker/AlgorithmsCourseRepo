/* import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats; */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// import java.util.Arrays;

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
    // public boolean[][] state;
    public boolean[] state1;
    public WeightedQuickUnionUF qu;
    public int size;    // L lenght of matrix LxL
    public int index;

    /**
     * Creates n-by-n grid (0 to n) with all sites blocked (false).
     * 
     * @param n : number of columns and rows
     */
    public Percolation(int n) {
        this.size = n;
        // this.state = new boolean[n][n];
        this.state1 = new boolean[n * n + 2];
        this.qu = new WeightedQuickUnionUF(n * n + 2);
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < n; j++) {
        // this.state[i][j] = false;
        // }
        // }

        // State false in all boxes of matrix except virtual points at the end as begin
        // and end.
        this.state1[size * size] = begin;
        this.state1[size * size + 1] = end;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.state1[i * size + j] = false;
            }
        }

        // for (int ii = 0; ii < n * n; i++){

        // }
    }

    /**
     * Opens the site (row, col) if it is not open already. Must be between 1-n,
     * otherwise returns false.
     * 
     * @param row of site.
     * @param col of site.
     */
    public void open(int row, int col) {
        // state[row][col] = true;

        index = row * size + col;
        state1[index] = true;

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
        } else if (row == size - 1) {
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
            return state1[row * size + col];
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
            if (state1[i]) {
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
     * Showing matrix in and percolation.
     */
    public void show() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isFull(i, j)) {
                    System.out.print("*");
                } else {
                    System.out.print("+");
                }
                System.out.print("\t");
            }
            System.out.println();
        }
        //show also id of Id array in QU
        for (int j = 0; j < size * size; j++) {
            System.out.print(qu.find(j) + " ");
        }
    }

    /**
     * Test client.
     * 
     * @param args
     */
    public static void main(String[] args) {
        final int n = 3;
        final Percolation p = new Percolation(n);

        p.open(0, 1);
        p.open(1, 1);
        p.open(2, 2);
        p.show();
        System.out.println(p.percolates());
    }
}
