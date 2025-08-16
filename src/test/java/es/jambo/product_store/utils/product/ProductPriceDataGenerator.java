package es.jambo.product_store.utils.product;

import es.jambo.product_store.product.price_finder.infrastructure.persistence.model.ProductPricePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public final class ProductPriceDataGenerator {

    private final String INSERT_PRODUCT_PRICE = """
                INSERT INTO PRODUCT_PRICES (
                    brand_id, product_id, start_at, end_at, product_rate, priority, price, curr
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private final String DELETE_PRODUCT_PRICE = "DELETE FROM PRODUCT_PRICES";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ProductPricePO> createOverlapPrices() {
        final var productId = UUID.randomUUID().toString();
        final var prices = List.of(ProductPriceUtil.GET.createProductPO(productId, LocalDateTime.now().minusDays(2), LocalDateTime.now().plusMonths(3)),
                ProductPriceUtil.GET.createProductPO(productId, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusMonths(2))
        );
        prices.forEach(this::insertPO);
        return prices;
    }

    public ProductPricePO createRandom() {
        final var productPrice = ProductPriceUtil.GET.createRandomPO();
        insertPO(productPrice);
        return productPrice;
    }

    public void insertPO(ProductPricePO price) {

        jdbcTemplate.update(INSERT_PRODUCT_PRICE,
                price.getBrandId(),
                price.getProductId(),
                price.getStartAt(),
                price.getEndAt(),
                price.getProductRate(),
                price.getPriority(),
                price.getPrice(),
                price.getCurr()
        );
    }

    public void cleanUp() {
        jdbcTemplate.update(DELETE_PRODUCT_PRICE);
    }
}
