package es.jambo.product_store.config.shared.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("messageService")
@Slf4j
final class MessageServiceImpl implements MessageService {

    private static final String[] VALUES_EMPTY = new String[]{};
    private static final Locale LOCALE_DEFAULT = Locale.forLanguageTag("es-ES");

    private final MessageSource messagesSource;

    MessageServiceImpl(ResourceBundleMessageSource messageSource) {
        this.messagesSource = messageSource;
    }

    @Override
    public String getMessage(String key) {
        return messagesSource.getMessage(key, VALUES_EMPTY, key, LOCALE_DEFAULT);
    }
}