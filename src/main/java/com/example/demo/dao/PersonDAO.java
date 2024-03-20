package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private static Connection connection;


    @Autowired

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(int id) {
        return jdbcTemplate.query("select * from person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);

    }

    public void save(String name, int age, String email) {
        jdbcTemplate.update("insert into person(name, age, email) values(?, ?, ?)", name, age, email);
    }

    public Person update(Person person) {
        jdbcTemplate.update("update person set name=?, age=?, email=? WHERE id =?", person.getName(), person.getAge(), person.getEmail(), person.getId());
        return person;
    }

    public void deleteById(int id) {
        jdbcTemplate.update("delete from person where id=?", id);

    }
}
