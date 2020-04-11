package com.netcracker.lab.matrix.multyplyer;

import com.netcracker.lab.matrix.Matrix;

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
