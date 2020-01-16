package Structures;

public class Matrix {

    private int rows = 0;
    private int columns = 0;
    private int[] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new int[rows * columns];
    }

    public void put(int number, int row, int column) {
        if (column >= 0 && column < this.columns && row >= 0 && row < this.rows) {
            matrix[row * this.columns + column] = number;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void add(int number, int row, int column) {
        if (column >= 0 && column < this.columns && row >= 0 && row < this.rows) {
            matrix[row * this.columns + column] += number;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int get(int row, int column) {
        if (column >= 0 && column < this.columns && row >= 0 && row < this.rows) {
            return matrix[row * this.columns + column];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int getRowsNumber() {
        return this.rows;
    }

    public int getColumnsNumber() {
        return this.columns;
    }


    public void fillAllMatrix(int number) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                matrix[i * this.columns + j] = number;
            }
        }
    }

    public void fillAreaInMatrix(int obj, int start_row, int end_row, int start_column, int end_column) {
        if (start_column >= 0 && start_column < this.columns && end_column >= 0 && end_column <= this.columns && start_row >= 0 && start_row < this.rows && end_row >= 0 && end_row <= this.rows) {
            for (int i = start_row; i < end_row; i++) {
                for (int j = start_column; j < end_column; j++) {
                    matrix[i * this.columns + j] = obj;
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void writeMatrix() {
        for (int i = 0; i < this.rows; i++) {
            System.out.print("[");
            for (int j = 0; j < this.columns; j++) {
                System.out.print(matrix[i * this.columns + j]);
                if (j < this.columns - 1) {
                    System.out.print("  ");
                }
            }
            System.out.print("]\n");
        }
    }

}
