package com.netcracker.lab.entities.comparators;

import com.netcracker.lab.entities.IPerson;

import java.util.Comparator;

public class PersonIdComparator implements Comparator<IPerson> {
    @Override
    public int compare(IPerson p1, IPerson p2) {
        return p1.getId().compareTo(p2.getId());
    }
}
