package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity errorHandlerValidacionDeIntegridad (Exception e){

        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerValidacionDeNegocios (Exception e){

        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> errorhandleDateTimeParseException(DateTimeParseException ex) {
        String error = "Formato de fecha inválido. Use el formato 'yyyy-MM-dd'T'HH:mm:ss'.";
        return  ResponseEntity.badRequest().body(error);
    }

    private record DatosErrorValidacion (String campo, String error){
        public DatosErrorValidacion (FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
