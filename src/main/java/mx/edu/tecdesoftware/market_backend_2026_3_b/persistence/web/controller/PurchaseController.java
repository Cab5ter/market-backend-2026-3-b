package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase", description = "Manage purchase in the store")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("")
    @Operation(summary = "Get all purchase",
            description = "Return a list of all available purchase")
    @ApiResponse(responseCode = "200",
            description = "Successful retrieval of purchase")
    @ApiResponse(responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get purchase by client",
            description = "Return all purchase by in a specific clientId")
    @ApiResponse(responseCode = "200",
            description = "Purchase(s) found in the clientId")
    @ApiResponse(responseCode = "404",
            description = "Purchase(s) not found in the clientId")
    @ApiResponse(responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<List<Purchase>> getByClient(
            @Parameter(description = "Id of the client", example = "1")
            @PathVariable("clientId") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "purchase created successfuly"),
            @ApiResponse(responseCode = "400", description = "Invalid purchase data")
    })
    public ResponseEntity<Purchase> save(
            @RequestBody(content = @Content(examples = @ExampleObject(
                    value = """
                    {
                      "clientId": "1",
                      "date": "2026-07-14T10:00:00",
                      "paymentMethod": "E",
                      "comment": "Compra de ejemplo",
                      "status": "P",
                      "items": [
                        { "productId": 1, "quantity": 2, "total": 600.00, "active": true }
                      ]
                    }
                    """)))
            @org.springframework.web.bind.annotation.RequestBody Purchase purchase) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(purchase));
    }

    @DeleteMapping("/{purchaseId}")
    @ApiResponse(responseCode = "201", description = "Purchase deleted succesfully")
    @ApiResponse(responseCode = "400", description = "Invalid purchase ID")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "409", description = "Purchase not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "204", description = "Delete successfully")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Id of the purchase", example = "1")
            @PathVariable Integer purchaseId) {
        if (!purchaseService.delete(purchaseId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
