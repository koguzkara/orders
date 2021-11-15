package ecommerce.orders.entity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "order_product")
public class OrderProduct {

    public OrderProduct(){}

    public OrderProduct(Long id, Order order, ProductPrice productPrice, int size) {
        this.id = id;
        this.order = order;
        this.productPrice = productPrice;
        this.size = size;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_price_id")
    private ProductPrice productPrice;


    @Column
    private int size;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductPrice getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", productPrice=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, size);
    }
}
