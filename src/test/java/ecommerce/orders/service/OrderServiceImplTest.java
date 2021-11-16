package ecommerce.orders.service;

import ecommerce.orders.dao.OrderRepository;
import ecommerce.orders.dao.ProductPriceRepository;
import ecommerce.orders.data.OrderSumData;
import ecommerce.orders.entity.Order;
import ecommerce.orders.entity.OrderProduct;
import ecommerce.orders.entity.Product;
import ecommerce.orders.entity.ProductPrice;
import ecommerce.orders.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(value = SpringExtension.class)
public class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductPriceRepository productPriceRepository;

    @Test
    public void givenOrder_DoesNotExistThrowNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());
        OrderServiceImpl impl = new OrderServiceImpl(orderRepository, productPriceRepository);

        assertThatThrownBy(() -> {
            impl.sumOrder(1L);
        }).isInstanceOf(NotFoundException.class).hasMessage("Could not find 1");
    }


    @Test
    public void givenOrderWithMultipleSize_ReturnedOrderSumShouldBeCorrect() {
        Date date = new Date();
        String email = "o@o.o";
        Order order = prepare(4L, email, date);
        when(orderRepository.findById(4L)).thenReturn(Optional.of(order));

        OrderServiceImpl impl = new OrderServiceImpl(orderRepository, productPriceRepository);
        OrderSumData data = impl.sumOrder(4L);

        assertThat(data.getId()).isEqualTo(4L);
        assertThat(data.getEmail()).isEqualTo(email);
        assertThat(data.getOrderData()).isEqualTo(date);

        assertThat(data.getAmounts())
                .isNotNull()
                .hasSize(1)
                .extracting("amount", "currency")
                .containsOnly(tuple(BigDecimal.valueOf(72.0D), "USD"));
    }

    @Test
    public void givenOrderWithMultipleCurrency_ReturnedOrderSumShouldBeCorrect() {
        Date date = new Date();
        String email = "o@o.o";
        Order order = prepareMulti(4L, email, date);
        when(orderRepository.findById(4L)).thenReturn(Optional.of(order));

        OrderServiceImpl impl = new OrderServiceImpl(orderRepository, productPriceRepository);
        OrderSumData data = impl.sumOrder(4L);

        assertThat(data.getId()).isEqualTo(4L);
        assertThat(data.getEmail()).isEqualTo(email);
        assertThat(data.getOrderData()).isEqualTo(date);

        assertThat(data.getAmounts())
                .isNotNull()
                .hasSize(2)
                .extracting("amount", "currency")
                .contains(tuple(BigDecimal.valueOf(20.0D), "USD"),
                        tuple(BigDecimal.valueOf(52.0D), "EUR"));
    }

    private Order prepareMulti(Long id, String email, Date date) {
        Order o1 = new Order();
        o1.setOrderDate(date);
        o1.setId(id);
        o1.setEmail(email);

        Set<OrderProduct> set = new HashSet<>();
        o1.setOrderProductPrices(set);

        OrderProduct op = new OrderProduct();
        op.setId(1L);
        op.setOrder(o1);
        op.setSize(2);
        set.add(op);


        Product p = new Product();
        p.setId(1L);
        p.setDescription("p description");
        p.setName("p1");
        p.setCreated(new Date());
        p.setUpdated(new Date());

        ProductPrice pp = new ProductPrice();
        op.setProductPrice(pp);

        pp.setActive(true);
        pp.setAmount(BigDecimal.valueOf(10.0d));
        pp.setCurrency("USD");
        pp.setStartDate(new Date());
        pp.setEndDate(new Date());
        pp.setId(1L);
        pp.setProduct(p);


        OrderProduct op2 = new OrderProduct();
        op2.setId(2L);
        op2.setOrder(o1);
        op2.setSize(4);
        set.add(op2);

        ProductPrice pp2 = new ProductPrice();
        op2.setProductPrice(pp2);
        pp2.setActive(true);
        pp2.setAmount(BigDecimal.valueOf(13.0d));
        pp2.setCurrency("EUR");
        pp2.setStartDate(new Date());
        pp2.setEndDate(new Date());
        pp2.setId(2L);
        pp2.setProduct(p);

        Set<ProductPrice> ppSet = new HashSet<>();
        ppSet.add(pp);
        ppSet.add(pp2);
        p.setPrices(ppSet);

        return o1;
    }

    private Order prepare(Long id, String email, Date date) {
        Order o1 = new Order();
        o1.setOrderDate(date);
        o1.setId(id);
        o1.setEmail(email);

        Set<OrderProduct> set = new HashSet<>();
        o1.setOrderProductPrices(set);

        OrderProduct op = new OrderProduct();
        op.setId(1L);
        op.setOrder(o1);
        op.setSize(2);
        set.add(op);


        Product p = new Product();
        p.setId(1L);
        p.setDescription("p description");
        p.setName("p1");
        p.setCreated(new Date());
        p.setUpdated(new Date());

        ProductPrice pp = new ProductPrice();
        op.setProductPrice(pp);

        pp.setActive(true);
        pp.setAmount(BigDecimal.valueOf(10.0d));
        pp.setCurrency("USD");
        pp.setStartDate(new Date());
        pp.setEndDate(new Date());
        pp.setId(1L);
        pp.setProduct(p);


        OrderProduct op2 = new OrderProduct();
        op2.setId(2L);
        op2.setOrder(o1);
        op2.setSize(4);
        set.add(op2);

        ProductPrice pp2 = new ProductPrice();
        op2.setProductPrice(pp2);
        pp2.setActive(true);
        pp2.setAmount(BigDecimal.valueOf(13.0d));
        pp2.setCurrency("USD");
        pp2.setStartDate(new Date());
        pp2.setEndDate(new Date());
        pp2.setId(2L);
        pp2.setProduct(p);

        Set<ProductPrice> ppSet = new HashSet<>();
        ppSet.add(pp);
        ppSet.add(pp2);
        p.setPrices(ppSet);

        return o1;
    }
}
