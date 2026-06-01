package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.entities.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getAll(){
        //Se "castea" iterable a listaa

        return (List<Producto>) productoCrudRepository.findAll();
    }

}
