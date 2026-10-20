package fi.restapi.project.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {
    private final ProductCategoryRepository categories;
    public ProductCategoryController(ProductCategoryRepository categories) { this.categories = categories; }
    @GetMapping public List<ProductCategory> all() { return categories.findAll(); }
    @GetMapping("/{id}") public ProductCategory one(@PathVariable Long id) { return categories.findById(id).orElseThrow(); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public ProductCategory create(@RequestBody ProductCategory category) { category.setId(null); return categories.save(category); }
    @PutMapping("/{id}") public ProductCategory update(@PathVariable Long id, @RequestBody ProductCategory input) {
        ProductCategory category = categories.findById(id).orElseThrow(); category.setName(input.getName()); return categories.save(category);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { categories.deleteById(id); }
}
