package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Manage products in the store")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    @Operation(summary = "Get all products",
            description = "Return a list of all available products")
    @ApiResponse(responseCode = "200",
    description = "Successful retrieval of products")
    @ApiResponse(responseCode = "500",
    description = "Internal server error")
    public ResponseEntity<List<Product>>getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID",
            description = "Return a product by its ID if it exists")
    @ApiResponse(responseCode = "200",
            description = "Product found")
    @ApiResponse(responseCode = "404",
    description = "Product not found")
    @ApiResponse(responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<Product> getProduct(
            @Parameter(description = "ID of the product to be retrieved", example = "7", required = true)
            @PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get products by category",
            description = "Return all products by in a specific category")
    @ApiResponse(responseCode = "200",
            description = "Product(s) found in the category")
    @ApiResponse(responseCode = "404",
            description = "Product(s) not found in the category")
    @ApiResponse(responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<List<Product>> getByCategory(
            @Parameter(description = "Category ID", example = "7", required = true)
            int categoryId){
        return productService.getByCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("")
    @Operation(
            summary = "Create a new product",
            description = "Register a new product and return it",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                            value = """
                                    {
                                        "name" : "Helado"
                                        "categoryId" : 5,
                                        "price" : "19.50",
                                        "stock" : 300,
                                        "active" : true 
                                    }
                                    """

                                    )
                    )
            )
    )

    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid product data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Product conflict (duplicate code or SKU)")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Product deleted succesfully")
    @ApiResponse(responseCode = "400", description = "Invalid product ID")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity delete(
            @Parameter(description = "ID of the product to be deleted", example = "7",
                required = true)
            @PathVariable("id") int productId){
        return ResponseEntity.ok(productService.delete(productId));
    }


}
