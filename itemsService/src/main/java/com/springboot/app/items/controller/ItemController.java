package com.springboot.app.items.controller;

import com.springboot.app.items.models.Item;
import com.springboot.app.items.models.service.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("items")
public class ItemController {

    private IItemService itemService;

    @Autowired
    public ItemController(@Qualifier("serviceRestTemplate") IItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @GetMapping("/list/{id}/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
        return itemService.findById(id,cantidad);
    }
}
