package com.general.utils;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 10/26/13
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Matrix<T> {
    private final int numOfRows;
    private final int numOfColumns;
    private final Object[][] data;

    /**
     * Create M-by-N matrix
     *
     * @param m - Number of rows
     * @param n - Number of columns
     */
    @SuppressWarnings({"unchecked"})
    public Matrix(int m, int n) {
        numOfRows = m;
        numOfColumns = n;
        data = new Object[m][n];
    }

    /**
     * Create a matrix based on a 2D array
     *
     * @param data - 2D array used to create the matrix
     */
    public Matrix(T[][] data) {
        numOfRows = data.length;
        numOfColumns = data[0].length;
        this.data = new Object[numOfRows][numOfColumns];
        for (int i = 0; i < numOfRows; i++)
            for(int j = 0; j < numOfColumns; j++)
                this.data[i][j] = data[i][j];
    }

    /**
     * Returns a random M-by-N matrix with values between 0 and 1
     *
     * @param m - Number of rows
     * @param n - Number of columns
     * @return - Random MxN matrix
     */
    public static Matrix random(int m, int n) {
        Matrix a = new Matrix(m, n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a.data[i][j] = Math.random();
        return a;
    }

    /**
     * Returns an N-by-N identity matrix
     *
     * @param n - Number of rows/columns
     * @return - Identity matrix
     */
    public static Matrix identity(int n) {
        Matrix id = new Matrix(n, n);
        for (int i = 0; i < n; i++)
            id.data[i][i] = 1;
        return id;
    }

    /**
     * Swaps rows i and j
     *
     * @param i - Row to be swapped with row j
     * @param j - Row to be swapped with row i
     */
    private void swap(int i, int j) {
        Object[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * Return the transpose of this matrix
     *
     * @return - The transpose of this matrix
     */
    public Matrix transpose() {
        Matrix a = new Matrix(numOfRows, numOfColumns);
        for (int i = 0; i < numOfRows; i++)
            for (int j = 0; j < numOfColumns; j++)
                a.data[j][i] = this.data[i][j];
        return a;
    }

    public Matrix plus(Matrix B) {

    }
}
