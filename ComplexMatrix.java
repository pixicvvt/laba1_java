public class ComplexMatrix {
    private Complex[][] matrix;
    private int rows;
    private int col;

    public ComplexMatrix (int rows, int col) {
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
    
    public void setElement (int i, int j, Complex value) {
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

    public Complex determinant () {
        if (rows != col) {
            throw new IllegalArgumentException (
                "определитель можно посчитать только у квадратной матрицы"
            );
        }
        return determinantOf(matrix);
    }

    public Complex determinantOf(Complex[][] source) {
        int n = source.length;
        if (n==1) {
            return source[0][0];
        }
        if (n == 2) {
            Complex first = source[0][0].multiply(source[1][1]);
            Complex second = source[0][1].multiply(source[1][0]);
            return first.subtract(second);
        }
        Complex result = new Complex();

        for (int col = 0; col<n; col++) {
            Complex[][] minor = createMinor(source, 0, col);
            Complex term = source[0][col].multiply(determinantOf(minor));

            if (col%2 == 0){
                result = result.add(term);
            } else {
                result = result.subtract(term);
            }
        }
        return result;
    }

    private Complex[][] createMinor (Complex[][] source, int exRow, int exCol ) {
        int n = source.length;
        Complex[][] minor = new Complex[n-1][n-1];
        int mRow = 0;
        for (int i= 0; i<n; i++) {
            if (i == exRow) {
                continue;
            }
            int mCol = 0;
            for (int j = 0; j<n; j++) {
                if (j==exCol) {
                    continue;
                }
                minor[mRow][mCol] = source[i][j];
                mCol++;
            }
            mRow++;
        }
        return minor;
    }
    
    public ComplexMatrix divide(ComplexMatrix other) {
        if (rows != col || other.rows != other.col || rows != other.rows) {
            throw new IllegalArgumentException(
                "для деления нужны квадратные матрицы одинакового размера"
            );
        }
        Complex determinant = other.determinant();

        if (determinant.abs() < 0) {
            throw new ArithmeticException(
                "делитель второй матрицы равен 0"
            );
        }
        ComplexMatrix inverse = other.inverse();

        return this.multiply(inverse);
    }

    private ComplexMatrix inverse () {
        Complex determinant  = determinant();
        ComplexMatrix help = new ComplexMatrix(rows, col);
        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<col; j++) {
                Complex[][] minor = createMinor(matrix, i, j);
                Complex value;

                if (rows==1) {
                    value = new Complex(1, 0);
                } else {
                    value = determinantOf(minor);
                }
                if ((i+j)%2 != 0) {
                    value = value.negative();
                }
                help.matrix[i][j] = value;
            }
        }
         ComplexMatrix join = help.tranpose();
         ComplexMatrix result = new ComplexMatrix(rows, col);

         for (int i = 0; i<rows; i++) {
            for (int j = 0; j<col; j++) {
                result.matrix[i][j]=join.matrix[i][j].divide(determinant);
            }
         }
         return result;
    }

    public void print() {
        for (int i = 0; i<rows; i++){
            for (int j = 0; j<col; j++) {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

}
