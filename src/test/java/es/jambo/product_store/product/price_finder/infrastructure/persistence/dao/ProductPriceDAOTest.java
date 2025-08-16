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

        final var result = productPriceDAO.find(expectedPrice.getBrandId(), expectedPrice.getProductId(), expectedPrice.getStartAt());

        assertExpectedPrice(result, expectedPrice);
    }


    @Test
    void should_getPricesInfo_when_findProductPricesByEndDate() {
        final var expectedPrice = productPriceDataGenerator.createRandom();

        final var result = productPriceDAO.find(expectedPrice.getBrandId(), expectedPrice.getProductId(), expectedPrice.getEndAt());

        assertExpectedPrice(result, expectedPrice);
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