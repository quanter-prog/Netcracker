package com.netcracker.lab;

import com.netcracker.lab.exceptions.InjectorException;
import com.netcracker.lab.factory.LabFactory;
import com.netcracker.lab.repository.PersonRepository;

import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Класс - точка входа в программу.
 */
public class Main {

    private static final String PATH = "src/main/resources/persons.csv";

    /**
     * Точка входа в программу.
     * @param args возможные (но необязательные входные значения)
     */
    public static void main(String[] args) throws InjectorException{

        LabFactory factory = new LabFactory();
        CSVLoader loader = new CSVLoader(factory.createPersonRepository(), PATH);
        PersonRepository repository = (PersonRepository) loader.getRepository();
        //repository.sortBy(new PersonAgeComparator());
        //System.out.println(repository.toString());

        int selector = 0;
        while (selector != 5) {
            System.out.println("\nВыберите нужный вариант и введите его номер: \n\n"
                    + "1)Вывести список всех подразделений с общей заработной платой\n"
                    + "всех сотрудников для каждого отдела. \n\n"
                    + "2) Найти всех сотрудников у которых одновременно:\n"
                    + "В имени есть буква \"А\"\n"
                    + "Возраст больше 30 лет\n"
                    + "Заработная плата < 5000.\n\n"
                    + "3)Найти всех людей с двумя буквами \"А\" в имени подряд.\n\n"
                    + "4)Выдать мапу по годам рождения и количество людей\n"
                    + "этого года рождения.\n\n"
                    + "5)Выход\n\n");
            Scanner scanner = new Scanner(System.in);
            selector = scanner.nextInt();
            switch (selector) {
                case 1: {
                    System.out.println(repository.toList()
                            .stream()
                            .collect(Collectors
                                    .groupingBy(p->p.getDivision()
                                                    .getName(),
                                            Collectors
                                                    .summingDouble(p->p.getSalary()
                                                    .doubleValue()))));
                    break;
                }
                case 2: {
                    repository.toList()
                            .stream()
                            .filter(p->p.getFirstName()
                                    .toLowerCase()
                                    .contains("a"))
                            .filter(p->p.getAge() > 30)
                            .filter(p->p.getSalary()
                                    .intValue() < 5000)
                            .forEach(System.out::println);

                    System.out.println("\nCount: " +
                            repository.toList()
                            .stream()
                            .filter(p->p.getFirstName()
                                    .toLowerCase()
                                    .contains("a"))
                            .filter(p->p.getAge() > 30)
                            .filter(p->p.getSalary()
                                    .intValue() < 5000)
                            .count());
                    break;
                }
                case 3: {
                    repository.toList()
                            .stream()
                            .filter(p->p.getFirstName()
                                    .toLowerCase()
                                    .contains("aa"))
                            .forEach(System.out::println);

                    System.out.println("Count: " +
                            repository.toList()
                            .stream()
                            .filter(p->p.getFirstName()
                                    .toLowerCase()
                                    .contains("aa"))
                            .count());
                    break;
                }
                case 4: {
                    System.out.println(repository
                            .toList()
                            .stream()
                            .collect(Collectors
                                    .groupingBy(p->p.getBirthdate()
                                            .getYear(),
                                            Collectors.counting())));
                    break;
                }
                case 5: {
                    break;
                }
                default: {
                    System.out.println("Invalid option!");
                    break;
                }
            }
        }
    }
}
