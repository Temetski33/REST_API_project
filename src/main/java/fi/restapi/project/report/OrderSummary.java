package fi.restapi.project.report;

import java.time.LocalDateTime;

public record OrderSummary(
        Long orderId,
        LocalDateTime orderDate,
        String status,
        Long customerId,
        String customerName,
        Long itemCount) {
}
