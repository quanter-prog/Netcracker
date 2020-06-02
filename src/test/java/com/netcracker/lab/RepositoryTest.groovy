package com.netcracker.lab

import com.netcracker.lab.entities.Division
import com.netcracker.lab.entities.Person
import com.netcracker.lab.entities.comparators.PersonAgeComparator
import com.netcracker.lab.factory.LabFactory
import com.netcracker.lab.repository.PersonRepository
import ru.vsu.lab.entities.IPerson
import ru.vsu.lab.entities.enums.Gender

import java.util.function.Predicate

/**
 * Класс Groovy тестов для Repository.
 */
class RepositoryTest extends GroovyTestCase {
    PersonRepository rep = (PersonRepository) new LabFactory().createPersonRepository()
    Division marketingDivision = new Division("Marketing")
    Person human1 = new Person("Илья", "Марков",
            "1998-11-18", Gender.MALE, (BigDecimal)100000,
            marketingDivision)
    Person human2 = new Person("Петр", "Краснов",
            "1990-01-07", Gender.MALE, (BigDecimal)150000,
            marketingDivision)
    Person human3 = new Person("Анька", "Жданова",
            "1999-08-22", Gender.FEMALE, (BigDecimal)200000,
            marketingDivision)

    /**
     * Тест, который возвращает экзампляр класса Person по его индексу в репозитории
     */
    void testGetPerson() {
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        assertEquals(rep.get(1).age, 29)
    }

/**
 * Проверка фукции удаления как по индексу, так и по конкретному экземпляру класса Person
 */
    void testDelete() {
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        rep.delete(2)
        assertEquals(rep.getLength(), 2)
    }

    void testSortBy() {
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)
        rep.sortBy(new PersonAgeComparator())
        assertEquals(rep.get(0).age, 20)
    }

    void testSearch() {
        rep.add(human1)
        rep.add(human2)
        rep.add(human3)

        Predicate<IPerson> isSalaryHigh = new Predicate<IPerson>() {
            @Override
            boolean test(IPerson iPerson) {
                return iPerson.getSalary() > 130000
            }
        }
        assertEquals(rep.searchBy(isSalaryHigh).getLength(), 2)
    }
}
