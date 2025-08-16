package es.jambo.product_store.shared;

import es.jambo.product_store.config.shared.error.ApplicationException;
import es.jambo.product_store.config.shared.message.KeyMessageSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

class UuIdTest {

    @Test
    void should_getObject_when_valueIsUUID() {
        var uuid = UUID.randomUUID().toString();

        final var entity = new UuId(uuid);

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.value()).isEqualTo(uuid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void should_fail_when_valueIsBlank(String value) {
        Throwable expectedThrow = Assertions.catchThrowable(() -> new UuId(value));

        Assertions.assertThat(expectedThrow).isInstanceOf(ApplicationException.class)
                .hasMessage(KeyMessageSource.APPLICATION_SHARED_UUID_INVALID_VALUE);

    }


    @Test
    void should_fail_when_valueIsNull() {
        Throwable expectedThrow = Assertions.catchThrowable(() -> new UuId(null));

        Assertions.assertThat(expectedThrow).isInstanceOf(ApplicationException.class)
                .hasMessage(KeyMessageSource.APPLICATION_SHARED_UUID_INVALID_VALUE);

    }

    @Test
    void should_beValid_when_isUUID() {
        Assertions.assertThat(UuId.isNotValid(UUID.randomUUID().toString())).isFalse();
    }

    @Test
    void should_beNOTValid_when_isNOTUID() {
        final var badUUID = "añ51e495-e83f-4c81-beee-e1030c799711";
        Assertions.assertThat(UuId.isNotValid(badUUID)).isTrue();
    }
}