package fi.restapi.project.report;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-summary")
public class OrderSummaryController {
    private final OrderSummaryRepository summaries;

    public OrderSummaryController(OrderSummaryRepository summaries) {
        this.summaries = summaries;
    }

    @GetMapping
    public List<OrderSummary> latest() {
        return summaries.findLatest();
    }
}
