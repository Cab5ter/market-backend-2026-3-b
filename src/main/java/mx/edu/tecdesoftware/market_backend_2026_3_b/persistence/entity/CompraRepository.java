package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ClienteCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        List<Compra> compras = compraCrudRepository.findByIdCliente(clientId);

        if (compras.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(purchaseMapper.toPurchases(compras));
    }

    @Override
    public Optional<Purchase> getPurchase(Integer purchaseId) {
        return compraCrudRepository.findById(purchaseId)
                .map(purchaseMapper::toPurchase);
    }

    @Override
    public boolean clientExists(String clientId) {
        return clienteCrudRepository.existsById(clientId);
    }

    @Override
    public boolean productExists(Integer productId) {
        return productoCrudRepository.existsById(productId);
    }

    @Override
    public Purchase save(Purchase purchase) {
        // POST always creates a new purchase; PostgreSQL assigns its identity.
        purchase.setPurchaseId(null);
        Compra compra = purchaseMapper.toCompra(purchase);

        if (compra.getProductos() != null) {
            for (CompraProducto producto : compra.getProductos()) {
                if (producto.getId() == null) {
                    producto.setId(new CompraProductoPK());
                }

                producto.setCompra(compra);
            }
        }

        return purchaseMapper.toPurchase(compraCrudRepository.save(compra));
    }

    @Override
    public void delete(Integer purchaseId) {
        compraCrudRepository.deleteById(purchaseId);
    }
}
