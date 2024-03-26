package com.example.demo.repository;

import com.example.demo.model.Item;
import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByOwner(Person owner);
    List<Item> findByName(String name);
}
