package br.com.vertigo.Employees.controllers.handlers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.vertigo.Employees.services.exceptions.BadRequestException;
import br.com.vertigo.Employees.services.exceptions.BooleanDtoException;
import br.com.vertigo.Employees.services.exceptions.InternalServerException;
import br.com.vertigo.Employees.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorModel> employeeNotFoud(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorModel errorModel = new ErrorModel();
        errorModel.setTimestamp(LocalDateTime.now());
        errorModel.setStatus(HttpStatus.NOT_FOUND.toString());
        errorModel.setFrom("employees-api");
        errorModel.setMessage(e.getMessage());
        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorModel> serverError(InternalServerException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorModel errorModel = new ErrorModel();
        errorModel.setTimestamp(LocalDateTime.now());
        errorModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorModel.setFrom("employees-api");
        errorModel.setMessage(e.getMessage());
        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorModel> badRequest(BadRequestException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorModel errorModel = new ErrorModel();
        errorModel.setTimestamp(LocalDateTime.now());
        errorModel.setStatus(HttpStatus.BAD_REQUEST.toString());
        errorModel.setFrom("employees-api");
        errorModel.setMessage(e.getMessage());
        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(BooleanDtoException.class)
    public ResponseEntity<ErrorModel> booleanException(BooleanDtoException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorModel errorModel = new ErrorModel();
        errorModel.setTimestamp(LocalDateTime.now());
        errorModel.setStatus(HttpStatus.BAD_REQUEST.toString());
        errorModel.setFrom("employees-api");
        errorModel.setMessage(e.getMessage());
        return ResponseEntity.status(status).body(errorModel);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            errors.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss")));
            errors.put("status", HttpStatus.BAD_REQUEST.toString());
            errors.put("from", "employees-api");
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException e,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        ErrorModel errors = new ErrorModel();
        errors.setTimestamp(LocalDateTime.now());
        errors.setStatus(status.toString());
        errors.setFrom("employees-api");
        errors.setMessage(e.getMessage());

        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        ErrorModel errors = new ErrorModel();
        errors.setTimestamp(LocalDateTime.now());
        errors.setStatus(status.toString());
        errors.setFrom("employees-api");
        errors.setMessage(e.getMessage());

        return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        ErrorModel errors = new ErrorModel();
        errors.setTimestamp(LocalDateTime.now());
        errors.setStatus(status.toString());
        errors.setFrom("employees-api");
        errors.setMessage(e.getMessage());

        return ResponseEntity.status(status).body(errors);
    }
}
