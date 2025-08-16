package es.jambo.product_store.product.price_finder.application;

import es.jambo.product_store.config.shared.error.ApplicationException;
import es.jambo.product_store.config.shared.error.ApplicationNotFoundException;
import es.jambo.product_store.config.shared.message.KeyMessageSource;
import es.jambo.product_store.product.price_finder.application.dto.ProductPriceDTO;
import es.jambo.product_store.product.price_finder.application.dto.QueryPriceDTO;
import es.jambo.product_store.product.price_finder.application.projection.ProductPriceProjection;
import es.jambo.product_store.shared.UuId;
import org.springframework.stereotype.Service;

@Service
final class ProductPriceFinderImpl implements ProductPriceFinder {

    private final ProductPriceProjection productPriceProjection;

    ProductPriceFinderImpl(ProductPriceProjection productPriceProjection) {
        this.productPriceProjection = productPriceProjection;
    }

    @Override
    public ProductPriceDTO getPrice(QueryPriceDTO query) {
        validateQuery(query);

        return getProductPrice(query);
    }

    private void validateQuery(QueryPriceDTO queryPriceDTO) {
        validateUuid(queryPriceDTO.getBrandId(), KeyMessageSource.APPLICATION_PRODUCT_PRICE_BRAND_IS_REQUIRED,
                KeyMessageSource.APPLICATION_PRODUCT_PRICE_BRAND_IS_NOT_VALID);

        validateUuid(queryPriceDTO.getProductId(), KeyMessageSource.APPLICATION_PRODUCT_PRICE_PRODUCT_IS_REQUIRED,
                KeyMessageSource.APPLICATION_PRODUCT_PRICE_PRODUCT_IS_NOT_VALID);

        validateDate(queryPriceDTO);
    }

    private ProductPriceDTO getProductPrice(QueryPriceDTO query) {
        final var prices = productPriceProjection.getPrices(query);

        return prices.stream().reduce((priceX, priceY) -> priceX.getPriority() > priceY.getPriority() ? priceX : priceY)
                .map(ProductPriceDTOMapper.FROM::view).orElseThrow(ApplicationNotFoundException::new);
    }

    private void validateUuid(String value, String errorKeyRequired, String errorKeyNotValid) {
        if (value == null) {
            throw new ApplicationException(errorKeyRequired);
        }
        if (UuId.isNotValid(value)) {
            throw new ApplicationException(errorKeyNotValid);
        }
    }

    private void validateDate(QueryPriceDTO queryPriceDTO) {
        if (queryPriceDTO.getPriceDate() == null) {
            throw new ApplicationException(KeyMessageSource.APPLICATION_PRODUCT_PRICE_DATE_IS_REQUIRED);
        }
    }
}
