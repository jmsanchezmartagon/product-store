package es.jambo.product_store.config.shared.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@SpringBootTest
class MessageServiceImplTest {


    @Autowired
    private MessageService messageService;

    @Test
    void should_AssuredAllMessage() throws IllegalArgumentException, IllegalAccessException {

        for (Field field : KeyMessageSource.class.getDeclaredFields()) {

            String key = (String) field.get(KeyMessageSource.class);
            Assertions.assertThat(messageService.getMessage(key)).isNotBlank().isNotEqualTo(key);
        }
    }

    @Test
    void should_AssuredNotRepeat() throws IllegalArgumentException, IllegalAccessException {
        final Set<String> storeKey = new TreeSet<>();

        for (Field field : KeyMessageSource.class.getDeclaredFields()) {

            String key = (String) field.get(KeyMessageSource.class);
            Assertions.assertThat(storeKey).doesNotContain(key);
            storeKey.add(key);
        }
    }

    @Test
    void should_return_literal_when_locale_exist() {
        final var key = "es.jambo.product_store.shared.uuid.invalid_value";

        final var result = messageService.getMessage(key);

        Assertions.assertThat(result).isNotEqualTo(key).isEqualTo("UUID no válido");
    }
}