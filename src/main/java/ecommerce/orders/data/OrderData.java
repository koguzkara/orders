package ecommerce.orders.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderData implements Serializable {

    private Long id;


    @Email
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    private List<OrderSize> products;


    public OrderData(){}

    public OrderData(Long id, List<OrderSize> list){
        this.id= id;
        this.products =list;
    }

    public OrderData(Long id, String email, Date orderData, List<OrderSize> productPrices) {
        this.id = id;
        this.email = email;
        this.orderDate = orderData;
        this.products = productPrices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderSize> getProducts() {
        return products;
    }

    public void setProducts(List<OrderSize> products) {
        this.products = products;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", orderData=" + orderDate +
                ", productPrices=" + products +
                '}';
    }
}
