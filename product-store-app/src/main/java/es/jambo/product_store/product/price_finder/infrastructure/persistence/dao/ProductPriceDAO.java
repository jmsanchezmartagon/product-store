package es.jambo.product_store.product.price_finder.infrastructure.persistence.dao;

import es.jambo.product_store.product.price_finder.infrastructure.persistence.model.ProductPricePO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ProductPriceDAO {
    List<ProductPricePO> find(String brandId, String productId, LocalDateTime date);
}
