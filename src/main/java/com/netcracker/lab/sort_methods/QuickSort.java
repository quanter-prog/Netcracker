package com.netcracker.lab.sort_methods;

import com.netcracker.lab.repository.PersonRepository;
import ru.vsu.lab.entities.IPerson;

import java.util.Comparator;

public class QuickSort {

    private PersonRepository repository;
    private Comparator comparator;

    public QuickSort(PersonRepository repository, Comparator comparator) {
        this.repository = repository;
        this.comparator = comparator;
        quickSort();
    }

    private void quickSort() {
        doSort(0, repository.getLength() - 1);
    }

    private void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (comparator.compare
                    (repository.getBank()[i], repository.getBank()[cur]) <= 0)) {
                i++;
            }
            while (j > cur && (comparator.compare
                    (repository.getBank()[cur], repository.getBank()[j]) <= 0)) {
                j--;
            }
            if (i < j) {
                IPerson temp = repository.getBank()[i];
                repository.getBank()[i] = repository.getBank()[j];
                repository.getBank()[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur+1, end);
    }
}

