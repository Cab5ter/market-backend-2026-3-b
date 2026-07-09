package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompraProductoPK implements Serializable {

    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof CompraProductoPK that)) {
            return false;
        }

        return Objects.equals(idCompra, that.idCompra)
                && Objects.equals(idProducto, that.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra, idProducto);
    }
}
