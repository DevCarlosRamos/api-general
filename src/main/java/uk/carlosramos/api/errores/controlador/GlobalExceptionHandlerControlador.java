package uk.carlosramos.api.errores.controlador;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import uk.carlosramos.api.errores.ClaveIncorrectaException;
import uk.carlosramos.api.errores.modelo.RespuestaModeloRes;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandlerControlador {

    @ExceptionHandler(ClaveIncorrectaException.class)
    public Mono<ResponseEntity<RespuestaModeloRes>> handleClaveIncorrecta(ClaveIncorrectaException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new RespuestaModeloRes(ex.getMessage())));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<RespuestaModeloRes>> handleValidationException(WebExchangeBindException ex) {

        String mensaje = ex.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" | "));

        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RespuestaModeloRes(mensaje)));
    }


}
