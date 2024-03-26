package com.example.demo.services;

import com.example.demo.model.Item;
import com.example.demo.model.Person;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findByName(String name){
        return itemRepository.findByName(name);
    }
    public List<Item> findByOwner(Person owner){
        return itemRepository.findByOwner(owner);
    }

}
