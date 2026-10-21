package fi.restapi.project.order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findTop50ByOrderByOrderDateDesc();
    List<Order> findTop50ByCustomerIdOrderByOrderDateDesc(Long customerId);
}
