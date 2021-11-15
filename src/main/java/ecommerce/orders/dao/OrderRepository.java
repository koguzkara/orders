package ecommerce.orders.dao;

import ecommerce.orders.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);
}
