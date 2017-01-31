package algorithms.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final byte CLOSED = 0;
    private static final byte OPEN = 1;

    private int topSite;
    private int bottomSite;
    private int n;
    private WeightedQuickUnionUF wquf;
    private byte[] status;
    private int openedSitesCount;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        int totalSize = (n * n) + 2;
        topSite = totalSize - 2;
        bottomSite = totalSize - 1;
        wquf = new WeightedQuickUnionUF(totalSize);
        status = new byte[(n*n)];
        for(int i=0; i< status.length; i++) {
            status[i] = CLOSED;
        }
        openedSitesCount = 0;
    }

    private int xyTo1D(int x, int y) {
        validateIndices(x, y);
        return (x * n + y) - (n + 1);
    }

    private void validateIndices(int row, int col) {
        if (!(row >= 1 && row <= n) || !(col >= 1 && col <= n)) {
            throw new IndexOutOfBoundsException("row and col values should be in the ran" +
                    "ge [1, " + n + "]: " + row + ", " + col);
        }
    }

    public void open(int row, int col) {
        validateIndices(row, col);
        if (!isOpen(row, col)) {
            status[xyTo1D(row, col)] = OPEN;
            openedSitesCount++;
            if (col - 1 >= 1) { // left
                if (isOpen(row, col - 1)) {
                    wquf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                }
            }
            if (col + 1 <= n) { // right
                if (isOpen(row, col + 1)) {
                    wquf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                }
            }
            if (row - 1 >= 1) { // top
                if (isOpen(row - 1, col)) {
                    wquf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                }
            }
            if (row + 1 <= n) { // bottom
                if (isOpen(row + 1, col)) {
                    wquf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                }
            }
            if (row == 1) {
                wquf.union(xyTo1D(row, col), topSite);
            }
            if (row == n) {
                wquf.union(xyTo1D(row, col), bottomSite);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        int node = xyTo1D(row, col);
        return status[node] == OPEN;
    }

    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        int node = xyTo1D(row, col);
        return wquf.connected(node, topSite);
    }

    public int numberOfOpenSites() {
        return openedSitesCount;
    }

    public boolean percolates() {
        return wquf.connected(topSite, bottomSite);
    }

}