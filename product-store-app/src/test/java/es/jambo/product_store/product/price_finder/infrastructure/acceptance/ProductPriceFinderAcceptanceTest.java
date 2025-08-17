package es.jambo.product_store.product.price_finder.infrastructure.acceptance;

import es.jambo.product_store.product.price_finder.application.ProductPriceFinder;
import es.jambo.product_store.product.price_finder.application.model.PriceFinderQuery;
import es.jambo.product_store.utils.product.ProductPriceDataGenerator;
import es.jambo.product_store.utils.product.ProductPriceUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class ProductPriceFinderAcceptanceTest {

    private static final String RATE_LINE_ONE = UUID.randomUUID().toString();
    private static final String RATE_LINE_TWO = UUID.randomUUID().toString();
    private static final String RATE_LINE_THREE = UUID.randomUUID().toString();
    private static final String RATE_LINE_FOUR = UUID.randomUUID().toString();

    @Autowired
    private ProductPriceFinder productPriceFinder;
    @Autowired
    private ProductPriceDataGenerator productPriceDataGenerator;

    @Test
    void should_getRateOne_when_request14thDay10H() {
        final var query = createQueryAt(14, 10);
        final var expectedPrice = 35.50D;
        prepareData();

        final var result = productPriceFinder.getPrice(query);

        Assertions.assertThat(result.getProductRate()).isEqualTo(RATE_LINE_ONE);
        Assertions.assertThat(result.getPrice()).isEqualTo(expectedPrice);
    }

    @Test
    void should_getRateTwo_when_request14thDay16H() {
        final var query = createQueryAt(14, 16);
        final var expectedPrice = 25.45D;
        prepareData();

        final var result = productPriceFinder.getPrice(query);

        Assertions.assertThat(result.getProductRate()).isEqualTo(RATE_LINE_TWO);
        Assertions.assertThat(result.getPrice()).isEqualTo(expectedPrice);
    }


    @Test
    void should_getRateOne_when_request14thDay21H() {
        final var query = createQueryAt(14, 21);
        final var expectedPrice = 35.50D;
        prepareData();

        final var result = productPriceFinder.getPrice(query);

        Assertions.assertThat(result.getProductRate()).isEqualTo(RATE_LINE_ONE);
        Assertions.assertThat(result.getPrice()).isEqualTo(expectedPrice);
    }

    @Test
    void should_getRateThree_when_request15thDay10H() {
        final var query = createQueryAt(15, 10);
        final var expectedPrice = 30.50D;
        prepareData();

        final var result = productPriceFinder.getPrice(query);

        Assertions.assertThat(result.getProductRate()).isEqualTo(RATE_LINE_THREE);
        Assertions.assertThat(result.getPrice()).isEqualTo(expectedPrice);
    }

    @Test
    void should_getRateFour_when_request16thDay21H() {
        final var query = createQueryAt(16, 21);
        final var expectedPrice = 38.95;
        prepareData();

        final var result = productPriceFinder.getPrice(query);

        Assertions.assertThat(result.getProductRate()).isEqualTo(RATE_LINE_FOUR);
        Assertions.assertThat(result.getPrice()).isEqualTo(expectedPrice);
    }

    @AfterEach
    void cleanUp() {
        productPriceDataGenerator.cleanUp();
    }

    private PriceFinderQuery createQueryAt(int dayOfMonth, int hour) {
        return PriceFinderQuery.builder().brandId(ProductPriceUtil.BRAND_ID).productId(ProductPriceUtil.PRODUCT_ID)
                .priceDate(LocalDateTime.of(2020, 6, dayOfMonth, hour, 0, 0)).build();
    }

    private void prepareData() {
        productPriceDataGenerator.insertPO(ProductPriceUtil.GET.createProductPO(RATE_LINE_ONE,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                0,
                35.50
        ));

        productPriceDataGenerator.insertPO(ProductPriceUtil.GET.createProductPO(RATE_LINE_TWO,
                LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30, 59),
                1,
                25.45
        ));

        productPriceDataGenerator.insertPO(ProductPriceUtil.GET.createProductPO(RATE_LINE_THREE,
                LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                LocalDateTime.of(2020, 12, 15, 11, 0, 0),
                1,
                30.50
        ));

        productPriceDataGenerator.insertPO(ProductPriceUtil.GET.createProductPO(RATE_LINE_FOUR,
                LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1,
                38.95
        ));
    }


}
