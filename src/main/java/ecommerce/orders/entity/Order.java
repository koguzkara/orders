package ecommerce.orders.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name="orders")
public class Order{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Email
    @Column
    private String email;


    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProductPrices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<OrderProduct> getOrderProductPrices() {
        return orderProductPrices;
    }

    public void setOrderProductPrices(Set<OrderProduct> orderProductPrices) {
        this.orderProductPrices = orderProductPrices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(email, order.email) && Objects.equals(orderDate, order.orderDate) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", orderDate=" + orderDate +
                ", orderProductPrices=" + orderProductPrices +
                '}';
    }
}
