package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.product.price_finder.application.model.ProductPriceResult;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceView;

enum ProductPriceResultMapper {
    FROM;

    public ProductPriceResult view(ProductPriceView priceView) {
        return ProductPriceResult.builder()
                .brandId(priceView.getBrandId())
                .productId(priceView.getProductId())
                .startAt(priceView.getStartAt())
                .endAt(priceView.getEndAt())
                .productRate(priceView.getProductRate())
                .price(priceView.getPrice())
                .build();
    }
}
