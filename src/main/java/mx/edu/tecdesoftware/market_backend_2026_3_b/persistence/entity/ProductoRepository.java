package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.entities.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getAll(){
        //Se "castea" iterable a listaa

        return (List<Producto>) productoCrudRepository.findAll();
    }

    //Obtener productos por caategoria
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByCantidadOrderByNombreAsc(idCategoria);
    }
    //Obtener productos escasos
    public Optional<List<Producto>> getEscasos (int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }
    //Obtener un producto dado el ID
    public Optional<Producto> getProductoById(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    //Guardar un producto
    public Producto addProducto(Producto producto){
        return productoCrudRepository.save(producto);
    }

    //Eliminar un producto por ID
    public void deleteProductoById(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }

}
