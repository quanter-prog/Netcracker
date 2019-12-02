package com.netcracker.lab.entities.comparators;

import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

public class PersonSalaryComparator implements Comparator<IPerson> {
    @Override
    public int compare(IPerson p1, IPerson p2) {
        return p1.getSalary().compareTo(p2.getSalary());
    }
}
