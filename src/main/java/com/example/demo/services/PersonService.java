package com.example.demo.services;

import com.example.demo.model.Mood;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Optional<Person> findByEmail(String email){
        return personRepository.findByEmail(email);
    }
    public void save(Person person){
        person.setCreatedAt(new Date());
        if(person.getMood() == null){
            person.setMood(Mood.CALM);
        }
        personRepository.save(person);
    }
    public void update(Person person){
        Optional<Person> existingPersonOptional = personRepository.findById(person.getId());
        System.out.println(person);
        if (existingPersonOptional.isPresent()) {
             personRepository.save(person);
        } else {
            throw new EntityNotFoundException("Person with ID " + person.getId() + " not found");
        }
    }
    public void delete(int id){
        personRepository.deleteById(id);
    }
    public void test(){
        System.out.println("Testing...");
    }
}
