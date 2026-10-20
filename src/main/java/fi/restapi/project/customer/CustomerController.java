package fi.restapi.project.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerRepository customers;
    public CustomerController(CustomerRepository customers) { this.customers = customers; }
    @GetMapping public List<Customer> all() { return customers.findAll(); }
    @GetMapping("/{id}") public Customer one(@PathVariable Long id) { return customers.findById(id).orElseThrow(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Customer create(@RequestBody Customer customer) { customer.setId(null); return customers.save(customer); }
    @PutMapping("/{id}") public Customer update(@PathVariable Long id, @RequestBody Customer input) {
        Customer customer = customers.findById(id).orElseThrow();
        customer.setFirstName(input.getFirstName()); customer.setLastName(input.getLastName());
        customer.setEmail(input.getEmail()); customer.setPhone(input.getPhone());
        return customers.save(customer);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { customers.deleteById(id); }
}
