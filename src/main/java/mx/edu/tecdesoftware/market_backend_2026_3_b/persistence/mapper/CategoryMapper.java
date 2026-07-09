package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.Category;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.Categoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper{
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);


    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
