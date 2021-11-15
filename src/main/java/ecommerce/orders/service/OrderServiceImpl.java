package ecommerce.orders.service;

import ecommerce.orders.dao.OrderRepository;
import ecommerce.orders.dao.ProductPriceRepository;
import ecommerce.orders.data.*;
import ecommerce.orders.entity.Order;
import ecommerce.orders.entity.OrderProduct;
import ecommerce.orders.entity.ProductPrice;
import ecommerce.orders.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductPriceRepository productPriceRepository;

    public OrderServiceImpl(OrderRepository or, ProductPriceRepository ppr){
        this.orderRepository = or;
        this.productPriceRepository = ppr;
    }

    @Override
    public OrderSumData placeOrder(OrderData data) {
        List<OrderSize> sizes = data.getProducts();

        List<Long> ppIds = new ArrayList<>();
        sizes.forEach(s-> ppIds.add(s.getProductPriceId()));

        Iterable<ProductPrice> iter = this.productPriceRepository.findAllById(ppIds);
        Order order = convert(data);
        Set<OrderProduct> set = new HashSet<>();

        iter.forEach(i-> set.add(new OrderProduct(null, order,i, findSize(i.getId(), sizes))));
        order.setOrderProductPrices(set);
        Order orderPersisted  = this.orderRepository.save(order);

        return new OrderSumData(orderPersisted.getId(), calculateSum(orderPersisted)
                , orderPersisted.getEmail(), orderPersisted.getOrderDate());
    }



    @Override
    public OrderSumData sumOrder(Long orderId) {
        Order order  = this.orderRepository.findById(orderId).orElseThrow(
                ()-> new NotFoundException("Could not find "+orderId)
        );

        return new OrderSumData(order.getId(), calculateSum(order)
                , order.getEmail(), order.getOrderDate());
    }

    @Override
    public List<OrderSumData> querySumOrder(Date queryStartDate, Date queryEndDate) {
        List<Order> list = this.orderRepository.findByOrderDateBetween(queryStartDate, queryEndDate);
        return list.stream().map(o->new OrderSumData(o.getId(), calculateSum(o)
                , o.getEmail(), o.getOrderDate())).collect(Collectors.toList());

    }

    @Override
    public OrderDetailData detailOrder(Long orderId) {
        Order order  = this.orderRepository.findById(orderId).orElseThrow(
                ()-> new NotFoundException("Could not find "+orderId)
        );

        return convert(order);
    }

    @Override
    public List<OrderDetailData> detailOrderList() {
        List<Order> orders = new ArrayList<>();
        this.orderRepository.findAll().forEach(orders::add);
        return orders.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailData> queryDetailOrder(Date queryStartDate, Date queryEndDate) {
        List<Order> orders = new ArrayList<>();
        this.orderRepository.findByOrderDateBetween(queryStartDate, queryEndDate).forEach(orders::add);
        return orders.stream().map(this::convert).collect(Collectors.toList());
    }

    private Order convert(OrderData data){
        Order o = new Order();
        o.setId(data.getId());
        o.setOrderDate(data.getOrderDate());
        o.setEmail(data.getEmail());
        return o;
    }

    private int findSize(Long productPriceId, List<OrderSize> list){
        return  list.stream().filter(l-> l.getProductPriceId().equals(productPriceId)).findAny()
                .map(OrderSize::getSize).orElse(0);


    }


    private List<MonetaryAmount> calculateSum(Order orderPersisted){
        Map<String, MonetaryAmount> map = new HashMap<>();
        orderPersisted.getOrderProductPrices().stream().forEach(op->{
            if(map.containsKey(op.getProductPrice().getCurrency())){
                MonetaryAmount money = map.get(op.getProductPrice().getCurrency());
                money.setAmount(money.getAmount().
                        add(op.getProductPrice().getAmount().multiply(BigDecimal.valueOf(op.getSize()))));
            } else{
                map.put(op.getProductPrice().getCurrency(),
                        new MonetaryAmount(op.getProductPrice().getAmount().multiply(BigDecimal.valueOf(op.getSize()))
                                , op.getProductPrice().getCurrency()));
            }
        });

        return List.of(map.values().toArray(new MonetaryAmount[]{}));
    }

    private OrderDetailData convert(Order order){

        OrderDetailData data = new OrderDetailData();
        data.setId(order.getId());
        data.setOrderData(order.getOrderDate());
        data.setEmail(order.getEmail());

        List<ProductPriceData> list  = order.getOrderProductPrices().stream().map(o->{
            ProductPriceData ppd = new ProductPriceData();
            ppd.setId(o.getProductPrice().getId());
            ppd.setMoney(new MonetaryAmount(o.getProductPrice().getAmount(),o.getProductPrice().getCurrency()));
            ppd.setSize(o.getSize());
            return ppd;
        }).collect(Collectors.toList());

        data.setProductPriceDataList(list);
        data.setAmounts(calculateSum(order));
        return data;
    }
}
