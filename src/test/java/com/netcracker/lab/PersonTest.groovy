package com.netcracker.lab

import org.joda.time.DateTime

/**
 * Класс Groovy тестов для Person.
 */
class PersonTest extends GroovyTestCase {
    /**
     * Проверка корректности возвращаемого возраста
     */
    void testGetAge() {
        Person human3 = new Person("Анька", "Жданова",
                DateTime.parse("1988-05-05T10:11:12.123"))
        println ("Возраст Аньки: " + human3.getAge() + " лет.")
    }
    /**
     * Проверка перегрузки метода toString()
     */
    void testToString() {
        Person human3 = new Person("Анька", "Жданова",
                DateTime.parse("1988-05-05T10:11:12.123"))
        println (human3.toString())
    }
    /**
     * Метод доступа к имени. Остальные методы доступа тестировать нет смысла
     * так как они сгенерированы IDE
     */
    void testGetFirstName() {
        Person human3 = new Person("Анька", "Жданова",
                DateTime.parse("1988-05-05T10:11:12.123"))
        println (human3.getFirstName())
    }
}
