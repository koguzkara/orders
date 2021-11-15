package ecommerce.orders.data;

public class OrderSize {

    private int size;
    private Long productPriceId;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Long getProductPriceId() {
        return productPriceId;
    }

    public void setProductPriceId(Long productPriceId) {
        this.productPriceId = productPriceId;
    }

    @Override
    public String toString() {
        return "OrderSize{" +
                "size=" + size +
                ", productPriceId=" + productPriceId +
                '}';
    }
}
