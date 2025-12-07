package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.product.price_finder.application.model.PriceFinderQuery;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceResult;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceView;
import es.jambo.product_store.product.price_finder.application.projection.ProductPriceProjection;
import es.jambo.product_store.utils.product.ProductPriceUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.BDDMockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ProductPriceFinderBehaviorTest {

    @InjectMocks
    private ProductPriceFinderImpl productPriceFinder;
    @Mock
    private ProductPriceProjection productPriceProjection;

    @Test
    void should_returnHigherPrice_when_multiplePricesExistWithSamePriority() {
        // GIVEN
        final var query = givenAValidQuery();
        final var lowerPrice = givenAPriceWith(10.0, 1);
        final var higherPrice = givenAPriceWith(20.0, 1);

        // Simulating DB return order (ASCENDING by PRICE as per our fix)
        givenTheProjectionReturns(query, List.of(lowerPrice, higherPrice));

        // WHEN
        final var result = productPriceFinder.getPrice(query);

        // THEN
        thenThePriceIs(result, 20.0);
    }

    @Test
    void should_returnHighestPriority_when_multiplePrioritiesExist() {
        // GIVEN
        final var query = givenAValidQuery();
        final var lowPriorityPrice = givenAPriceWith(50.0, 0);
        final var highPriorityPrice = givenAPriceWith(20.0, 1);

        // Simulating DB return order (ASCENDING by PRIORITY)
        givenTheProjectionReturns(query, List.of(lowPriorityPrice, highPriorityPrice));

        // WHEN
        final var result = productPriceFinder.getPrice(query);

        // THEN
        thenThePriceIs(result, 20.0);
    }

    private PriceFinderQuery givenAValidQuery() {
        return PriceFinderQuery.builder()
                .brandId(UUID.randomUUID().toString())
                .productId(UUID.randomUUID().toString())
                .priceDate(LocalDateTime.now())
                .build();
    }

    private ProductPriceView givenAPriceWith(double price, int priority) {
        return ProductPriceUtil.GET.createProductPriceView(
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1),
                "RATE-" + price,
                priority,
                price);
    }

    private void givenTheProjectionReturns(PriceFinderQuery query, List<ProductPriceView> prices) {
        BDDMockito.given(productPriceProjection.getPrices(query)).willReturn(prices);
    }

    private void thenThePriceIs(ProductPriceResult result, double expectedPrice) {
        Assertions.assertThat(result.getPrice()).isEqualTo(expectedPrice);
    }
}
