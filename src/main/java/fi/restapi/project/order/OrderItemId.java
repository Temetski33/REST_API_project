package fi.restapi.project.order;

import java.io.Serializable;

public class OrderItemId implements Serializable {
    private Long order;
    private Long product;

    public OrderItemId() { }
    public OrderItemId(Long order, Long product) { this.order = order; this.product = product; }
    public Long getOrder() { return order; }
    public void setOrder(Long order) { this.order = order; }
    public Long getProduct() { return product; }
    public void setProduct(Long product) { this.product = product; }
    @Override public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OrderItemId other)) return false;
        return java.util.Objects.equals(order, other.order) && java.util.Objects.equals(product, other.product);
    }
    @Override public int hashCode() { return java.util.Objects.hash(order, product); }
}
