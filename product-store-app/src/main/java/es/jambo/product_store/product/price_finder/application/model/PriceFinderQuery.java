package es.jambo.product_store.product.price_finder.application.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class PriceFinderQuery {
    private String brandId;
    private String productId;
    private LocalDateTime priceDate;
}
