package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.utils.product.ProductPriceUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductPriceResultMapperTest {

    @Test
    void should_getPrice_when_mappingView() {
        final var view = ProductPriceUtil.GET.getProductPriceView();

        final var result = ProductPriceResultMapper.FROM.view(view);

        Assertions.assertThat(result.getBrandId()).isEqualTo(view.getBrandId());
        Assertions.assertThat(result.getProductId()).isEqualTo(view.getProductId());
        Assertions.assertThat(result.getProductRate()).isEqualTo(view.getProductRate());
        Assertions.assertThat(result.getPrice()).isEqualTo(view.getPrice());
        Assertions.assertThat(result.getStartAt()).isEqualTo(view.getStartAt());
        Assertions.assertThat(result.getEndAt()).isEqualTo(view.getEndAt());
    }

}