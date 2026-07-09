package mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.mapper;

import mx.edu.tecdesoftware.market_backend_2026_3_b.domain.PurchaseItem;
import mx.edu.tecdesoftware.market_backend_2026_3_b.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(CompraProducto compraProducto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    CompraProducto toCompraProducto(PurchaseItem purchaseItem);
}