package ecommerce.orders.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderDetailData implements Serializable {

    private Long id;


    private String email;

    private Date orderData;

    private List<ProductPriceData> productPriceDataList;

    private List<MonetaryAmount> amounts;

    public OrderDetailData(){}

    public OrderDetailData(Long id, List<ProductPriceData> list){
        this.id =id;
        this.productPriceDataList = list;
    }

    public OrderDetailData(Long id, String email, Date orderData, List<ProductPriceData> productPriceDataList) {
        this.id = id;
        this.email = email;
        this.orderData = orderData;
        this.productPriceDataList = productPriceDataList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductPriceData> getProductPriceDataList() {
        return productPriceDataList;
    }

    public void setProductPriceDataList(List<ProductPriceData> productPriceDataList) {
        this.productPriceDataList = productPriceDataList;
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

    public List<MonetaryAmount> getAmounts() {
        return amounts;
    }

    public void setAmounts(List<MonetaryAmount> amounts) {
        this.amounts = amounts;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", orderData=" + orderData +
                ", productPriceDataList=" + productPriceDataList +
                '}';
    }
}
