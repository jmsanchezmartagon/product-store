package es.jambo.product_store.product.price_finder.infrastructure.persistence.repository;

import es.jambo.product_store.product.price_finder.application.model.PriceFinderQuery;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceView;
import es.jambo.product_store.product.price_finder.application.projection.ProductPriceProjection;
import es.jambo.product_store.product.price_finder.infrastructure.persistence.dao.ProductPriceDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
final class ProductPriceProjectionImpl implements ProductPriceProjection {

    private final ProductPriceDAO dao;

    ProductPriceProjectionImpl(ProductPriceDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<ProductPriceView> getPrices(PriceFinderQuery query) {
        return dao.find(query.getBrandId(), query.getProductId(), query.getPriceDate())
                .stream().map(ProductPriceViewMapper.FROM::persistenceObject).toList();
    }
}
