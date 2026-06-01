package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "compras_productos")
    public class CompraProducto {

    @EmbeddedId
    private CompraProductoPK id;

    //Saber los productos que hay en una compra

    //Unir la tabla de compras
    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;

    //Unir la tabla de productos
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    private  Integer cantidad;
    private  Double total;
    protected Boolean estado;

}
