package es.jambo.product_store.product.price_finder.infrastructure.persistence.repository;

import es.jambo.product_store.product.price_finder.application.model.PriceFinderQuery;
import es.jambo.product_store.product.price_finder.application.model.ProductPriceView;
import es.jambo.product_store.product.price_finder.infrastructure.persistence.dao.ProductPriceDAO;
import es.jambo.product_store.product.price_finder.infrastructure.persistence.model.ProductPricePO;
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
class ProductPriceProjectionImplTest {

    @InjectMocks
    private ProductPriceProjectionImpl productPriceProjection;
    @Mock
    private ProductPriceDAO productPriceDAO;

    @Test
    void should_getPricesView_when_searchByBrandProductAndDate() {
        final var prices = List.of(ProductPriceUtil.GET.createRandomPO(), ProductPriceUtil.GET.createRandomPO());

        final var query = PriceFinderQuery.builder().brandId(UUID.randomUUID().toString())
                .productId(UUID.randomUUID().toString()).priceDate(LocalDateTime.now()).build();

        BDDMockito.given(productPriceDAO.find(query.getBrandId(), query.getProductId(), query.getPriceDate())).willReturn(prices);

        final var result = productPriceProjection.getPrices(query);

        Assertions.assertThat(result).size().isEqualTo(prices.size());

        Assertions.assertThat(result.stream().map(ProductPriceView::getProductId).toList())
                .containsAll(prices.stream().map(ProductPricePO::getProductId).toList());

    }
}