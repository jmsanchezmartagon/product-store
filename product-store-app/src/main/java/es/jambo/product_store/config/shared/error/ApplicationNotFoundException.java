package es.jambo.product_store.config.shared.error;


import es.jambo.product_store.config.shared.message.KeyMessageSource;

public final class ApplicationNotFoundException extends ApplicationException {
    public ApplicationNotFoundException() {
        super(KeyMessageSource.APPLICATION_SHARED_RESOURCE_NOT_FOUND);
    }
}