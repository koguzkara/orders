package ecommerce.orders.data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class ProductData implements Serializable  {


    private Long id;


    @NotBlank(message = "Product should have a unique name")
    @Size(max = 64, message = "product name can not be bigger than 64 characters ")
    private String name;

    @NotBlank(message = "Product should have a unique name")
    @Size(max = 128, message = "product description can not be bigger than 128 characters ")
    private String description;

    private List<ProductPriceData> prices;

    public ProductData() {
    }

    public ProductData(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductData(Long id, String name, String description) {
        this.id = id;
        this.description = description;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductPriceData> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPriceData> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
