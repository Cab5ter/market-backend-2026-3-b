package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategaria;

    private String descripcion;
    private Boolean estado;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Integer getIdCategaria() {
        return idCategaria;
    }

    public void setIdCategaria(Integer idCategaria) {
        this.idCategaria = idCategaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria(){

    }
}
