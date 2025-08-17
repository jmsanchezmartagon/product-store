package es.jambo.product_store.shared.application.model;

import es.jambo.product_store.config.shared.error.ApplicationException;
import es.jambo.product_store.config.shared.message.KeyMessageSource;

import java.util.regex.Pattern;

public record UuId(String value) {

    private static final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private static final Pattern UUID_PATTERN = Pattern.compile(UUID_REGEX);

    public UuId {
        if (isNotValid(value)) {
            throw new ApplicationException(KeyMessageSource.APPLICATION_SHARED_UUID_INVALID_VALUE);
        }
    }

    public static boolean isNotValid(String value) {
        return value == null || !UUID_PATTERN.matcher(value).matches();
    }
}
