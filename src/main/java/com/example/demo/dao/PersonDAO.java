package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
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

    //////////////////////////////////////////
    //TEST BATCH UPDATE AND USUAL UPDATE/////
    /////////////////////////////////////////
    public void testMultipleUpdate() {
        List<Person> people = create1000People();
        for (Person person : people) {
            jdbcTemplate.update("insert into person( name, age, email) values(?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
        }
    }

    public void testBatchUpdate() {
        List<Person> people = create1000People();
        jdbcTemplate.batchUpdate("insert into person( name, age, email) values(?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, people.get(i).getName());
                ps.setInt(2, people.get(i).getAge());
                ps.setString(3, people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "name" + i, 30, "email" + i));
        }
        return people;
    }
}
