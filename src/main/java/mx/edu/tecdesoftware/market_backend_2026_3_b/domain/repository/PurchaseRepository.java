package mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<Purchase> getAll();

    Optional<List<Purchase>> getByClient(String clientId);

    Optional<Purchase> getPurchase(Integer purchaseId);

    boolean clientExists(String clientId);

    boolean productExists(Integer productId);

    Purchase save(Purchase purchase);

    void delete(Integer purchaseId);
}
