package es.jambo.product_store.product.price_finder.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public final class QueryPriceDTO {
    private String brandId;
    private String productId;
    private LocalDateTime priceDate;
}
