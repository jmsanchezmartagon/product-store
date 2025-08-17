package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.product.price_finder.application.model.PriceFinderQuery;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceResult;

public interface ProductPriceFinder {
    ProductPriceResult getPrice(PriceFinderQuery priceFinderQuery);
}
