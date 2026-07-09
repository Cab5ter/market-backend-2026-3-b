package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.web.controller;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.ProductService;
import org.hibernate.validator.internal.constraintvalidators.bv.PatternValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PatternValidator patternValidator;

    @GetMapping("")
    public ResponseEntity<List<Product>>getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/caregory/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("category") int categoryId){
        return productService.getByCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
        return ResponseEntity.ok(productService.delete(productId));
    }


}
