package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int peopleCount = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++peopleCount, "Nadya"));
        people.add(new Person(++peopleCount, "Kate"));
        people.add(new Person(++peopleCount, "Mike"));
        people.add(new Person(++peopleCount, "John"));
    }

    public List<Person> findAll() {
        return people;
    }

    public Person findById(int id) {
        return people.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Person save(String name) {
        Person person = new Person(++peopleCount, name);
        people.add(person);
        return person;
    }

    public Person update(Person person) {
        Person forUpdate = findById(person.getId());
        forUpdate.setName(person.getName());
        return forUpdate;
    }

    public void deleteById(int id) {
        people.removeIf(i -> i.getId() == id);
    }
}
