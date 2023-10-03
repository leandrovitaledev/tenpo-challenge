package com.example.tenpochallenge.exception;

import com.example.tenpochallenge.utils.HTTPCodesEnum;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 583853622216583241L;
	
	private HTTPCodesEnum status;
	
	public CustomException(HTTPCodesEnum status) {
		super();
	}

	public HTTPCodesEnum getStatus() {
		return status;
	}

	public void setStatus(HTTPCodesEnum status) {
		this.status = status;
	}
	
}
