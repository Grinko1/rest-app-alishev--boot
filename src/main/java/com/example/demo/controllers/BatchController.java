package com.example.demo.controllers;

import com.example.demo.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {
    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @GetMapping("/without")
    public String withoutBath() {
        long start = System.currentTimeMillis();
        personDAO.testMultipleUpdate();
        long end = System.currentTimeMillis();
        return "without takes time:- " + (end - start);
    }

    @GetMapping("/with")
    public String withBatch() {
        long start = System.currentTimeMillis();
        personDAO.testBatchUpdate();
        long end = System.currentTimeMillis();

        return "with takes time:- " + (end - start);
    }
}
