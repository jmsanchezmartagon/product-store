package es.jambo.product_store.product.price_finder.infrastructure.entrypoint.api;

import es.jambo.product_store.infrastructure.entrypoint.api.controllers.BrandApi;
import es.jambo.product_store.infrastructure.entrypoint.api.models.ProductPrice;
import es.jambo.product_store.product.price_finder.application.ProductPriceFinder;
import es.jambo.product_store.product.price_finder.application.model.PriceFinderQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
class BrandProductPriceController implements BrandApi {

    private final ProductPriceFinder productPriceFinder;

    BrandProductPriceController(ProductPriceFinder productPriceFinder) {
        this.productPriceFinder = productPriceFinder;
    }

    @Override
    public ResponseEntity<ProductPrice> brandBrandIdProductProductIdPriceGet(String brandId, String productId, LocalDateTime datetime) {
        final var query = PriceFinderQuery.builder().brandId(brandId).productId(productId).priceDate(datetime).build();
        final var price = GetProductPriceAPI.FROM.priceFinder(productPriceFinder.getPrice(query));
        return ResponseEntity.of(Optional.of(price));
    }

}
