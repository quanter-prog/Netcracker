package com.netcracker.lab.service.impl;

import com.netcracker.lab.CSVLoader;
import com.netcracker.lab.entities.Person;
import com.netcracker.lab.factory.LabFactory;
import com.netcracker.lab.service.IPersonRepositoryService;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.repository.IRepository;

import javax.jws.WebService;

@WebService(endpointInterface = "com.netcracker.lab.service.IPersonRepositoryService")
public class PersonRepositoryServiceImpl implements IPersonRepositoryService {

    IRepository<IPerson> repository;

    public PersonRepositoryServiceImpl() {
        LabFactory factory = new LabFactory();
        repository = factory.createRepository(IPerson.class);
        CSVLoader loader = new CSVLoader(repository, "src/main/resources/persons.csv");
        this.repository = loader.getRepository();
    }

    @Override
    public String getUserNameById(int id) {
        Person person = (Person) repository.get(id);
        return person.getFirstName();
    }

    @Override
    public long getCountUsersByAge(int age) {
        repository.toList();
        return repository.toList().stream().filter(x -> x.getAge() > 25).count();
    }
}
