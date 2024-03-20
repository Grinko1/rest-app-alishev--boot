package com.example.demo.controllers;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;
import com.example.demo.payload.NewPersonPayload;
import com.example.demo.payload.UpdatePersonPayload;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    public Person getPersonById(@PathVariable("id") int id) throws SQLException {
        return personDAO.findById(id);
    }
    @PostMapping
    public void createPerson(@Valid @RequestBody NewPersonPayload payload, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()){
            throw new Exception("Invalid data for creating new person");
        }else {
             personDAO.save(payload.name(), payload.age(), payload.email());
        }

    }
    @PatchMapping("/{id}")
    public Person update(@PathVariable("id") int id, @Valid @RequestBody UpdatePersonPayload payload) throws SQLException {
        return personDAO.update(new Person(id, payload.name(), payload.age(), payload.email()));

    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.deleteById(id);
        return "Person was deleted";
    }
}
