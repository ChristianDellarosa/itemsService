package com.springboot.app.items.clientes;

import com.springboot.app.items.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "products-service", url = "http://localhost:8001")
public interface ProductClientFeign {

    @GetMapping("/products/list")
    List<Product> list();

    @GetMapping("/products/list/{id}")
    Product detalle(@PathVariable Long id);
}
