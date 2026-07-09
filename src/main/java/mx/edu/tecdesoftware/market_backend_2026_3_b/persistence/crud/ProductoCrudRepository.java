package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Obtener la lista de productos filtrados
    //id de ctegoria y ordenados ascedentemente por nombre
    /*
        SELECT *
        FROM Categorias
        WHERE id_categoria = ?
        ORDER BY Nombre ASC
     */
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Obtener los productos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado (int cantidad, boolean estado);

}
