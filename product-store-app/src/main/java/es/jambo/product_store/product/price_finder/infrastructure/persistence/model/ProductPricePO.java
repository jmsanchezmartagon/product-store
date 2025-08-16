package es.jambo.product_store.product.price_finder.infrastructure.persistence.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class ProductPricePO {
    private String brandId;
    private String productId;
    private String productRate;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Double price;
    private Integer priority;
    private String curr;
}
