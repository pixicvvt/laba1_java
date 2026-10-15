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
        ComplexMatrix curr = null;
        boolean code = true;

        System.out.println("введите матрицу");
         
        while (curr == null) {
            try {
                curr = createMatrix();
            } catch (NumberFormatException e) {
                System.out.println("нужно ввести число");
            } 
        }

        while(code) {
            System.out.println("текущая матрица:");
            curr.print();
            System.out.println();
            System.out.println("1. прибавить другую матрицу");
            System.out.println("2. умножить на другую матрицу");
            System.out.println("3. разделить на другую матрицу");
            System.out.println("4. транспонировать текущую матрицу");
            System.out.println("5. найти определитель текущей матрицы");
            System.out.println("6. ввести другую матрицу");
            System.out.println("0. выход");
            System.out.println("выберите операцию: ");
            
            try {
                int choice = Integer.parseInt(scanner.next());

                switch (choice) {
                    case 1:
                    case 2:
                    case 3:
                        System.out.println("введите вторую матрицу");
                        ComplexMatrix drugaya = createMatrix();

                        if (choice ==1) {
                            curr = curr.add(drugaya);
                        } else if (choice == 2) {
                            curr = curr.multiply(drugaya);
                        } else {
                            curr = curr.divide(drugaya);
                        }
                        
                        System.out.println("теперь текщущая матрица - результат");
                        break;
                    case 4:
                        curr = curr.tranpose();
                        System.out.println("теперь текщущая матрица - результат");
                        break;
                    case 5:
                        System.out.println("det = "+ curr.determinant());
                        break;
                    case 6:
                        curr = createMatrix();
                        break;
                    case 0:
                        code = false;
                        System.out.println("программа завершена");
                        break; 
                    default:
                        System.out.println("такой операции нет");
                }
            }   catch (NumberFormatException e) {
                System.out.println("ошибка: нужно ввести число, дробную часть пишите через точку");
            }
        }
    }
}
