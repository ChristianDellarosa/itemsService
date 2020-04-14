package com.springboot.app.items.clientes;

import com.springboot.app.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductClientFeign {

    @GetMapping("/list")
    List<Product> list();

    @GetMapping("/list/{id}")
    Product detalle(@PathVariable Long id);

    @PostMapping("/create")
    Product create(@RequestBody Product product);

    @PutMapping("/update/{id}")
    Product update(@RequestBody Product product, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id);
}
