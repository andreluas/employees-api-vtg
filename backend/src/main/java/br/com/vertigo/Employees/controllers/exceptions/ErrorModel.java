package br.com.vertigo.Employees.controllers.exceptions;

import java.io.Serializable;

public class ErrorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String error;
    private String from;

    public ErrorModel() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
