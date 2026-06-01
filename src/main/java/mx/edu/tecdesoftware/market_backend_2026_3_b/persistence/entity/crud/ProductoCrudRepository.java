package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
}
