package com.springboot.app.items.models.service;

import com.springboot.app.items.clientes.ProductClientFeign;
import com.springboot.app.items.models.Item;
import com.springboot.app.items.models.Product;
import com.springboot.app.items.models.service.interfaces.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
//@Primary /*Ya que tengo 2 implementaciones, le digo que esta es por defecto*/
public class ItemServiceFeign implements IItemService {

    private ProductClientFeign clienteFeign;

    @Autowired
    public ItemServiceFeign(ProductClientFeign productClientFeign) {
        this.clienteFeign = productClientFeign;
    }

    @Override
    public List<Item> findAll() {
        return clienteFeign.list().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(clienteFeign.detalle(id),cantidad);
    }

    @Override
    public Product save(Product product) {
        return clienteFeign.create(product);
    }

    @Override
    public Product update(Product product, Long id) {
        return clienteFeign.update(product,id);
    }

    @Override
    public void delete(Long id) {
        clienteFeign.delete(id);
    }
}
