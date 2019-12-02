package com.netcracker.lab;

import com.netcracker.lab.entities.Division;
import com.netcracker.lab.entities.Person;
import com.netcracker.lab.repository.PersonRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IPersonRepository;

import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CSVLoader {

    private String path;

    private HashSet<IDivision> divisions;

    CSVLoader(String path) {
        this.path = path;
        divisions = new HashSet<>();
        }

    public IPersonRepository getRepository() {
        return read();
    }

    /**
     * Проверка вхождения значения по имени.
     * @param name название отдела.
     * @return имеется ли такой отдел?
     */
    private boolean hasDivision(String name) {
        Iterator<IDivision> iterator = divisions.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Данная функция считывает из файла информацию и записывает её в list.
     * @return list с информацией из файла
     */
    public IPersonRepository read() {
        IPersonRepository rep = new PersonRepository();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .build();

            while (csvReader.iterator().hasNext()) {
                StringBuilder buf = new StringBuilder("");
                String[] line = csvReader.peek();

                for (String s : line) {
                    buf.append(s);
                }

                line = buf.toString().split(";");

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("dd.MM.yyyy");

                if (!hasDivision(line[4])) {
                    divisions.add(new Division(line[4]));
                }

                IDivision division = null;
                Iterator<IDivision> iterator = divisions.iterator();
                while (iterator.hasNext()) {
                    division = iterator.next();
                    if (division.getName().equals(line[4])) {
                        break;
                    }
                }

                Person person = new Person(line[1],
                        Integer.parseInt(line[0]),
                        LocalDate.parse(line[3], formatter),
                        Gender.valueOf(line[2].toUpperCase()),
                        division,
                        BigDecimal.valueOf(Double.parseDouble(line[5])));
                rep.add(person);
            }

            reader.close();
            csvReader.close();
        } catch (Exception ex) {
            ex = new Exception();
        }
        return rep;
    }
}
