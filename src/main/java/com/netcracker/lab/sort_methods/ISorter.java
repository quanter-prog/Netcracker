package com.netcracker.lab.sort_methods;

import com.netcracker.lab.repository.PersonRepository;

import java.util.Comparator;

/**
 * Интерфейс, описывающий сортировщик, для IPersonRepository.
 */
public interface ISorter {

    /**
     * Метод доступа к полю.
     * @param repository устанавливает репозиторий.
     */
    void setRepository(PersonRepository repository);

    /**
     * Метод доступа к полю.
     * @param comparator устанавливает компаратор.
     */
    void setComparator(Comparator comparator);

    /**
     * Метод сорировки.
     */
    void sort();
}
