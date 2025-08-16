package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.product.price_finder.application.dto.ProductPriceDTO;
import es.jambo.product_store.product.price_finder.application.projection.ProductPriceView;

enum ProductPriceDTOMapper {
    FROM;

    public ProductPriceDTO view(ProductPriceView priceView) {
        return ProductPriceDTO.builder()
                .brandId(priceView.getBrandId())
                .productId(priceView.getProductId())
                .startAt(priceView.getStartAt())
                .endAt(priceView.getEndAt())
                .productRate(priceView.getProductRate())
                .price(priceView.getPrice())
                .build();
    }
}
