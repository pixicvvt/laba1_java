import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static ComplexMatrix createMatrix() {
        System.out.print("введите количество строк: ");
        int rows = Integer.parseInt(scanner.next());

        System.out.print("введите количество столбцов: ");
        int col = Integer.parseInt(scanner.next());

        ComplexMatrix matrix = new ComplexMatrix(rows, col);

        for (int i = 0; i<rows; i++) {
            for (int j = 0; j<col; j++) {
                System.out.println("элемент [" + i + "][" + j + "]");

                System.out.print("действительная часть: ");
                double real = Double.parseDouble(scanner.next());

                System.out.print("мнимая часть: ");
                double img = Double.parseDouble(scanner.next());

                matrix.setElement(i, j, new Complex(real, img));
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        ComplexMatrix matrixA = null;
        ComplexMatrix matrixB = null;

        boolean code = true;

        while(code) {
            System.out.println();
            System.out.println("1. создать матрицу А");
            System.out.println("2. создать матрицу В");
            System.out.println("3. сложить А+В");
            System.out.println("4. умножить А*В");
            System.out.println("5. разделить А/В");
            System.out.println("6. транспонировать А");
            System.out.println("7. найти определитель А");
            System.out.println("0. выход");
            System.out.println("выберите операцию: ");
            
            try {
                int choice = Integer.parseInt(scanner.next());

                switch (choice) {
                    case 1: 
                        matrixA = createMatrix();
                        System.out.println("матрица А создана");
                        matrixA.print();
                        break;

                    case 2:
                        matrixB = createMatrix();
                        System.out.println("матрица D создана");
                        matrixB.print();
                        break;
                    case 3:
                        if (matrixA == null || matrixB == null) {
                            throw new IllegalArgumentException (
                                "сначала создайте обе матрицы"
                            );
                        }
                        System.out.println("A + B:");
                        matrixA.add(matrixB).print();
                        break;
                    case 4:
                        if (matrixA == null || matrixB == null) {
                            throw new IllegalArgumentException (
                                "сначала создайте обе матрицы"
                            );
                        }
                        System.out.println("A * B: ");
                        matrixA.multiply(matrixB).print();
                        break;
                    case 5:
                        if (matrixA == null || matrixB == null) {
                            throw new IllegalArgumentException (
                                "сначала создайте обе матрицы"
                            );
                        }
                        System.out.println("A/B: ");
                        matrixA.divide(matrixB).print();
                        break;
                    case 6:
                        if (matrixA == null) {
                            throw new IllegalArgumentException (
                                "сначала создайте матрицу"
                            );
                        }
                        System.out.println("транспонированная матрица А");
                        matrixA.tranpose().print();
                        break;
                    case 7:
                        if (matrixA == null) {
                            throw new IllegalArgumentException (
                                "сначала создайте матрицу"
                            );
                        }
                        System.out.println("det(А) = "+matrixA.determinant());
                        break;
                    case 0:
                        code = false;
                        System.out.println("программа завершена");
                        break; 
                    default:
                        System.out.println("такой операции нет");
                }
            }   catch (NumberFormatException e) {
                System.out.println(
                        "ошибка: нужно ввести число " +
                        "дробную часть пишите через точку"
                );
            }
        }
    }
}
