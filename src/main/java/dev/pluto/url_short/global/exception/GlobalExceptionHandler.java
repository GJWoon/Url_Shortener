package dev.pluto.url_short.global.exception;


import dev.pluto.url_short.global.dto.ErrorResponse;
import dev.pluto.url_short.global.model.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleException(final BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleException(final MethodArgumentNotValidException e) {
       System.out.println(e.getMessage());
        final ErrorResponse response = new ErrorResponse(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),400,"B001");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
