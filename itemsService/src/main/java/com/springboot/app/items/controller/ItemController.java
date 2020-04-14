package com.springboot.app.items.controller;

import com.springboot.app.items.models.Item;
import com.springboot.app.items.models.Product;
import com.springboot.app.items.models.service.interfaces.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
//@RequestMapping("items")
public class ItemController {

    private static Logger logger = LoggerFactory.getLogger(ItemController.class);

    private Environment environment;

    private IItemService itemService;

    @Value("${configuracion.texto}")
    private String texto;

    @Autowired
    public ItemController(@Qualifier("serviceRestTemplate") IItemService itemService, Environment environment) {  /*@Qualifier("serviceFeign) para utilizar como cliente rest a Feign en lugar de RestTemplate*/
        this.itemService = itemService;
        this.environment = environment;
    }

    @GetMapping("/list")
    public List<Item> list() {
        return itemService.findAll();
    }

    @GetMapping("/list/{id}/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
        return itemService.findById(id,cantidad);
    }

    @GetMapping("/obtener-config")
    public ResponseEntity<?> obtenerConfig( @Value("${server.port}") String port) { /*Otra forma de traer el valor, en vez de un atributo*/
        logger.info(texto);
        HashMap<String,String> jsonMap = new HashMap<>();
        jsonMap.put("texto", texto);
        jsonMap.put("puerto", port);

        if((environment.getActiveProfiles().length > 0) && (environment.getActiveProfiles()[0].equals("dev"))) {
            jsonMap.put("autor.nombre",environment.getProperty("configuracion.autor.nombre"));
            jsonMap.put("autor.email",environment.getProperty("configuracion.autor.email"));
        }
        return new ResponseEntity<Map<String,String>>(jsonMap, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product){
        return itemService.save(product);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product, @PathVariable Long id){
        return itemService.update(product,id);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        itemService.delete(id);
    }
}
