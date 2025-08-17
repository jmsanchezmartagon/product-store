package es.jambo.product_store.utils.product;

import es.jambo.product_store.product.price_finder.application.model.ProductPriceResult;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceView;
import es.jambo.product_store.product.price_finder.infrastructure.persistence.model.ProductPricePO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;

public enum ProductPriceUtil {
    GET;

    public static final String BRAND_ID = UUID.randomUUID().toString();
    public static final String PRODUCT_ID = UUID.randomUUID().toString();
    public static final int PRIORITY = 0;


    public ProductPriceResult createProductPriceResult() {
        return ProductPriceResult.builder()
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .price(30.45D)
                .startAt(LocalDateTime.now().minusMonths(1))
                .endAt(LocalDateTime.now().plusMonths(3))
                .productRate(UUID.randomUUID().toString())
                .build();
    }

    public ProductPriceView createProductPriceViewWithRandomRate(LocalDateTime startAt, LocalDateTime endAt, double price) {
        return createProductPriceView(startAt, endAt, UUID.randomUUID().toString(), PRIORITY, price);
    }


    public ProductPriceView getProductPriceView() {
        return createProductPriceView(LocalDateTime.now().minusMonths(5), LocalDateTime.now().plusMonths(8), UUID.randomUUID().toString(), PRIORITY, 25.1D);
    }

    public ProductPriceView createProductPriceView(LocalDateTime startAt, LocalDateTime endAt, String rate, int priority, double price) {
        final var priceView = new ProductPriceView();
        priceView.setProductId(PRODUCT_ID);
        priceView.setBrandId(BRAND_ID);
        priceView.setPrice(price);
        priceView.setProductRate(rate);
        priceView.setPriority(priority);
        priceView.setStartAt(startAt);
        priceView.setEndAt(endAt);
        return priceView;
    }

    public ProductPricePO createRandomPO() {
        final var price = new ProductPricePO();
        price.setBrandId(UUID.randomUUID().toString());
        price.setProductId(UUID.randomUUID().toString());
        price.setStartAt(LocalDateTime.now().minusMonths(1).truncatedTo(ChronoUnit.SECONDS));
        price.setEndAt(LocalDateTime.now().plusDays(1).truncatedTo(ChronoUnit.SECONDS));
        price.setProductRate(UUID.randomUUID().toString());
        price.setPriority(PRIORITY);
        price.setPrice(createRandomPrice());
        price.setCurr("EUR");
        return price;
    }


    public ProductPricePO createProductPO(String productId, LocalDateTime startAt, LocalDateTime endAt) {
        final var price = new ProductPricePO();
        price.setBrandId(BRAND_ID);
        price.setProductId(productId);
        price.setStartAt(startAt);
        price.setEndAt(endAt);
        price.setProductRate(UUID.randomUUID().toString());
        price.setPriority(PRIORITY);
        price.setPrice(createRandomPrice());
        price.setCurr("EUR");

        return price;
    }

    private double createRandomPrice() {
        double randomDouble = 1 + new Random().nextDouble() * (30 - 1);
        return BigDecimal.valueOf(randomDouble)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
