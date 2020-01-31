package com.dapperdrakes.dimo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GenericResponse {

    private boolean success;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String error;
    private Object data;
    private Object error_message;
    public GenericResponse() {
        success = true;
    }

    public GenericResponse(Object data) {
        success = true;
        this.data = data;
    }

    public GenericResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }

    public GenericResponse(final String error_message) {
        this.error_message = error_message;
    }

    public GenericResponse(final String error_message, final String error) {
        this.error_message = error_message;
        this.error = error;
    }

    public GenericResponse(List<ObjectError> allErrors, String error) {
        this.error = error;
        String temp = allErrors.stream().map(e -> {
            if (e instanceof FieldError) {
                return "{\"field\":\"" + ((FieldError) e).getField() + "\",\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            } else {
                return "{\"defaultMessage\":\"" + e.getDefaultMessage() + "\"}";
            }
        }).collect(Collectors.joining(","));
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.error_message = mapper.readTree("[" + temp + "]");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Object getError_message() {
        return error_message;
    }

    public void setError_message(final Object error_message) {
        this.error_message = error_message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericResponse that = (GenericResponse) o;
        return success == that.success &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(error, that.error) &&
                Objects.equals(data, that.data) &&
                Objects.equals(error_message, that.error_message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, timestamp, error, data, error_message);
    }
}
