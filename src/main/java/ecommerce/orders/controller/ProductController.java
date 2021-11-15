package ecommerce.orders.controller;


import ecommerce.orders.data.MonetaryAmount;
import ecommerce.orders.data.ProductData;
import ecommerce.orders.data.ProductPriceData;
import ecommerce.orders.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductData> getProducts() {
        return service.getAll();
    }

    @PostMapping(value = "/product/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductData newProduct(@Valid @RequestBody ProductData data) {
        return service.newProduct(data);
    }

    @PutMapping(value = "/product/{id}/update", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductData update(@PathVariable(value = "id") Long id, @Valid @RequestBody ProductData data) {
        return service.updateProductDescription(id, data);
    }

    @PostMapping(value = "/product/{productId}/price/add", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public List<ProductPriceData> addPrice(@PathVariable(value="productId") Long productId, @Valid @RequestBody MonetaryAmount money){
            return service.newProductPrice(productId, money.getAmount(), money.getCurrency());
    }

}
