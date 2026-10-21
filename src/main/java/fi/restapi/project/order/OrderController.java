package fi.restapi.project.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orders;
    private final OrderService orderService;

    public OrderController(OrderRepository orders, OrderService orderService) {
        this.orders = orders;
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> all(@RequestParam(required = false) Long customerId) {
        return orderService.findAll(customerId);
    }

    @GetMapping("/{id}")
    public Order one(@PathVariable Long id) {
        return orders.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody OrderCreateRequest request) {
        return orderService.create(request);
    }
}
