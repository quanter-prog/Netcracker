package com.netcracker.lab;

import com.netcracker.lab.matrix.multyplyer.MultyThreadMatrixMultiplyer;
import com.netcracker.lab.matrix.multyplyer.MatrixMultyplyer;
import com.netcracker.lab.matrix.Matrix;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        long startTime, timeSpent;
        int matrixSize, greatestMatrixValue, threadCount;
        int mode;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размерность матриц: ");
        matrixSize = scanner.nextInt();
        System.out.print("Введите наибольшее число в матрице: ");
        greatestMatrixValue = scanner.nextInt();

        Matrix m1 = new Matrix(matrixSize);
        Matrix m2 = new Matrix(matrixSize);
        Matrix result = null;
        m1.randFilling(greatestMatrixValue);
        m2.randFilling(greatestMatrixValue);

        do {
            System.out.println("\nДанная программа выполняет однопоточное и " +
                    "многопоточное умножение матриц. Выберите необходимый вариант:\n" +
                    "1) Однопоточное умножение.\n2) Многопоточное умножение.\n" +
                    "3) Оба умножения и сравнение времени исполнения.\n" +
                    "4) Перегенерировать матрицы.\n5) Напечатать матрицы в консоль.\n" +
                    "6) Выход из программы.");
            try {
                mode = scanner.nextInt();
            } catch (InputMismatchException ex) {
                throw new InputMismatchException("Недопустимое значение!");
            }

            switch (mode) {
                case 1:
                    startTime = System.currentTimeMillis();

                    result = MatrixMultyplyer.multyply(m1, m2);

                    timeSpent = System.currentTimeMillis() - startTime;
                    System.out.println("\nПрограмма выполнялась " + timeSpent + " миллисекунд.");

                    break;
                case 2:
                    System.out.print("Введите число потоков: ");
                    threadCount = scanner.nextInt();

                    startTime = System.currentTimeMillis();

                    result = MultyThreadMatrixMultiplyer.multiply(m1, m2, threadCount);

                    timeSpent = System.currentTimeMillis() - startTime;
                    System.out.println("\nПрограмма выполнялась " + timeSpent + " миллисекунд.");

                    break;
                case 3:
                    System.out.print("Введите число потоков: ");
                    threadCount = scanner.nextInt();

                    startTime = System.currentTimeMillis();

                    result = MatrixMultyplyer.multyply(m1, m2);

                    long firstMultyplyTimeSpent = System.currentTimeMillis() - startTime;

                    startTime = System.currentTimeMillis();
                    MultyThreadMatrixMultiplyer.multiply(m1, m2, threadCount);
                    timeSpent = System.currentTimeMillis() - startTime;

                    System.out.println("\nВремя выполнения однопоточного умноения: "
                            + firstMultyplyTimeSpent + " миллисекунд.");
                    System.out.println("\nВремя выполнения многопоточного умноения: "
                            + timeSpent + " миллисекунд.");

                    break;
                case 4:
                    System.out.print("Введите размерность матриц: ");
                    matrixSize = scanner.nextInt();
                    System.out.print("Введите наибольшее число в матрице: ");
                    greatestMatrixValue = scanner.nextInt();

                    m1 = new Matrix(matrixSize);
                    m2 = new Matrix(matrixSize);
                    m1.randFilling(greatestMatrixValue);
                    m2.randFilling(greatestMatrixValue);
                    result = null;

                    break;
                case 5:
                    System.out.println("\nПервая матрица:");
                    m1.print();
                    System.out.println("\nВторая матрица:");
                    m2.print();

                    if (result != null) {
                        System.out.println("\nРезультат:");
                        result.print();
                    }

                    break;
                case 6:
                    break;
                default:
                    System.out.println("Неверно введено число!");
            }
        } while (mode != 6);
    }
}
