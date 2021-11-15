package ecommerce.orders.data;

import org.hibernate.validator.constraints.Currency;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class MonetaryAmount implements Serializable {

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    @Currency({"USD","EUR","YTL"})
    private String currency;

    public MonetaryAmount(){}

    public MonetaryAmount(BigDecimal amount, String currency){
        this.amount = amount;
        this.currency = currency;
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

    @Override
    public String toString() {
        return "MonetaryAmount{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
