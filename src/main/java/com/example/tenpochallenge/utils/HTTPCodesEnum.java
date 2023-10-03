package com.example.tenpochallenge.utils;

public enum HTTPCodesEnum {
	
	STATUS_200("200", "OK"),
	STATUS_201("201", "CREATED"),
	STATUS_202("202", "ACCEPTED"),
	STATUS_400("400", "BAD_REQUEST"),
	STATUS_401("401", "UNAUTHORIZED"),
	STATUS_402("402", "PAYMENT_REQUIRED"),
	STATUS_403("403", "FORBIDDEN"),
	STATUS_404("404","NOT_FOUND"),
	STATUS_408("408","REQUEST_TIMEOUT"),
	STATUS_410("410", "GONE"),
	STATUS_500("500", "INTERNAL_SERVER_ERROR"),
	STATUS_504("504", "TIMEOUT");


	private String statusCode;
	private String statusText;

	private HTTPCodesEnum(String codigo, String message) {
		this.statusCode = codigo;
		this.statusText = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

}
