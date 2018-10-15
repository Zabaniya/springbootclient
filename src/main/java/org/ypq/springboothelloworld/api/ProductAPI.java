package org.ypq.springboothelloworld.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.ypq.springboothelloworld.Product;

import java.util.List;

@RequestMapping("/product-service-api")
public interface ProductAPI {

    @RequestMapping(value = "/getOneProduct")
    public Product getOneProduct(int id);

    @RequestMapping(value = "/getProducts")
    public List<Product> getProducts();

}

