package org.hopenghou.SpringBootApi.handler;

import org.hibernate.exception.ConstraintViolationException;
import org.hopenghou.SpringBootApi.utility.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ConstraintViolationExceptionHandler {
    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param ex the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                    WebRequest request) {
        Throwable cause = ex.getCause();
        if (cause instanceof ConstraintViolationException && cause.getMessage() != null && cause.getMessage().contains("UNIQUE")) {
            ApiError errorResponse = new ApiError("Name is not unique.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        ApiError errorResponse = new ApiError(ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}


