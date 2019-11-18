package com.netcracker.lab.repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.netcracker.lab.entities.IPerson;

public interface IRepository {

    void add(IPerson person);

    Optional<IPerson> get(int index);

    void delete(int index);

    void set(int index, IPerson person);

    List<IPerson> toList();

    void sortBy(Comparator<IPerson> comparator);

    IRepository searchBy(Predicate<IPerson> condition);

}
