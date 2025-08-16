package es.jambo.product_store.product.price_finder.application.projection;

import es.jambo.product_store.product.price_finder.application.dto.QueryPriceDTO;

import java.util.List;

public interface ProductPriceProjection {
    List<ProductPriceView> getPrices(QueryPriceDTO query);
}
