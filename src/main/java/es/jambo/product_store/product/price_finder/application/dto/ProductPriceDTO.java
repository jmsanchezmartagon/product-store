package es.jambo.product_store.product.price_finder.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class ProductPriceDTO {
    private String brandId;
    private String productId;
    private String productRate;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Double price;
}
