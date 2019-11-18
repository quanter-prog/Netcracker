package com.netcracker.lab.repository;

import com.netcracker.lab.entities.IPerson;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.Comparator;
import java.util.Optional;
import java.util.List;

/**
 * Данный класс предназначен для хранения экземпляров класса Person.
 */
public class Repository implements IRepository {
    /**
     * Пустой массив для хранения экземпляров класса Person.
     */
    private IPerson[] personBank;

    /**
     * Индекс последнего элемента.
     */
    private int lastIndex = 0;

    /**
     * Конструкторе класса.
     * В нём выделяется память для массива, хранящего экземпляры класса Person.
     */
    public Repository() {
        personBank = new IPerson[10];

        for (int i = 0; i < personBank.length; i++) {
            personBank[i] = null;
        }
    }

    /**
     * Конструкторе класса.
     * В нём выделяется память для произвольного массива,
     * хранящего экземпляры класса Person.
     * @param repositorySize размер репозитория
     */
    public Repository(int repositorySize) {
        personBank = new IPerson[repositorySize];
    }

    /**
     * Данный метод предназначен для получения экземпляра класса Person
     * по индексу из массива personBank.
     * Если такого элемента не существует возвращается null.
     * @param index значение индекса массива (нумерация начинается с нуля!).
     * @return экземпляр класса Optional<Person> или Optional.empty().
     */
    @Override
    public Optional<IPerson> get(int index) {
        if (index < lastIndex) {
            return Optional.of(personBank[index]);
        } else {
            return Optional.empty();
        }
    }

    /**
     *Метод, увеличивающий массив personBank.
     */
    private void increaseCapacity() {
        IPerson[] localPersonBank =
                new IPerson[personBank.length + (personBank.length + 2) / 2];

        for (int i = personBank.length; i < localPersonBank.length; i++) {
            localPersonBank[i] = null;
        }

        System.arraycopy(personBank, 0, localPersonBank,
                0, personBank.length);

        personBank = localPersonBank;
    }

    /**
     * Данный метод предназначен для получения экземпляра класса Person
     * по id из массива personBank.
     * Если такого элемента не существует возвращается null.
     * @param id значение id экземпляра класса Person.
     * @return экземпляр класса Optional<Person> или Optional.empty().
     */
    public Optional<IPerson> getPersonById(int id) {
        for (int i = 0; i < lastIndex; i++) {
            if (i == id) {
                return Optional.of(personBank[i]);
            }
        }
        return Optional.empty();
    }

    /**
     * Данный метод предназначен для добавления экземпляра класса Person
     * в массив personBank.
     * @param person экземпляра класса Person который необходимо добавить.
     */
    @Override
    public void add(IPerson person) {
        if (lastIndex == personBank.length) {
            increaseCapacity();
        }

        personBank[lastIndex] = person;
        lastIndex++;

    }

    /**
     * Данный метод предназначен для удаления экземпляра класса Person
     * из массива personBank по индексу.
     * Если экземпляра класса Person с таким индексом нет, ничего не меняется.
     * @param index значения индекса массива (нумерация начинается с нуля!).
     */
    public void delete(int index) {
        if (index < lastIndex) {
            for (int i = 0; i < lastIndex; i++) {
                if (i == index) {
                    personBank[i] = null;
                }
            }

            move(index, false);
            lastIndex--;
        }
    }

    /**
     * Метод, сдвигающий все элементы массива влево или вправо на одну позицию.
     * @param index индекс элемента, относительно которого происходит сдвиг.
     * @param isShiftToRight true - сдвиг вправо, false - сдвиг влево.
     */
    private void move(int index, boolean isShiftToRight) {
        IPerson[] localBank = new IPerson[personBank.length];
        if (isShiftToRight) {
            System.arraycopy(personBank, index, localBank,
                    index, personBank.length - (index + 1));

            System.arraycopy(localBank, index, personBank,
                    index + 1, localBank.length - (index + 1));
        } else  {
            System.arraycopy(personBank, index + 1, localBank,
                    index + 1, personBank.length - (index + 1));

            System.arraycopy(localBank, index + 1, personBank,
                    index, localBank.length - (index + 1));

            personBank[personBank.length - 1] = null;
        }
    }

    @Override
    public void set(int index, IPerson person) {
        if (lastIndex == personBank.length) {
            increaseCapacity();
        }

        move(index, true);
        personBank[index] = person;

        lastIndex++;
    }

    @Override
    public List<IPerson> toList() {
        return Arrays.asList(personBank);
    }

    /**
     * Данная функция меняет местами людей в репозитории.
     * Предназначена для сортировки
     * @param index1 индекс первого элемента
     * @param index2 индекс второго элемента
     */
    private void swap(int index1, int index2) {
        if (index1 < lastIndex && index2 < lastIndex) {
            IPerson bufIPerson = personBank[index1];
            personBank[index1] = personBank[index2];
            personBank[index2] = bufIPerson;
        }
    }

    @Override
    public void sortBy(Comparator<IPerson> comparator) {
        int swapCount;
        do {
            swapCount = 0;
            for (int i = 0; i < lastIndex; i++) {
                for (int j = 0; j < lastIndex; j++) {
                    if (comparator.compare(personBank[i], personBank[j]) < 0) {
                        swap(i, j);
                        swapCount++;
                    }
                }
            }

        } while (swapCount == 0);
    }

    @Override
    public IRepository searchBy(Predicate<IPerson> condition) {
        IRepository localIRepository = new Repository();
        for (int i = 0; i < lastIndex; i++) {
            if (condition.test(personBank[i])) {
                localIRepository.add(personBank[i]);
            }
        }
        return localIRepository;
    }

    /**
     * Перегрузка метода toString.
     * @return построчный список элементов массива personBank, каждый из
     * которых вызывает свою перегрузку toString.
     * (см. докумнтацию Person.toString())
     */
    @Override
    public final String toString() {
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < lastIndex; i++) {
            buf.append(personBank[i].toString());
            buf.append("\n");
        }
        return buf.toString();
    }
}
