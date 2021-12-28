package vml.travelagency.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse constraintViolationExceptionHandler(ConstraintViolationException e) {
        ValidationErrorResponse response = new ValidationErrorResponse(new ArrayList<>(), LocalDateTime.now());

        e.getConstraintViolations().forEach(violation -> {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            log.error("ConstraintViolation raised : propertyPath = {} , message = {}", propertyPath, message);
            response.getViolations().add(new ValidationViolation(propertyPath, message));
        });
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ValidationErrorResponse response = new ValidationErrorResponse(new ArrayList<>(), LocalDateTime.now());

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            log.error("FieldError raised : on field = {} , message = {}", field, message);
            response.getViolations().add(new ValidationViolation(field, message));
        });
        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> accessForbiddenExceptionHandler(HttpServletRequest request,
                                                                  AccessDeniedException e) {
        return getResponseEntity(request, HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundExceptionHandler(HttpServletRequest request,
                                                                 EntityNotFoundException e) {
        return getResponseEntity(request, HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(NullEntityReferenceException.class)
    public ResponseEntity<Object> nullEntityReferenceExceptionHandler(HttpServletRequest request,
                                                                      NullEntityReferenceException e) {
        return getResponseEntity(request, HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(NullMethodParameterException.class)
    public ResponseEntity<Object> nullMethodParameterExceptionHandler(HttpServletRequest request,
                                                                      NullMethodParameterException e) {
        return getResponseEntity(request, HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(RoomNumberAlreadyExistException.class)
    public ResponseEntity<Object> roomNumberAlreadyExistExceptionHandler(HttpServletRequest request,
                                                                         RoomNumberAlreadyExistException e) {
        return getResponseEntity(request, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> internalServerErrorHandler(HttpServletRequest request, Exception e) {
        return getResponseEntity(request, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<Object> getResponseEntity(HttpServletRequest request, HttpStatus httpStatus, Exception e) {

        log.error("Exception raised = {} :: URL = {}", e.getMessage(), request.getRequestURL());

        String statusCode = String.format("%s / %s", httpStatus.value(), httpStatus.getReasonPhrase());
        ApiException apiException = new ApiException(statusCode, e.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(httpStatus).body(apiException);
    }
}
