package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.web.controller;

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
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("")
    public ResponseEntity<List<Purchase>> getAll() {
        return ResponseEntity.ok(purchaseService.getAll());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") String clientId) {
        return purchaseService.getByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Compra creada"),
            @ApiResponse(responseCode = "400", description = "Cliente, producto o datos de compra inválidos")
    })
    public ResponseEntity<Purchase> save(
            @RequestBody(content = @Content(examples = @ExampleObject(value = """
                    {
                      "clientId": "4546221",
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
    public ResponseEntity<Void> delete(@PathVariable Integer purchaseId) {
        if (!purchaseService.delete(purchaseId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
