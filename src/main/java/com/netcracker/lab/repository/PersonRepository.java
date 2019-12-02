package com.netcracker.lab.repository;

import com.netcracker.lab.sort_methods.QuickSort;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IPersonRepository;
import ru.vsu.lab.repository.IRepository;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class PersonRepository implements IPersonRepository {

    /**
     * Пустой массив для хранения экземпляров класса.
     */
    private IPerson[] bank;

    /**
     * Индекс последнего элемента.
     */
    private int length = 0;

    /**
     * Конструкторе класса.
     * В нём выделяется память для массива, хранящего экземпляры класса.
     */
    public PersonRepository() {
        bank = new IPerson[10];
    }

    /**
     * Конструкторе класса.
     * В нём выделяется память для произвольного массива,
     * хранящего экземпляры класса.
     * @param repositorySize размер репозитория
     */
    public PersonRepository(int repositorySize) {
        bank = new IPerson[repositorySize];
    }

    @Override
    public void add(IPerson person) {
        if (length == bank.length) {
            increaseCapacity();
        }
        bank[length] = person;
        length++;
    }

    @Override
    public void add(int index, IPerson person) {
        if (length == bank.length) {
            increaseCapacity();
        }

        move(index, true);
        bank[index] = person;

        length++;
    }

    @Override
    public IPerson set(int index, IPerson p1) {
        IPerson p2 = bank[index];
        bank[index] = p1;
        return p2;
    }

    @Override
    public IPerson get(int index) {
        if (index < length) {
            return bank[index];
        } else {
            return null;
        }
    }

    @Override
    public IPerson delete(int index) {
        IPerson p = null;
        if (index < length) {
            for (int i = 0; i < length; i++) {
                if (i == index) {
                    p = bank[i];
                    bank[i] = null;
                    break;
                }
            }
            move(index, false);
            length--;
            return p;
        }
        return null;
    }

    @Override
    public void sortBy(Comparator<IPerson> comparator) {
        new QuickSort(this, comparator);
    }

    @Override
    public IPersonRepository searchBy(Predicate<IPerson> condition) {
        IPersonRepository localIRepository = new PersonRepository();
        for (int i = 0; i < length; i++) {
            if (condition.test(bank[i])) {
                localIRepository.add(bank[i]);
            }
        }
        return localIRepository;
    }

    @Override
    public List<IPerson> toList() {
        return Arrays.asList(bank);
    }

    /**
     * @return возвращает индекс последнего элемента + 1.
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @return внутренний массив нашего репозитория.
     */
    public IPerson[] getBank() {
        return this.bank;
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

    /**
     *Метод, увеличивающий массив bank.
     */
    private void increaseCapacity() {
        IPerson[] localBank =
                new IPerson[bank.length + (bank.length + 2) / 2];


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
        IPerson[] localBank = new IPerson[bank.length];
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

    /**
     * Данный метод предназначен для получения экземпляра класса.
     * по id из массива bank.
     * Если такого элемента не существует возвращается null.
     * @param id значение id экземпляра класса.
     * @return экземпляр класса Optional<Object> или Optional.empty().
     */
    public Optional<Object> getPersonById(int id) {
        for (int i = 0; i < length; i++) {
            if (i == id) {
                return Optional.of(bank[i]);
            }
        }
        return Optional.empty();
    }
}
