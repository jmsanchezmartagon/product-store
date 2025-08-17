package es.jambo.product_store.product.price_finder.infrastructure.entrypoint.api;

import es.jambo.product_store.infrastructure.entrypoint.api.models.ProductPrice;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceResult;

enum GetProductPriceAPI {
    FROM;

    ProductPrice priceFinder(ProductPriceResult priceModel) {
        final var price = new ProductPrice();
        price.setBrandId(priceModel.getBrandId());
        price.setProductId(priceModel.getProductId());
        price.setPrice(priceModel.getPrice());
        price.setProductRate(priceModel.getProductRate());
        price.setStartAt(priceModel.getStartAt());
        price.setEndAt(priceModel.getEndAt());
        return price;
    }
}
