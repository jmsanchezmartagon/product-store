package es.jambo.product_store.product.price_finder.infrastructure.persistence.repository;

import es.jambo.product_store.product.price_finder.application.model.ProductPriceView;
import es.jambo.product_store.product.price_finder.infrastructure.persistence.model.ProductPricePO;

enum ProductPriceViewMapper {
    FROM;

    ProductPriceView persistenceObject(ProductPricePO po) {
        final var view = new ProductPriceView();
        view.setBrandId(po.getBrandId());
        view.setProductId(po.getProductId());
        view.setStartAt(po.getStartAt());
        view.setEndAt(po.getEndAt());
        view.setPrice(po.getPrice());
        view.setPriority(po.getPriority());
        view.setProductRate(po.getProductRate());
        return view;
    }
}
