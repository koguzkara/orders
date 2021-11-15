package ecommerce.orders.entity;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="product_price")
public class ProductPrice {


    @Id
    @GeneratedValue
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    @Column
    @NotEmpty
    private BigDecimal amount;


    @Column
    @NotEmpty
    private String currency;


    @Column
    @NotEmpty
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;


    @Column
    @NotNull
    private boolean isActive;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPrice price = (ProductPrice) o;
        return isActive == price.isActive && Objects.equals(id, price.id) && Objects.equals(product, price.product)
                && Objects.equals(amount, price.amount) && Objects.equals(currency, price.currency)
                && Objects.equals(startDate, price.startDate) && Objects.equals(endDate, price.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, amount, currency, startDate, endDate, isActive);
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}
