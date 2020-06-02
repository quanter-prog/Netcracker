package com.netcracker.lab.repository;

import com.netcracker.lab.inject.LabInject;
import com.netcracker.lab.sort_methods.ISorter;
import com.netcracker.lab.sort_methods.QuickSort;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * Данный класс предназначен для хранения экземпляров класса.
 * @param <T> тип хранимых данных.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlJavaTypeAdapter(Repository.Adapter.class)
public class Repository<T> implements IRepository<T> {

    /** Логер. */
    private static Logger log = Logger.getLogger(Repository.class.getName());

    /** Сортировщик для класса. */
    @LabInject
    @XmlElement
    @XmlJavaTypeAdapter(QuickSort.Adapter.class)
    private ISorter sorter;

    /** Пустой массив для хранения экземпляров класса. */
    @XmlElement(name = "bank")
    private T[] bank;

    /** Индекс последнего элемента. */
    private int length = 0;

    /**
     * Конструкторе класса.
     * В нём выделяется память для массива, хранящего экземпляры класса.
     */
    public Repository() {
        bank = (T[]) new Object[10];
    }

    /**
     * Конструкторе класса.
     * В нём выделяется память для произвольного массива,
     * хранящего экземпляры класса.
     * @param repositorySize размер репозитория
     */
    public Repository(int repositorySize) {
        bank = (T[]) new Object[repositorySize];
    }

    public static class Adapter extends XmlAdapter<Repository, IRepository> {
        public IRepository unmarshal(Repository r) { return r; }
        public Repository marshal(IRepository r) { return (Repository) r; }
    }

    /**
     *Метод, увеличивающий массив bank.
     */
    private void increaseCapacity() {
        T[] localBank = (T[]) new Object[bank.length + (bank.length + 2) / 2];

        System.arraycopy(bank, 0, localBank,
                0, bank.length);

        bank = localBank;
    }

    /**
     * Метод, сдвигающий все элементы массива влево или вправо на одну позицию.
     * @param index индекс элемента, относительно которого происходит сдвиг.
     * @param isShiftToRight true - сдвиг вправо, false - сдвиг влево.
     */
    private void move(int index, boolean isShiftToRight) {
        Object[] localBank = new Object[bank.length];
        if (isShiftToRight) {
            System.arraycopy(bank, index, localBank,
                    index, bank.length - (index + 1));

            System.arraycopy(localBank, index, bank,
                    index + 1, localBank.length - (index + 1));
        } else  {
            System.arraycopy(bank, index + 1, localBank,
                    index + 1, bank.length - (index + 1));

            System.arraycopy(localBank, index + 1, bank,
                    index, localBank.length - (index + 1));

            bank[bank.length - 1] = null;
        }
    }

    @Override
    public void add(T o) {
        if (length == bank.length) {
            increaseCapacity();
        }
        bank[length] = o;
        length++;

        log.info("Object has been added to Repository.");
    }

    @Override
    public void add(int index, T o) {
        if (length == bank.length) {
            increaseCapacity();
        }

        move(index, true);
        bank[index] = o;

        length++;

        log.info("Object has been added to Repository.");
    }

    @Override
    public T get(int index) {
        if (index < length) {
            log.info("Get element by index.");
            return (T) bank[index];
        } else {
            log.warning("No element found!");
            return null;
        }
    }

    @Override
    public T set(int index, T o1) {
        Object o2 = bank[index];
        bank[index] = null;
        bank[index] = o1;

        log.info("Object has been added to Repository.");
        return (T) o2;
    }

    @Override
    public T delete(int index) {
        Object o = null;
        if (index < length) {
            for (int i = 0; i < length; i++) {
                if (i == index) {
                    o = bank[i];
                    bank[i] = null;
                    break;
                }
            }
            move(index, false);
            length--;

            log.info("Object has been successfully deleted from repository.");
            return (T) o;
        }

        log.warning("Impossible to delete object.");
        return null;
    }

    private void trimToSize() {
        T[] localBank = (T[]) new Object[length];
        System.arraycopy(bank, 0, localBank, 0, length);
        bank = localBank;
    }

    @Override
    public List<T> toList() {
        this.trimToSize();
        return Arrays.asList(bank);
    }

    @Override
    public void sortBy(Comparator comparator) {
        //new QuickSort(this, comparator);
    }

    @Override
    public IRepository<T> searchBy(Predicate<T> condition) {
        IRepository<T> localIRepository = new Repository<>();
        for (int i = 0; i < length; i++) {
            if (condition.test((T) bank[i])) {
                localIRepository.add((T) bank[i]);
            }
        }
        return localIRepository;
    }

    @Override
    public final String toString() {
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            buf.append(bank[i].toString());
            buf.append("\n");
        }
        return buf.toString();
    }
}
