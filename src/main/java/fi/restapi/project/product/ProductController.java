package fi.restapi.project.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository products;
    public ProductController(ProductRepository products) { this.products = products; }
    @GetMapping public List<Product> all(@RequestParam(required = false) String search, @RequestParam(required = false) Long categoryId) {
        if (search != null) return products.findByNameContainingIgnoreCase(search);
        if (categoryId != null) return products.findByCategoryId(categoryId);
        return products.findAll();
    }
    @GetMapping("/{id}") public Product one(@PathVariable Long id) { return products.findById(id).orElseThrow(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Product create(@RequestBody Product product) { product.setId(null); return products.save(product); }
    @PutMapping("/{id}") public Product update(@PathVariable Long id, @RequestBody Product input) {
        Product product = products.findById(id).orElseThrow();
        product.setName(input.getName()); product.setDescription(input.getDescription()); product.setPrice(input.getPrice());
        product.setStock(input.getStock()); product.setCategory(input.getCategory());
        return products.save(product);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { products.deleteById(id); }
}
