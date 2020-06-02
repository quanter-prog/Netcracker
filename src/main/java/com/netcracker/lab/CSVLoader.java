package com.netcracker.lab;

import com.netcracker.lab.entities.Division;
import com.netcracker.lab.entities.Person;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;
import ru.vsu.lab.repository.IRepository;

import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Класс, реализующий считывание из файла информации.
 * А также записывающий её в репозиторий.
 */
public class CSVLoader {

    private static Logger log = Logger.getLogger(CSVLoader.class.getName());

    /** Директория файла. */
    private String path;

    /** Репозиторий для записи информации из файла. */
    private IRepository<IPerson> repository;

    /**
     * Метод доступа ко всем отделам из файла.
     * @return отделы из файла.
     */
    public HashSet<IDivision> getDivisions() {
        return divisions;
    }

    /**
     * HashSet, хранящий информацию об отделах.
     * Необходим для оптимизации.
     */
    private HashSet<IDivision> divisions;

    /**
     * Конструктор класса.
     * @param repository репозиторий для записи информации из файла.
     * @param path директория файла.
     */
    public CSVLoader(IRepository<IPerson> repository, String path) {
        this.repository = repository;
        divisions = new HashSet<>();
        this.path = path;
        load();
    }

    /**
     * Метод доступа к репозиторию.
     * @return заполненный репозиторий.
     */
    public IRepository<IPerson> getRepository() {
        return repository;
    }

    /**
     * Проверка вхождения значения по имени.
     * @param name название отдела.
     * @return имеется ли такой отдел?
     */
    private boolean hasDivision(String name) {
        for (IDivision division : divisions) {
            if (division.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Данная функция считывает из файла информацию.
     * А также записывает её в репозиторий.
     */
    private void load() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .build();

            while (csvReader.iterator().hasNext()) {
                StringBuilder buf = new StringBuilder("");
                if (csvReader.peek() == null) {
                    break;
                }
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
                for (IDivision iDivision : divisions) {
                    if (iDivision.getName().equals(line[4])) {
                        division = iDivision;
                        break;
                    }
                }
                Person person = new Person(line[1],
                        Integer.parseInt(line[0]),
                        LocalDate.parse(line[3], formatter),
                        Gender.valueOf(line[2].toUpperCase()),
                        division,
                        BigDecimal.valueOf(Double.parseDouble(line[5])));
                repository.add(person);
            }

            reader.close();
            csvReader.close();
        } catch (Exception ex) {
            log.warning("Impossible to read CSV file!");
            ex.printStackTrace();
        }

        log.info("CSV file was written in person repository.");
    }
}
