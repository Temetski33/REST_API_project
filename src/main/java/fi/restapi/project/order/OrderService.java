package fi.restapi.project.order;

import fi.restapi.project.customer.CustomerAddress;
import fi.restapi.project.customer.CustomerAddressRepository;
import fi.restapi.project.customer.CustomerRepository;
import fi.restapi.project.product.Product;
import fi.restapi.project.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orders;
    private final CustomerRepository customers;
    private final CustomerAddressRepository addresses;
    private final ProductRepository products;

    public OrderService(OrderRepository orders, CustomerRepository customers,
                        CustomerAddressRepository addresses, ProductRepository products) {
        this.orders = orders;
        this.customers = customers;
        this.addresses = addresses;
        this.products = products;
    }

    @Transactional
    public Order create(OrderCreateRequest request) {
        Order order = new Order();
        order.setCustomer(customers.findById(request.customerId()).orElseThrow());
        if (request.shippingAddressId() != null) {
            CustomerAddress address = addresses.findById(request.shippingAddressId()).orElseThrow();
            order.setShippingAddress(address);
        }
        if (request.items() == null || request.items().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
        for (OrderCreateRequest.OrderLineRequest line : request.items()) {
            if (line.quantity() == null || line.quantity() < 1) {
                throw new IllegalArgumentException("Quantity must be at least 1");
            }
            Product product = products.findById(line.productId()).orElseThrow();
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(line.quantity());
            item.setUnitPrice(product.getPrice());
            order.getItems().add(item);
        }
        return orders.save(order);
    }

    public List<Order> findAll(Long customerId) {
        return customerId == null
                ? orders.findTop50ByOrderByOrderDateDesc()
                : orders.findTop50ByCustomerIdOrderByOrderDateDesc(customerId);
    }
}
