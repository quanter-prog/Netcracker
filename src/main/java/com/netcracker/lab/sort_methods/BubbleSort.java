package com.netcracker.lab.sort_methods;

import com.netcracker.lab.repository.PersonRepository;
import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * Класс, реализующий сортировку пузырьком.
 * Сортировка возможна только для IPersonRepository.
 */
public class BubbleSort implements ISorter {

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
    public BubbleSort(PersonRepository repository,
                      Comparator comparator) {
        this.repository = repository;
        this.comparator = comparator;
    }

    /**
     * Конструктор класса по умолчанию.
     */
    public BubbleSort() {
    }

    /**
     * Метод, меняющий местами два элемента массива.
     * @param first первый элемент.
     * @param second второй элемент.
     */
    private void toSwap(int first, int second) {
        IPerson person = repository.getBank()[first];
        repository.getBank()[first]
                = repository.getBank()[second];
        repository.getBank()[second] = person;
    }

    @Override
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort() {
        for (int out = repository.getLength() - 1; out >= 1; out--) {
            for (int in = 0; in < out; in++) {
                if (comparator.compare(repository.getBank()[in],
                        repository.getBank()[in + 1]) > 0) {
                    toSwap(in, in + 1);
                }
            }
        }
    }
}
