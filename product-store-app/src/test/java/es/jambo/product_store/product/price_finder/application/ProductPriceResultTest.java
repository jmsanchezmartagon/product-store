package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.config.shared.error.ApplicationException;
import es.jambo.product_store.config.shared.message.KeyMessageSource;
import es.jambo.product_store.product.price_finder.application.model.PriceFinderQuery;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceView;
import es.jambo.product_store.product.price_finder.application.projection.ProductPriceProjection;
import es.jambo.product_store.utils.product.ProductPriceUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ProductPriceResultTest {

    @InjectMocks
    private ProductPriceFinderImpl productPriceFinder;
    @Mock
    private ProductPriceProjection productPriceProjection;

    @Test
    void should_throwError_when_brandIsNull() {
        final var query = PriceFinderQuery.builder().brandId(null).build();

        Assertions.assertThatExceptionOfType(ApplicationException.class).isThrownBy(() -> productPriceFinder.getPrice(query))
                .withMessage(KeyMessageSource.APPLICATION_PRODUCT_PRICE_BRAND_IS_REQUIRED);
    }


    @Test
    void should_throwError_when_brandIsEmpty() {
        final var query = PriceFinderQuery.builder().brandId("").build();

        Assertions.assertThatExceptionOfType(ApplicationException.class).isThrownBy(() -> productPriceFinder.getPrice(query))
                .withMessage(KeyMessageSource.APPLICATION_PRODUCT_PRICE_BRAND_IS_NOT_VALID);
    }

    @Test
    void should_throwError_when_productIsNull() {
        final var query = PriceFinderQuery.builder().brandId(UUID.randomUUID().toString())
                .productId(null).build();

        Assertions.assertThatExceptionOfType(ApplicationException.class).isThrownBy(() -> productPriceFinder.getPrice(query))
                .withMessage(KeyMessageSource.APPLICATION_PRODUCT_PRICE_PRODUCT_IS_REQUIRED);
    }


    @Test
    void should_throwError_when_productIsEmpty() {
        final var query = PriceFinderQuery.builder().brandId(UUID.randomUUID().toString())
                .productId("").build();

        Assertions.assertThatExceptionOfType(ApplicationException.class).isThrownBy(() -> productPriceFinder.getPrice(query))
                .withMessage(KeyMessageSource.APPLICATION_PRODUCT_PRICE_PRODUCT_IS_NOT_VALID);
    }


    @Test
    void should_throwError_when_dateIsNull() {
        final var query = PriceFinderQuery.builder().brandId(UUID.randomUUID().toString())
                .productId(UUID.randomUUID().toString())
                .priceDate(null).build();

        Assertions.assertThatExceptionOfType(ApplicationException.class).isThrownBy(() -> productPriceFinder.getPrice(query))
                .withMessage(KeyMessageSource.APPLICATION_PRODUCT_PRICE_DATE_IS_REQUIRED);
    }

    @Test
    void should_throwError_when_productIsNotFound() {
        final var query = createQueryFilterByNow();

        BDDMockito.given(this.productPriceProjection.getPrices(query)).willReturn(List.of());

        Assertions.assertThatExceptionOfType(ApplicationException.class).isThrownBy(() -> productPriceFinder.getPrice(query));
    }

    @Test
    void should_selectProductWithMayorPriority_when_existsDifferentRates() {
        final var expectedRate = UUID.randomUUID().toString();
        final var expectedPrice = 10.1D;
        final var query = createQueryFilterByNow();
        final var prices = createPrices(expectedRate, expectedPrice);

        BDDMockito.given(this.productPriceProjection.getPrices(query)).willReturn(prices);

        final var result = productPriceFinder.getPrice(query);

        Assertions.assertThat(result.getPrice()).isEqualTo(expectedPrice);
        Assertions.assertThat(result.getProductRate()).isEqualTo(expectedRate);
    }

    private PriceFinderQuery createQueryFilterByNow() {
        return PriceFinderQuery.builder().brandId(UUID.randomUUID().toString())
                .productId(UUID.randomUUID().toString())
                .priceDate(LocalDateTime.now()).build();
    }

    private List<ProductPriceView> createPrices(String expectedRate, Double expectedPrice) {
        return List.of(
                ProductPriceUtil.GET.createProductPriceViewWithRandomRate(LocalDateTime.now().minusDays(2), LocalDateTime.now().plusMonths(1), 40.05D),
                ProductPriceUtil.GET.createProductPriceView(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusMonths(1), expectedRate, ProductPriceUtil.PRIORITY + 1, expectedPrice)
        );
    }

}