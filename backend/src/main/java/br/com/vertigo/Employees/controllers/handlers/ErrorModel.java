package br.com.vertigo.Employees.controllers.handlers;

import java.io.Serializable;

public class ErrorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String timestamp;
    private String status;
    private String from;
    private String message;

    public ErrorModel() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
