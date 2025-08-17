package es.jambo.product_store.product.price_finder.infrastructure.entrypoint.api;

import es.jambo.product_store.utils.product.ProductPriceUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GetProductPriceAPITest {

    @Test
    void should_getPriceAPI_when_mapPriceResult() {
        final var priceResult = ProductPriceUtil.GET.createProductPriceResult();

        final var result = GetProductPriceAPI.FROM.priceFinder(priceResult);

        Assertions.assertThat(result.getBrandId()).isEqualTo(priceResult.getBrandId());
        Assertions.assertThat(result.getProductId()).isEqualTo(priceResult.getProductId());
        Assertions.assertThat(result.getPrice()).isEqualTo(priceResult.getPrice());
        Assertions.assertThat(result.getProductRate()).isEqualTo(priceResult.getProductRate());
        Assertions.assertThat(result.getStartAt()).isEqualTo(priceResult.getStartAt());
        Assertions.assertThat(result.getEndAt()).isEqualTo(priceResult.getEndAt());
    }

}