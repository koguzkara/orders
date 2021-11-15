package ecommerce.orders.service;

import ecommerce.orders.data.ProductData;
import ecommerce.orders.data.ProductPriceData;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductData newProduct(ProductData data);

    ProductData updateProductDescription(Long id, ProductData data) ;

    List<ProductData> getAll();

    List<ProductPriceData> newProductPrice(Long productId, BigDecimal amount, String currency);



}
