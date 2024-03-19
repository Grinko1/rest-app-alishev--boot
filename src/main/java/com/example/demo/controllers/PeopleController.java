package com.example.demo.controllers;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import com.example.demo.payload.NewPersonPayload;
import com.example.demo.payload.UpdatePersonPayload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")

public class PeopleController {
    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personDAO.findAll();
    }
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") int id){
        return personDAO.findById(id);
    }
    @PostMapping
    public Person createPerson(@RequestBody NewPersonPayload payload){
        return personDAO.save(payload.name());
    }
    @PatchMapping("/{id}")
    public Person update(@PathVariable("id") int id, @RequestBody UpdatePersonPayload payload){
        return personDAO.update(new Person(id, payload.name()));

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.deleteById(id);
        return "Person was deleted";
    }
}
