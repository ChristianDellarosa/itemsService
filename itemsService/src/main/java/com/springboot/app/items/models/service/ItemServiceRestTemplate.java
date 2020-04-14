package com.springboot.app.items.models.service;

import com.springboot.app.items.models.Item;
import com.springboot.app.items.models.Product;
import com.springboot.app.items.models.service.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceRestTemplate implements IItemService {

    private RestTemplate clienteRest;

    @Autowired
    public ItemServiceRestTemplate(RestTemplate clienteRest) {
        this.clienteRest = clienteRest;
    }

    @Override
    public List<Item> findAll() {
        List<Product> productos = Arrays.asList(clienteRest.getForObject("http://products-service/list", Product[].class));
        return productos.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String,String> pathVariables = new HashMap();
        pathVariables.put("id",id.toString());
        Product product = clienteRest.getForObject("http://products-service/list/{id}", Product.class,pathVariables);
        return new Item(product,cantidad);
    }

    @Override
    public Product save(Product product) {
        HttpEntity<Product> body = new HttpEntity<>(product);
        ResponseEntity<Product> responseEntity = clienteRest.exchange("http://products-service/create", HttpMethod.POST, body, Product.class);
        Product productResponse = responseEntity.getBody();
        return productResponse;
    }


    @Override
    public Product update(Product product, Long id) {
        Map<String,String> pathVariables = new HashMap();
        pathVariables.put("id",id.toString());
        HttpEntity<Product> body = new HttpEntity<>(product);
        ResponseEntity<Product> responseEntity = clienteRest.exchange("http://products-service/update/{id}", HttpMethod.PUT, body, Product.class, pathVariables);
        return responseEntity.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String,String> pathVariables = new HashMap();
        pathVariables.put("id",id.toString());
        clienteRest.delete("http://products-service/delete/{id}",  pathVariables);
    }
}
