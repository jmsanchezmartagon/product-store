package es.jambo.product_store.config.error;


import es.jambo.product_store.config.shared.error.ApplicationException;
import es.jambo.product_store.config.shared.error.ApplicationNotFoundException;
import es.jambo.product_store.config.shared.message.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class SpringHandleErrors extends ResponseEntityExceptionHandler {

    private static final String PATTERN = "[{}] [{}]";
    private static final Logger LOGGER_WRITER = LoggerFactory.getLogger(SpringHandleErrors.class);

    private final MessageService messageService;

    SpringHandleErrors(MessageService messageService) {
        this.messageService = messageService;
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = {ApplicationException.class})
    protected ResponseEntity<Object> handleDomainAccessError(RuntimeException ex, WebRequest request) {
        final var messageError = messageService.getMessage(ex.getMessage());
        LOGGER_WRITER.error(PATTERN, ex.getMessage(), messageError, ex);
        return handleExceptionInternal(ex, messageService.getMessage(ex.getMessage()), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ApplicationNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundError(RuntimeException ex, WebRequest request) {
        final var messageError = messageService.getMessage(ex.getMessage());
        LOGGER_WRITER.error(PATTERN, ex.getMessage(), messageError, ex);
        return handleExceptionInternal(ex, messageError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
