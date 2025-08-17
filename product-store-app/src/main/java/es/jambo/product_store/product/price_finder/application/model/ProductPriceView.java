package es.jambo.product_store.product.price_finder.application.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class ProductPriceView {
    private String brandId;
    private String productId;
    private String productRate;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Integer priority;
    private Double price;
}
