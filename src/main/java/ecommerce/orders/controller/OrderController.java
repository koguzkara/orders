package ecommerce.orders.controller;


import ecommerce.orders.data.OrderData;
import ecommerce.orders.data.OrderDetailData;
import ecommerce.orders.data.OrderSumData;
import ecommerce.orders.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    private static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @PostMapping(value = "/order/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderSumData placeOrder(@Valid @RequestBody OrderData order) {
        return service.placeOrder(order);
    }

    @GetMapping(value = "/order/{orderId}/sum",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public OrderSumData sumOrder(@PathVariable(value = "orderId") Long orderId) {
        return service.sumOrder(orderId);
    }

    @GetMapping(value = "/order/{orderId}/detail",produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderDetailData detailOrder(@PathVariable(value = "orderId") Long orderId) {
        return service.detailOrder(orderId);
    }

    @GetMapping(value = "/orders",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailData> detailOrderList() {
        return service.detailOrderList();
    }

    @GetMapping(value = "/order/sumquery", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderSumData> querySumOrder(@RequestParam(value = "startDate") Date startDate,
                                         @RequestParam(value = "endDate") Date endDate) {
        return service.querySumOrder(startDate, endDate);
    }


    @GetMapping(value = "/order/detailquery", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDetailData> queryDetailOrder(@RequestParam(value = "startDate") Date startDate,
                                         @RequestParam(value = "endDate") Date endDate) {
        return service.queryDetailOrder(startDate, endDate);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        LOG.warn("Returning HTTP 400 Bad Request", e);
    }
}
