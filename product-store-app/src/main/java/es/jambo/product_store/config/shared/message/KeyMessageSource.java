package es.jambo.product_store.config.shared.message;

public final class KeyMessageSource {

    private KeyMessageSource() {
        throw new IllegalCallerException();
    }

    public static final String APPLICATION_SHARED_UUID_INVALID_VALUE = "es.jambo.product_store.shared.uuid.invalid_value";
    public static final String APPLICATION_SHARED_RESOURCE_NOT_FOUND = "es.jambo.product_store.shared.resource.not_found";
    public static final String APPLICATION_PRODUCT_PRICE_BRAND_IS_REQUIRED = "es.jambo.product_store.product_price.brand_is_required";
    public static final String APPLICATION_PRODUCT_PRICE_BRAND_IS_NOT_VALID = "es.jambo.product_store.product_price.brand_is_not_valid";
    public static final String APPLICATION_PRODUCT_PRICE_PRODUCT_IS_REQUIRED = "es.jambo.product_store.product_price.product_is_required";
    public static final String APPLICATION_PRODUCT_PRICE_PRODUCT_IS_NOT_VALID = "es.jambo.product_store.product_price.product_is_not_valid";
    public static final String APPLICATION_PRODUCT_PRICE_DATE_IS_REQUIRED = "es.jambo.product_store.product_price.date_is_required";

}
