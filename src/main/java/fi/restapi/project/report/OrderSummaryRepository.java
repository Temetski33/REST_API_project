package fi.restapi.project.report;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderSummaryRepository {
    private final JdbcTemplate jdbc;

    public OrderSummaryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<OrderSummary> findLatest() {
        return jdbc.query("""
                SELECT order_id, order_date, status, customer_id, customer_name, item_count
                FROM order_summary
                ORDER BY order_date DESC
                LIMIT 50
                """, (result, row) -> new OrderSummary(
                result.getLong("order_id"),
                result.getTimestamp("order_date").toLocalDateTime(),
                result.getString("status"),
                result.getLong("customer_id"),
                result.getString("customer_name"),
                result.getLong("item_count")));
    }
}
