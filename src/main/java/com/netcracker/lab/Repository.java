package com.netcracker.lab;

/**
 * Данный класс предназначен для хранения экземпляров класса Person.
 */
public class Repository {
    /**
     * Пустой массив для хранения экземпляров класса Person.
     */
    private Person[] personBank;

    /**
     * Конструкторе класса.
     * В нём выделяется память для массива, хранящего экземпляры класса Person.
     */
    public Repository() {
        personBank = new Person[0];
    }

    /**
     * Данный метод предназначен для получения экземпляра класса Person
     * по индексу из массива personBank.
     * Если такого элемента не существует возвращается null.
     * @param index значение индекса массива (нумерация начинается с нуля!).
     * @return соответствующий экземпляр класса Person или null.
     */
    public final Person getPerson(final int index) {
        if (index + 1 <= personBank.length) {
            return personBank[index];
        } else {
            return null;
        }
    }

    /**
     * Данный метод предназначен для получения экземпляра класса Person
     * по id из массива personBank.
     * Если такого элемента не существует возвращается null.
     * @param id значение id экземпляра класса Person.
     * @return соответствующий экземпляр класса Person или null.
     */
    public final Person getPersonById(final int id) {
        for (Person i : personBank) {
            if (i.getPersonId() == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * Данный метод предназначен для добавления экземпляра класса Person
     * в массив personBank.
     * @param person экземпляра класса Person который необходимо добавить.
     */
    public final void add(final Person person) {
        Person[] localPersonBank = new Person[personBank.length + 1];

        System.arraycopy(personBank, 0, localPersonBank,
                0, personBank.length);

        localPersonBank[localPersonBank.length - 1] = person;
        personBank = new Person[localPersonBank.length];

        System.arraycopy(localPersonBank, 0, personBank,
                0, localPersonBank.length);
    }

    /**
     * Данный метод предназначен для удаления экземпляра класса Person
     * из массива personBank по индексу.
     * Если экземпляра класса Person с таким индексом нет, ничего не меняется.
     * @param index значения индекса массива (нумерация начинается с нуля!).
     */
    public final void delete(final int index) {
        Repository localRepository = new Repository();

        for (int i = 0; i < personBank.length; i++) {
            if (i != index) {
                localRepository.add(personBank[i]);
            }
        }

        Person[] localPersonBank =
                new Person[localRepository.personBank.length];

        System.arraycopy(localRepository.personBank, 0,
                localPersonBank, 0, localRepository.personBank.length);
        personBank = new Person[localPersonBank.length];
        System.arraycopy(localPersonBank, 0, personBank, 0,
                localPersonBank.length);
    }

    /**
     * Данный метод предназначен для удаления соотвтествующего экземпляра
     * класса Person из массива personBank.
     * Если соответствующего экземпляра класса Person в массиве personBank
     * нет, ничего не меняется.
     * @param person соответствующий экземпляр класса Person.
     */
    public final void delete(final Person person) {
        Repository localRepository = new Repository();

        for (Person i : personBank) {
            if (person.getPersonId() != i.getPersonId()) {
                localRepository.add(i);
            }
        }

        Person[] localPersonBank =
                new Person[localRepository.personBank.length];

        System.arraycopy(localRepository.personBank, 0, localPersonBank,
                0, localRepository.personBank.length);

        personBank = new Person[localPersonBank.length];

        System.arraycopy(localPersonBank, 0, personBank,
                0, localPersonBank.length);
    }

    /**
     * Перегрузка метода toString.
     * @return построчный список элементов массива personBank, каждый из
     * которых вызывает свою перегрузку toString.
     * (см. докумнтацию Person.toString())
     */
    @Override
    public final String toString() {
        StringBuilder buf = new StringBuilder("");
        for (Person i : personBank) {
            buf.append(i.toString());
            buf.append("\n");
        }
        return buf.toString();
    }
}
