package com.netcracker.lab.matrix.multyplyer;

import com.netcracker.lab.matrix.Matrix;

// Источник: https://ru.stackoverflow.com/questions/473733/%D0%9A%D0%B0%D0%BA-%D0%BF%D1%80%D0%B0%D0%B2%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE-%D1%83%D0%BC%D0%BD%D0%BE%D0%B6%D0%B8%D1%82%D1%8C-%D0%BC%D0%B0%D1%82%D1%80%D0%B8%D1%86%D1%83-%D0%BD%D0%B0-%D0%B4%D1%80%D1%83%D0%B3%D1%83%D1%8E-%D0%BC%D0%B0%D1%82%D1%80%D0%B8%D1%86%D1%83-%D0%B2-%D0%BD%D0%B5%D1%81%D0%BA%D0%BE%D0%BB%D1%8C%D0%BA%D0%B8%D1%85-%D0%BF%D0%BE%D1%82%D0%BE%D0%BA%D0%B0%D1%85

/** Класс, выполняющий многопоочное умножение матриц. */
public class MultyThreadMatrixMultiplyer {

    /** Многопоточное умножение матриц.
     *
     * @param m1  Первая (левая) матрица.
     * @param m2 Вторая (правая) матрица.
     * @param threadCount Число потоков.
     * @return Результирующая матрица.
     */
    public static Matrix multiply(final Matrix m1, final Matrix m2, int threadCount)
    {
        assert threadCount > 0;

        final int rowCount = m1.getSize();         // Число строк результирующей матрицы.
        final int colCount = m1.getSize();         // Число столбцов результирующей матрицы.
        Matrix resultMatrix = new Matrix(m1.getSize());  // Результирующая матрица.

        final int cellsForThread = (rowCount * colCount) / threadCount;  // Число вычисляемых ячеек на поток.
        int firstIndex = 0;  // Индекс первой вычисляемой ячейки.
        final MultiplierThread[] multiplierThreads = new MultiplierThread[threadCount];  // Массив потоков.

        // Создание и запуск потоков.
        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            int lastIndex = firstIndex + cellsForThread;  // Индекс последней вычисляемой ячейки.
            if (threadIndex == 0) {
                /* Один из потоков должен будет вычислить не только свой блок ячеек,
                   но и остаток, если число ячеек не делится нацело на число потоков. */
                lastIndex = rowCount * colCount;
            }

            multiplierThreads[threadIndex] =
                    new MultiplierThread(m1.getMatrix(), m2.getMatrix(),
                            resultMatrix.getMatrix(), firstIndex, lastIndex);

            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;
        }

        // Ожидание завершения потоков.
        try {
            for (final MultiplierThread multiplierThread : multiplierThreads)
                multiplierThread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultMatrix;
    }

}
