package com.springboot.app.items.models;

public class Item {

    private Product producto;
    private Integer cantidad;

    public Item() {
    }

    public Item(Product producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Product getProducto() {
        return producto;
    }

    public void setProducto(Product producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal()  { /*Como es publico, TOTAL forma parte del objeto(dado que tengo un metodo getALGO*/
        return  producto.getPrecio() * cantidad.doubleValue();
    }
}
