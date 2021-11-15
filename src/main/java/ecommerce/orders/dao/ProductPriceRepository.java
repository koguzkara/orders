package ecommerce.orders.dao;

import ecommerce.orders.entity.ProductPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

}
