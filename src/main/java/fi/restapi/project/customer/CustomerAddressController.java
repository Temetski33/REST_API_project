package fi.restapi.project.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/customer-addresses")
public class CustomerAddressController {
    private final CustomerAddressRepository addresses;

    public CustomerAddressController(CustomerAddressRepository addresses) {
        this.addresses = addresses;
    }

    @GetMapping
    public List<CustomerAddress> all(@RequestParam(required = false) Long customerId) {
        return customerId == null ? addresses.findAll() : addresses.findByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public CustomerAddress one(@PathVariable Long id) {
        return addresses.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerAddress create(@RequestBody CustomerAddress address) {
        address.setId(null);
        return addresses.save(address);
    }

    @PutMapping("/{id}")
    public CustomerAddress update(@PathVariable Long id, @RequestBody CustomerAddress input) {
        CustomerAddress address = addresses.findById(id).orElseThrow();
        address.setCustomer(input.getCustomer());
        address.setStreetAddress(input.getStreetAddress());
        address.setPostalCode(input.getPostalCode());
        address.setCity(input.getCity());
        address.setCountry(input.getCountry());
        return addresses.save(address);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        addresses.deleteById(id);
    }
}
