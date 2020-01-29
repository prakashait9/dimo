package com.dapperdrakes.dimo.util;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GenericResponse {

	private boolean success;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String error;
	private Object data;

	public GenericResponse() {
		super();
	}

	public GenericResponse(boolean success, String error) {
		super();
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
}
