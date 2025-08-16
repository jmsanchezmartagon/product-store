package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.product.price_finder.application.projection.ProductPriceView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

class ProductPriceDTOMapperTest {

    @Test
    void should_getDTO_when_mappingView() {
        final var price = getProductPriceView();

        final var result = ProductPriceDTOMapper.FROM.view(price);

        Assertions.assertThat(result.getBrandId()).isEqualTo(result.getBrandId());
        Assertions.assertThat(result.getProductId()).isEqualTo(result.getProductId());
        Assertions.assertThat(result.getProductRate()).isEqualTo(result.getProductRate());
        Assertions.assertThat(result.getPrice()).isEqualTo(result.getPrice());
        Assertions.assertThat(result.getStartAt()).isEqualTo(result.getStartAt());
        Assertions.assertThat(result.getEndAt()).isEqualTo(result.getEndAt());
    }

    private ProductPriceView getProductPriceView() {
        final var view = new ProductPriceView();
        view.setBrandId(UUID.randomUUID().toString());
        view.setProductId(UUID.randomUUID().toString());
        view.setStartAt(LocalDateTime.now().minusDays(10));
        view.setEndAt(LocalDateTime.now());
        view.setProductRate(UUID.randomUUID().toString());
        view.setPriority(1);
        view.setPrice(25.1D);
        return view;
    }
}