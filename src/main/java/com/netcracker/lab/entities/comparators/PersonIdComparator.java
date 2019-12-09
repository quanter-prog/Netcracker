package com.netcracker.lab.entities.comparators;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

/**
 * Компаратор для сравнения людей по id.
 */
public class PersonIdComparator implements Comparator<IPerson> {
    @Override
    public int compare(IPerson p1, IPerson p2) {
        return p1.getId().compareTo(p2.getId());
    }
}
