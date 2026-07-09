package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.web.controller;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("clientId") Integer clientId) {
        return purchaseService.getByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.save(purchase));
    }
}