package mx.edu.tecdesoftware.market_backend_2026_3_b.domain.service;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        validate(purchase);
        return purchaseRepository.save(purchase);
    }

    private void validate(Purchase purchase) {
        if (purchase.getClientId() == null || purchase.getClientId().isBlank()) {
            throw new ResponseStatusException(BAD_REQUEST, "clientId es obligatorio");
        }
        if (!purchaseRepository.clientExists(purchase.getClientId())) {
            throw new ResponseStatusException(BAD_REQUEST,
                    "No existe el cliente con id " + purchase.getClientId());
        }
        if (purchase.getPaymentMethod() != null && purchase.getPaymentMethod().length() > 1) {
            throw new ResponseStatusException(BAD_REQUEST, "paymentMethod debe tener un solo carácter");
        }
        if (purchase.getStatus() != null && purchase.getStatus().length() > 1) {
            throw new ResponseStatusException(BAD_REQUEST, "status debe tener un solo carácter");
        }
        if (purchase.getItems() == null || purchase.getItems().isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "La compra debe contener al menos un producto");
        }

        for (var item : purchase.getItems()) {
            if (item.getProductId() == null || !purchaseRepository.productExists(item.getProductId())) {
                throw new ResponseStatusException(BAD_REQUEST,
                        "No existe el producto con id " + item.getProductId());
            }
            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                throw new ResponseStatusException(BAD_REQUEST, "La cantidad debe ser mayor que cero");
            }
        }
    }

    public boolean delete(Integer purchaseId) {
        if (purchaseRepository.getPurchase(purchaseId).isEmpty()) {
            return false;
        }

        purchaseRepository.delete(purchaseId);
        return true;
    }
}
