package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int peopleCount = 0;
    private final List<Person> people;

    private static final String url = "jdbc:postgresql://localhost:5432/security_db";
    private static final String username = "postgres";
    private static final String password = "postgres";
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    {
        people = new ArrayList<>();
        people.add(new Person(++peopleCount, "Nadya", 30, "nadya@mail.com"));
        people.add(new Person(++peopleCount, "Kate", 29, "kate@mail.com"));
        people.add(new Person(++peopleCount, "Mike", 31, "mike@mail.com"));
        people.add(new Person(++peopleCount, "John", 37, "john@mail.com"));
    }

    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                personList.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }

    public Person findById(int id) throws SQLException {
        Person person = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select * from person where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        person.setName(resultSet.getString("name"));
        return person;

    }

    public void save(String name, int age, String email) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into person(name, age, email) values(?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, email);

            //bad approach
/*            Statement statement = connection.createStatement();
            String SQL = "insert into person(name, age, email) values('" + name + "', '" + age + "', '" + email + "' )";
            statement.executeUpdate(SQL);*/


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Person update(Person person) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update person set name=?, age=?, email=? WHERE id =?");
        preparedStatement.setString(1, person.getName());
        preparedStatement.setInt(2, person.getAge());
        preparedStatement.setString(3, person.getEmail());
        preparedStatement.setInt(4, person.getId());
        preparedStatement.executeUpdate();

        return person;
    }

    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from person where id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
