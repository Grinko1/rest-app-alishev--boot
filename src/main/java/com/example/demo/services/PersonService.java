package com.example.demo.services;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private  PersonRepository personRepository;


    public List<Person> findAll(){
        return (List<Person>) personRepository.findAll();
    }
    public Optional<Person> findById(int id){
        return personRepository.findById(id);
    }
    public void save(Person person){
        personRepository.save(person);
    }
    public Person update(Person person){
        Optional<Person> existingPersonOptional = personRepository.findById(person.getId());
        if (existingPersonOptional.isPresent()) {
            // If the person exists, update it
            return personRepository.save(person);
        } else {
            // If the person does not exist, you may choose to throw an exception or handle it accordingly
            throw new EntityNotFoundException("Person with ID " + person.getId() + " not found");
        }
    }
    public void delete(int id){
        personRepository.deleteById(id);
    }
}
