package com.netcracker.lab.repository;

import com.netcracker.lab.sort_methods.QuickSort;

import java.util.function.Predicate;
import java.util.Comparator;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

/**
 * Данный класс предназначен для хранения экземпляров класса.
 */
public class Repository implements ru.vsu.lab.repository.IRepository {
    /**
     * Пустой массив для хранения экземпляров класса.
     */
    private Object[] bank;

    /**
     * Индекс последнего элемента.
     */
    private int length = 0;

    /**
     * Конструкторе класса.
     * В нём выделяется память для массива, хранящего экземпляры класса.
     */
    public Repository() {
        bank = new Object[10];
    }

    /**
     * Конструкторе класса.
     * В нём выделяется память для произвольного массива,
     * хранящего экземпляры класса.
     * @param repositorySize размер репозитория
     */
    public Repository(int repositorySize) {
        bank = new Object[repositorySize];
    }

    @Override
    public void add(Object o) {
        if (length == bank.length) {
            increaseCapacity();
        }
        bank[length] = o;
        length++;
    }

    /**
     * Данный метод предназначен для добавления экземпляра класса.
     * в массив bank.
     * @param o экземпляра класса который необходимо добавить.
     */
    @Override
    public void add(int index, Object o) {
        if (length == bank.length) {
            increaseCapacity();
        }

        move(index, true);
        bank[index] = o;

        length++;
    }

    /**
     * Данный метод предназначен для получения экземпляра класса
     * по индексу из массива bank.
     * Если такого элемента не существует возвращается null.
     * @param index значение индекса массива (нумерация начинается с нуля!).
     * @return экземпляр класса Optional<Object> или Optional.empty().
     */
    @Override
    public Optional<Object> get(int index) {
        if (index < length) {
            return Optional.of(bank[index]);
        } else {
            return Optional.empty();
        }
    }

    /**
     *Метод, увеличивающий массив bank.
     */
    private void increaseCapacity() {
        Object[] localBank =
                new Object[bank.length + (bank.length + 2) / 2];

        System.arraycopy(bank, 0, localBank,
                0, bank.length);

        bank = localBank;
    }

    /**
     * Метод, сдвигающий все элементы массива влево или вправо на одну позицию.
     * @param index индекс элемента, относительно которого происходит сдвиг.
     * @param isShiftToRight true - сдвиг вправо, false - сдвиг влево.
     */
    private void move(int index, boolean isShiftToRight) {
        Object[] localBank = new Object[bank.length];
        if (isShiftToRight) {
            System.arraycopy(bank, index, localBank,
                    index, bank.length - (index + 1));

            System.arraycopy(localBank, index, bank,
                    index + 1, localBank.length - (index + 1));
        } else  {
            System.arraycopy(bank, index + 1, localBank,
                    index + 1, bank.length - (index + 1));

            System.arraycopy(localBank, index + 1, bank,
                    index, localBank.length - (index + 1));

            bank[bank.length - 1] = null;
        }
    }

    @Override
    public Object set(int index, Object o1) {
        Object o2 = bank[index];
        bank[index] = o1;
        return o2;
    }

    /**
     * Данный метод предназначен для удаления экземпляра класса.
     * из массива bank по индексу.
     * Если экземпляра класса с таким индексом нет, ничего не меняется.
     * @param index значения индекса массива (нумерация начинается с нуля!).
     */
    public Optional<Object> delete(int index) {
        Object o = null;
        if (index < length) {
            for (int i = 0; i < length; i++) {
                if (i == index) {
                    o = bank[i];
                    bank[i] = null;
                    break;
                }
            }
            move(index, false);
            length--;
            return Optional.of(o);
        }
        return Optional.empty();
    }

    @Override
    public List<Object> toList() {
        return Arrays.asList(bank);
    }

    @Override
    public void sortBy(Comparator comparator) {
        //new QuickSort(this, comparator);
    }

    @Override
    public ru.vsu.lab.repository.IRepository searchBy(Predicate condition) {
        ru.vsu.lab.repository.IRepository localIRepository = new Repository();
        for (int i = 0; i < length; i++) {
            if (condition.test(bank[i])) {
                localIRepository.add(bank[i]);
            }
        }
        return localIRepository;
    }

    /**
     * Перегрузка метода toString.
     * @return построчный список элементов массива bank, каждый из
     * которых вызывает свою перегрузку toString.
     * (см. докумнтацию Object.toString())
     */
    @Override
    public final String toString() {
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            buf.append(bank[i].toString());
            buf.append("\n");
        }
        return buf.toString();
    }

    public Object[] getBank() {
        return bank;
    }

    public int getLength() {
        return length;
    }
}
