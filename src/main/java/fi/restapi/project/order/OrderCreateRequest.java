package fi.restapi.project.order;

import java.util.List;

public record OrderCreateRequest(Long customerId, Long shippingAddressId, List<OrderLineRequest> items) {
    public record OrderLineRequest(Long productId, Integer quantity) { }
}
