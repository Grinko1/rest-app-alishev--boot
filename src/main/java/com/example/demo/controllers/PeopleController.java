package com.example.demo.controllers;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import com.example.demo.services.PersonService;
import com.example.demo.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/people")

public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    private final PersonService personService;

    public PeopleController(PersonDAO personDAO, PersonValidator personValidator, PersonService personService) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPeople(){
//        return personDAO.findAll();
        return personService.findAll();
    }
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") int id) throws SQLException {
//        return personDAO.findById(id);
        return  personService.findById(id).orElse(null);
    }

@PostMapping
public void createPerson(@Valid @RequestBody Person person, BindingResult bindingResult) throws Exception {
    System.out.println(person);
    personValidator.validate(person, bindingResult);
    if (bindingResult.hasErrors()){
        throw new Exception("Invalid data for creating new person");
    }else {
//        personDAO.save(person);
        personService.save(person);
    }
}
    @PatchMapping("/{id}")
    public Person update(@PathVariable("id") int id, @Valid @RequestBody Person person, BindingResult bindingResult) throws Exception {
        person.setId(id);
        System.out.println(person);
//        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()){
            throw new Exception("Invalid data for updating new person");
        }
//        return personDAO.update(new Person(id, person.getName(), person.getAge(), person.getEmail(), person.getAddress()));
        return personService.update(person);
//        return null;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
//        personDAO.deleteById(id);
        personService.delete(id);
        return "Person was deleted";
    }
}
