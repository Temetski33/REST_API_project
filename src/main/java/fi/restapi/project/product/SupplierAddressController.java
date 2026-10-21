package fi.restapi.project.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supplier-addresses")
public class SupplierAddressController {
    private final SupplierAddressRepository addresses;

    public SupplierAddressController(SupplierAddressRepository addresses) {
        this.addresses = addresses;
    }

    @GetMapping
    public List<SupplierAddress> all(@RequestParam(required = false) Long supplierId) {
        return supplierId == null ? addresses.findAll() : addresses.findBySupplierId(supplierId);
    }

    @GetMapping("/{id}")
    public SupplierAddress one(@PathVariable Long id) {
        return addresses.findById(id).orElseThrow();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierAddress create(@RequestBody SupplierAddress address) {
        address.setId(null);
        return addresses.save(address);
    }

    @PutMapping("/{id}")
    public SupplierAddress update(@PathVariable Long id, @RequestBody SupplierAddress input) {
        SupplierAddress address = addresses.findById(id).orElseThrow();
        address.setSupplier(input.getSupplier());
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
