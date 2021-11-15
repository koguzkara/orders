package ecommerce.orders.service;

import ecommerce.orders.data.OrderData;
import ecommerce.orders.data.OrderDetailData;
import ecommerce.orders.data.OrderSumData;

import java.util.Date;
import java.util.List;

public interface OrderService {
    OrderSumData placeOrder(OrderData order);

    OrderSumData sumOrder(Long orderId);

    List<OrderSumData> querySumOrder(Date queryStartDate, Date queryEndDate);

    OrderDetailData detailOrder(Long orderId);

    List<OrderDetailData> detailOrderList();

    List<OrderDetailData> queryDetailOrder(Date queryStartDate, Date queryEndDate);


}
