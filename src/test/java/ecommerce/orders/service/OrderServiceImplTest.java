package ecommerce.orders.service;

import ecommerce.orders.dao.OrderRepository;
import ecommerce.orders.dao.ProductPriceRepository;
import ecommerce.orders.entity.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductPriceRepository productPriceRepository;

    @Test
    public void givenTwoCurrencies_OrderSumShouldReturnSeparetely(){

    }



}
