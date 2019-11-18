package com.netcracker.lab.entities.comparators;

import com.netcracker.lab.entities.IPerson;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<IPerson> {
    @Override
    public int compare(IPerson p1, IPerson p2) {
        return p1.getFirstName().compareTo(p2.getFirstName());
    }
}
