package com.netcracker.lab.sort_methods;

import com.netcracker.lab.repository.PersonRepository;
import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * Класс, реализующий быструю сортировку.
 * Сортировка возможна только для IPersonRepository.
 */
public class QuickSort implements ISorter {

    /**
     * Репозиторий, для работы сортировщика.
     */
    private PersonRepository repository;

    /**
     * Компаратор для сортировки (критерий).
     */
    private Comparator comparator;

    /**
     * Конструктор класса.
     * @param repository репозиторий, который необходимо отсортировать.
     * @param comparator компаратор для сортировки.
     */
    public QuickSort(PersonRepository repository, Comparator comparator) {
        this.repository = repository;
        this.comparator = comparator;
    }

    /**
     * Конструктор класса по умолчанию.
     */
    public QuickSort() {
    }

    /**
     * Метод, реализующий алгоритм сортировки.
     * @param start позиция в массиве, с которой начинается сортировка.
     * @param end позиция в массиве, на которой заканчивается сортировка.
     */
    private void doSort(int start, int end) {
        if (start >= end) {
            return;
        }
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (comparator.compare
                    (repository.getBank()[i],
                     repository.getBank()[cur]) <= 0)) {
                i++;
            }
            while (j > cur && (comparator.compare
                    (repository.getBank()[cur],
                     repository.getBank()[j]) <= 0)) {
                j--;
            }
            if (i < j) {
                IPerson temp = repository.getBank()[i];
                repository.getBank()[i] = repository.getBank()[j];
                repository.getBank()[j] = temp;
                if (i == cur) {
                    cur = j;
                }
                else if (j == cur) {
                    cur = i;
                }
            }
        }
        doSort(start, cur);
        doSort(cur + 1, end);
    }

    @Override
    public void sort() {
        doSort(0, repository.getLength() - 1);
    }

    @Override
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }
}

