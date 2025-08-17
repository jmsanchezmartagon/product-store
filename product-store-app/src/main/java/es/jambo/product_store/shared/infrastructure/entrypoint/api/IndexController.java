package es.jambo.product_store.shared.infrastructure.entrypoint.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class IndexController {

    @GetMapping("/")
    String index() {
        return "Product Services";
    }
}
