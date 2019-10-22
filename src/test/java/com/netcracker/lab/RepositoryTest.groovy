package com.netcracker.lab

import org.joda.time.DateTime
/**
 * Класс Groovy тестов для Repository.
 */
class RepositoryTest extends GroovyTestCase {
    /**
     * Тест, который возвращает экзампляр класса Person по его индексу в репозитории
     */
    void testGetPerson() {
        Repository rep = new Repository()
        Person human1 = new Person("Илья", "Марков",
                DateTime.now().minusYears(27).minusMonths(3))
        Person human2 = new Person("Петр", "Краснов",
                DateTime.now().minusYears(20).minusMonths(2))
        Person human3 = new Person("Анька", "Жданова",
                DateTime.parse("2018-05-05T10:11:12.123"))
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        println (rep.getPerson(2).toString())
    }
/**
 * Тест, который возвращает экзампляр класса Person по его id
 */
    void testGetPersonById() {
        Repository rep = new Repository()
        Person human1 = new Person("Илья", "Марков",
                DateTime.now().minusYears(27).minusMonths(3))
        Person human2 = new Person("Петр", "Краснов",
                DateTime.now().minusYears(20).minusMonths(2))
        Person human3 = new Person("Анька", "Жданова",
                DateTime.parse("2018-05-05T10:11:12.123"))
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        println (rep.getPersonById(2).toString())
    }
/**
 * Проверка фукции удаления как по индексу, так и по конкретному экземпляру класса Person
 */
    void testDelete() {
        Repository rep = new Repository()
        Person human1 = new Person("Илья", "Марков",
                DateTime.now().minusYears(27).minusMonths(3))
        Person human2 = new Person("Петр", "Краснов",
                DateTime.now().minusYears(20).minusMonths(2))
        Person human3 = new Person("Анька", "Жданова",
                DateTime.parse("2018-05-05T10:11:12.123"))
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        println ("Репозиторий до удаления: \n" + rep.toString())

        rep.delete(2)
        //rep.delete(human3)
        println ("Репозиторий после удаления Аньки: \n" + rep.toString())

        rep.delete(1)
        //rep.delete(human2)
        println ("Репозиторий после удаления Пети: \n" + rep.toString())

        //rep.delete(0)
        rep.delete(human1)
        println ("Репозиторий после удаления Ильи: \n" + rep.toString())
    }
/**
 * Тест перегрузки метода toString()
 */
    void testToString() {
        Repository rep = new Repository()
        Person human1 = new Person("Илья", "Марков",
                DateTime.now().minusYears(27).minusMonths(3))
        Person human2 = new Person("Петр", "Краснов",
                DateTime.now().minusYears(20).minusMonths(2))
        Person human3 = new Person("Анька", "Жданова",
                DateTime.parse("2018-05-05T10:11:12.123"))
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        println (rep.toString())
    }
}
