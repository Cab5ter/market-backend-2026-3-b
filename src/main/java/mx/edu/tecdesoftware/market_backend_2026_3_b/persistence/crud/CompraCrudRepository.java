package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    List<Compra> findByIdCliente(Integer idCliente);
}