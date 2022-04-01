package br.com.vertigo.Employees.controllers.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.vertigo.Employees.services.exceptions.InternalServerException;
import br.com.vertigo.Employees.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorModel> employeeNotFoud(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorModel errorModel = new ErrorModel();
        errorModel.setError("Employee not found");
        errorModel.setFrom(request.getRequestURI());
        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorModel> serverError(InternalServerException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorModel errorModel = new ErrorModel();
        errorModel.setError("Internal server error");
        errorModel.setFrom(request.getRequestURI());
        return ResponseEntity.status(status).body(errorModel);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorModel> badRequest(JsonParseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorModel errorModel = new ErrorModel();
        errorModel.setError("Bad request sintaxe");
        errorModel.setFrom(request.getRequestURI());
        return ResponseEntity.status(status).body(errorModel);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}
