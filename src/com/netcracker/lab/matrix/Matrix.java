package com.netcracker.lab.matrix;

import java.util.Random;

/**
 * Данный класс описывает квадратную матрицу и
 * предоставляет некоторые методы для работы с ней.
 */
public class Matrix {

    /** Размерность матрицы. */
    private int size;
    /** Матрица. */
    private int[][] matrix;

    /**
     * Конструктор, инициализирующий поля класса.
     * @param size размерность матрицы.
     */
    public Matrix(int size) {
        this.size = size;
        matrix = new int[size][size];
    }

    /**
     * Метод, заполняющий матрицу случайными положительными занчениями.
     * @param maxRandValue макисмально допустимое случайное значение в матрице.
     */
    public void randFilling(int maxRandValue) {
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = rand.nextInt(maxRandValue);
            }
        }
    }

    /** Метод, выводящий значения матрицы в консоль. */
    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Метод, возвращающий строку матрицы.
     * @param columnIndex индекс строки.
     * @return строка.
     */
    public int[] getColumn(int columnIndex) {
        int[] row = new int[size];

        for (int i = 0; i < size; i++) {
            row[i] = matrix[i][columnIndex];
        }

        return row;
    }

    /**
     * Метод, возвращающий столбец матрицы.
     * @param rowIndex индекс столбца.
     * @return столбец.
     */
    public int[] getRow(int rowIndex) {
        return matrix[rowIndex];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return this.size;
    }
}
