package com.springboot.app.items.models.service.interfaces;

import com.springboot.app.items.models.Item;
import com.springboot.app.items.models.Product;

import java.util.List;

public interface IItemService {
    List<Item> findAll();
    Item findById(Long id, Integer cantidad);
    Product save(Product product);
    Product update(Product product, Long id);
    void delete(Long id);
}
