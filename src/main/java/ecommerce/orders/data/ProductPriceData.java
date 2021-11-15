package ecommerce.orders.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductPriceData implements Serializable  {

    private MonetaryAmount money;

    private int size;

    public ProductPriceData() {
    }

    public ProductPriceData(BigDecimal amount, String currency) {
        this.money = new MonetaryAmount(amount,currency);
    }

    public ProductPriceData(MonetaryAmount amount){
        this.money = amount;
    }

    public ProductPriceData(Long id, BigDecimal amount, String currency) {
        this(amount,currency);
        this.id = id;
    }

    public ProductPriceData(MonetaryAmount money, int size, Long id) {
        this.money = money;
        this.size = size;
        this.id = id;
    }

    private Long id;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MonetaryAmount getMoney() {
        return money;
    }

    public void setMoney(MonetaryAmount money) {
        this.money = money;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ProductPriceData{" +
                "money=" + money +
                ", size=" + size +
                ", id=" + id +
                '}';
    }
}
