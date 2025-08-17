package es.jambo.product_store.product.price_finder.infrastructure.entrypoint.api;

import es.jambo.product_store.utils.product.ProductPriceDataGenerator;
import es.jambo.product_store.utils.product.ProductPriceUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class BrandProductPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductPriceDataGenerator productPriceDataGenerator;

    @Test
    void should_getProductId_when_searchByBrandProductAndDateTime() throws Exception {
        final var productPrice = productPriceDataGenerator.createRandom();

        mockMvc.perform(MockMvcRequestBuilders.get("/brand/%s/product/%s/price?datetime=%s".formatted(productPrice.getBrandId(), productPrice.getProductId(), productPrice.getStartAt())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(productPrice.getProductId()));
    }

    @Test
    void should_getNotFound_when_notExists() throws Exception {
        productPriceDataGenerator.createRandom();
        final var productPrice = ProductPriceUtil.GET.createRandomPO();

        mockMvc.perform(MockMvcRequestBuilders.get("/brand/%s/product/%s/price?datetime=%s".formatted(productPrice.getBrandId(), productPrice.getProductId(), productPrice.getStartAt())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @AfterEach
    void cleanUp() {
        productPriceDataGenerator.cleanUp();
    }

}