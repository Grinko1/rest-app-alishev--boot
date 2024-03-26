package com.example.demo.controllers;

import com.example.demo.model.Person;
import com.example.demo.services.ItemService;
import com.example.demo.services.PersonService;
import com.example.demo.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")

public class PeopleController {
    private final PersonValidator personValidator;
    private final PersonService personService;
    private final ItemService itemService;

    public PeopleController(PersonValidator personValidator, PersonService personService, ItemService itemService) {
        this.personValidator = personValidator;
        this.personService = personService;
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = personService.findAll();
        System.out.println(people);
//        itemService.findByName("Airpods");
//        itemService.findByOwner(people.get(0));

//        personService.test();
        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
        Person person = personService.findById(id).orElse(null);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        System.out.println(email);
        Optional<Person> personOptional = personService.findByEmail(email);

        if (personOptional.isPresent()) {

            Person person = personOptional.get();
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @PostMapping
    public ResponseEntity<Void> createPerson(@Valid @RequestBody Person person, BindingResult bindingResult) throws Exception {
        System.out.println(person);
        System.out.println(person.getDateBirth());
//        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            personService.save(person);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PatchMapping("/{id:\\d+}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @Valid @RequestBody Person person, BindingResult bindingResult) throws Exception {
        person.setId(id);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        personService.update(person);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        personService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Person was deleted");
    }
}
