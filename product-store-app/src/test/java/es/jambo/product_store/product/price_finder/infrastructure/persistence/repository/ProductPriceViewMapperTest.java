package es.jambo.product_store.product.price_finder.infrastructure.persistence.repository;

import es.jambo.product_store.utils.product.ProductPriceUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductPriceViewMapperTest {

    @Test
    void should_getView_when_mapPersistenceObject() {
        final var po = ProductPriceUtil.GET.createRandomPO();

        final var result = ProductPriceViewMapper.FROM.persistenceObject(po);

        Assertions.assertThat(result.getBrandId()).isEqualTo(po.getBrandId());
        Assertions.assertThat(result.getProductId()).isEqualTo(po.getProductId());
        Assertions.assertThat(result.getStartAt()).isEqualTo(po.getStartAt());
        Assertions.assertThat(result.getEndAt()).isEqualTo(po.getEndAt());
        Assertions.assertThat(result.getPrice()).isEqualTo(po.getPrice());
        Assertions.assertThat(result.getProductRate()).isEqualTo(po.getProductRate());
        Assertions.assertThat(result.getPriority()).isEqualTo(po.getPriority());
    }
}