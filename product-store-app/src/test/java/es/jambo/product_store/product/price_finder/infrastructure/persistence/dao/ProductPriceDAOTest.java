package es.jambo.product_store.product.price_finder.infrastructure.persistence.dao;

import es.jambo.product_store.product.price_finder.infrastructure.persistence.model.ProductPricePO;
import es.jambo.product_store.utils.product.ProductPriceDataGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ProductPriceDAOTest {

    @Autowired
    private ProductPriceDAO productPriceDAO;

    @Autowired
    private ProductPriceDataGenerator productPriceDataGenerator;

    @Test
    void should_getPrices_when_searchByBrandProductAndDate() {
        final var prices = productPriceDataGenerator.createOverlapPrices();
        productPriceDataGenerator.createRandom();
        final var targetProduct = prices.stream().findAny().get();
        final var appDate = LocalDateTime.now();

        final var result = productPriceDAO.find(targetProduct.getBrandId(), targetProduct.getProductId(), appDate);

        Assertions.assertThat(result).size().isEqualTo(2);
        result.forEach(p -> {
            Assertions.assertThat(p.getBrandId()).isEqualTo(targetProduct.getBrandId());
            Assertions.assertThat(p.getProductId()).isEqualTo(targetProduct.getProductId());
            Assertions.assertThat(p.getStartAt()).isBeforeOrEqualTo(appDate);
            Assertions.assertThat(p.getEndAt()).isAfterOrEqualTo(appDate);
        });
    }

    @Test
    void should_getPricesInfo_when_findProductPricesByStartDate() {
        final var expectedPrice = productPriceDataGenerator.createRandom();

        final var result = productPriceDAO.find(expectedPrice.getBrandId(), expectedPrice.getProductId(),
                expectedPrice.getStartAt());

        assertExpectedPrice(result, expectedPrice);
    }

    @Test
    void should_getPricesInfo_when_findProductPricesByEndDate() {
        final var expectedPrice = productPriceDataGenerator.createRandom();

        final var result = productPriceDAO.find(expectedPrice.getBrandId(), expectedPrice.getProductId(),
                expectedPrice.getEndAt());

        assertExpectedPrice(result, expectedPrice);
    }

    @Test
    void should_returnPricesSortedByPriorityAndId_when_multipleMatchesExist() {

        final var now = LocalDateTime.now();
        final var brandId = java.util.UUID.randomUUID().toString();
        final var productId = java.util.UUID.randomUUID().toString();

        // Priority 0, Price 10 (Lowest priority)
        final var p1 = es.jambo.product_store.utils.product.ProductPriceUtil.GET.createProductPO(
                "RATE-1", now.minusHours(1), now.plusHours(1), 0, 10.0);
        p1.setBrandId(brandId);
        p1.setProductId(productId);

        // Priority 1, Price 5.0 (Higher priority than p1)
        final var p2 = es.jambo.product_store.utils.product.ProductPriceUtil.GET.createProductPO(
                "RATE-2", now.minusHours(1), now.plusHours(1), 1, 5.0);
        p2.setBrandId(brandId);
        p2.setProductId(productId);

        // Priority 1, Price 20.0 (Same priority as p2, but higher price)
        final var p3 = es.jambo.product_store.utils.product.ProductPriceUtil.GET.createProductPO(
                "RATE-3", now.minusHours(1), now.plusHours(1), 1, 20.0);
        p3.setBrandId(brandId);
        p3.setProductId(productId);

        productPriceDataGenerator.insertPO(p3); // Insert in random order
        productPriceDataGenerator.insertPO(p1);
        productPriceDataGenerator.insertPO(p2);

        final var result = productPriceDAO.find(brandId, productId, now);

        Assertions.assertThat(result).hasSize(3);

        // Expected order: Priority ASC, then Price ASC.
        Assertions.assertThat(result.get(0).getProductRate()).isEqualTo("RATE-1");
        Assertions.assertThat(result.get(1).getProductRate()).isEqualTo("RATE-2");
        Assertions.assertThat(result.get(2).getProductRate()).isEqualTo("RATE-3");
    }

    private void assertExpectedPrice(List<ProductPricePO> result, ProductPricePO expectedPrice) {
        Assertions.assertThat(result).size().isEqualTo(1);
        result.forEach(entity -> {
            Assertions.assertThat(entity.getBrandId()).isEqualTo(expectedPrice.getBrandId());
            Assertions.assertThat(entity.getProductId()).isEqualTo(expectedPrice.getProductId());
            Assertions.assertThat(entity.getStartAt()).isEqualTo(expectedPrice.getStartAt());
            Assertions.assertThat(entity.getEndAt()).isEqualTo(expectedPrice.getEndAt());
            Assertions.assertThat(entity.getPrice()).isEqualTo(expectedPrice.getPrice());
            Assertions.assertThat(entity.getProductRate()).isEqualTo(expectedPrice.getProductRate());
            Assertions.assertThat(entity.getCurr()).isEqualTo(expectedPrice.getCurr());
            Assertions.assertThat(entity.getPriority()).isEqualTo(expectedPrice.getPriority());
        });
    }

    @AfterEach
    void cleanUp() {
        productPriceDataGenerator.cleanUp();
    }
}