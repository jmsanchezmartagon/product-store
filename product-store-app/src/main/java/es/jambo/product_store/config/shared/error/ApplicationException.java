package es.jambo.product_store.config.shared.error;


public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }
}