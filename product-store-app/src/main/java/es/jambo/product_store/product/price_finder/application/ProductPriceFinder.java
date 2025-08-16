package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.product.price_finder.application.dto.ProductPriceDTO;
import es.jambo.product_store.product.price_finder.application.dto.QueryPriceDTO;

public interface ProductPriceFinder {
    ProductPriceDTO getPrice(QueryPriceDTO queryPriceDTO);
}
