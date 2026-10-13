public class ComplexMatrix {
    private Complex[][] matrix;
    private int rows;
    private int col;

    private ComplexMatrix (int rows, int col) {
        if (rows <= 0 || col <= 0) {
            throw new IllegalArgumentException(
                "количество столбцов и строк должно быть положительными"
            );
        }

    this.rows = rows;
    this.col = col;
    matrix = new Complex[rows][col];

    for (int i = 0; i<rows; i++) {
        for (int j = 0; j<col; j++) {
            matrix[i][j] = new Complex();
            }
        }
    }

    public int getRows () {
        return rows;
    }
    public int getCol () {
        return col;

    }
    
    public void setElements (int i, int j, Complex value) {
        matrix[i][j] = value;
    }

    public ComplexMatrix add (ComplexMatrix other) {
        if (rows!=other.rows || col!=other.col) {
            throw new IllegalArgumentException(
                "матрицы должны быть одинакового размера"
            );
        }
        ComplexMatrix result = new ComplexMatrix(rows, col);
        for (int i = 0; i<rows; i++) {
            for(int j = 0; j<col; j++) {
                result.matrix[i][j] = matrix[i][j].add(other.matrix[i][j]);
            }
        }
        return result;
    }

    public ComplexMatrix multiply (ComplexMatrix other) {
        if (col != other.rows) {
            throw new IllegalArgumentException(
                "количество столбцов первой матрицы должно совпадать с количеством строк второй"
            );
        }
        ComplexMatrix result = new ComplexMatrix(rows, other.col);
        for(int i = 0; i<rows; i++) {
            for(int j = 0; j<other.col; j++) {
                Complex sum = new Complex();
                for (int k = 0; k<col; k++) {
                    Complex product = matrix[i][k].multiply(other.matrix[k][j]);
                    sum = sum.add(product);
                }
                result.matrix[i][j] = sum;
            }
        }
        return result;
    }

    public ComplexMatrix tranpose () {
        ComplexMatrix result = new ComplexMatrix(col, rows);
        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<col; j++) {
                result.matrix[j][i] = matrix[i][j];
            }
        }
        return result;
    }
}
