package br.com.vertigo.Employees.services.exceptions;

public class InternalServerException extends Exception {
    private static final long serialVersionUID = 1L;

    public InternalServerException(String msg) {
        super(msg);
    }
}