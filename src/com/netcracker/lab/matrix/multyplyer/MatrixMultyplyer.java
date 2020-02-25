package com.netcracker.lab.matrix.multyplyer;

import com.netcracker.lab.matrix.Matrix;

/** Класс, выполняющий умножение матриц. */
public class MatrixMultyplyer {

    /**
     * Метод, умножающий матрицы
     * @param m1 первая матрица.
     * @param m2 вторая матрица.
     * @return ответ.
     */
    public static Matrix multyply(Matrix m1, Matrix m2) {
        Matrix resultMatrix = new Matrix(m1.getSize());
        int sum = 0;
        int[] row, column;

        for (int i = 0; i < m1.getSize(); i++) {
            for (int j = 0; j < m1.getSize(); j++) {
                row = m1.getRow(i);
                column = m2.getColumn(j);

                for (int k = 0; k < m1.getSize(); k++) {
                    sum += row[k] * column[k];
                }

                resultMatrix.getMatrix()[i][j] = sum;
                sum = 0;
            }
        }

        return resultMatrix;
    }
}
