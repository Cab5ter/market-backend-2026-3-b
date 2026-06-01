package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.entities;

import jakarta.persistence.*;


@Entity
@Table (name = "productos")
public class Producto {

    @Id
    //Autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    private String nombre;

    @Column(name = "id_categoria")
    private String idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    //Relacion con categoria
    //Muchos productos pueden pertenecer a una categoria
    @ManyToOne
    @JoinColumn(name = "id_categoria",
    //No quiero que se edite nada
    insertable = false, updatable = false)

    private Categoria catergoria;


}
