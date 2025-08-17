package es.jambo.product_store.product.price_finder.application.projection;

import es.jambo.product_store.product.price_finder.application.model.PriceFinderQuery;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceView;

import java.util.List;

public interface ProductPriceProjection {
    List<ProductPriceView> getPrices(PriceFinderQuery query);
}
