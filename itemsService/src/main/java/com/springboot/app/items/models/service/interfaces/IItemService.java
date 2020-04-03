package com.springboot.app.items.models.service.interfaces;

import com.springboot.app.items.models.Item;

import java.util.List;

public interface IItemService {
    List<Item> findAll();
    Item findById(Long id, Integer cantidad);
}
