package es.jambo.product_store.product.price_finder.application.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class ProductPriceResult {
    private String brandId;
    private String productId;
    private String productRate;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Double price;
}
