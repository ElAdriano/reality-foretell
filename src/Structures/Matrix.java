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
        if (column >= 0 && column < columns && row >= 0 && row < rows) {
            matrix[row * columns + column] = number;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void add(int number, int row, int column) {
        if (column >= 0 && column < columns && row >= 0 && row < rows) {
            matrix[row * columns + column] += number;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int get(int row, int column) {
        if (column >= 0 && column < columns && row >= 0 && row < rows) {
            return matrix[row * columns + column];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int getColumnNumber() {
        return columns;
    }

    public int getRowNumber() {
        return rows;
    }

    public void fillAllMatrix(int number) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i * columns + j] = number;
            }
        }
    }

    public void fillAreaInMatrix(int number, int startRow, int endRow, int startColumn, int endColumn) {
        if (startColumn >= 0 && startColumn < columns && endColumn >= 0 && endColumn <= columns && startRow >= 0 && startRow < rows && endRow >= 0 && endRow <= rows) {
            for (int i = startRow; i < endRow; i++) {
                for (int j = startColumn; j < endColumn; j++) {
                    matrix[i * columns + j] = number;
                }
            }
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i * columns + j]);
                if (j < columns - 1) {
                    System.out.print("  ");
                }
            }
            System.out.print("]\n");
        }
    }

}
