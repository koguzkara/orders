package ecommerce.orders.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderSumData implements Serializable {

    private Long id;

    private List<MonetaryAmount> amounts;


    private String email;

    private Date orderData;

    public OrderSumData(){}

    public OrderSumData(Long id, List<MonetaryAmount> amounts){
        this.amounts = amounts;
        this.id = id;
    }

    public OrderSumData(Long id, List<MonetaryAmount> amounts, String email, Date orderData) {
        this.id = id;
        this.amounts = amounts;
        this.email = email;
        this.orderData = orderData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MonetaryAmount> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<MonetaryAmount> amounts) {
        this.amounts = amounts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getOrderData() {
        return orderData;
    }

    public void setOrderData(Date orderData) {
        this.orderData = orderData;
    }

    @Override
    public String toString() {
        return "OrderSum{" +
                "id=" + id +
                ", list=" + amounts +
                ", email='" + email + '\'' +
                ", orderData=" + orderData +
                '}';
    }
}
