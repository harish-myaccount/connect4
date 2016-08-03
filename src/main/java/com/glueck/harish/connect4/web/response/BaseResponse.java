package com.glueck.harish.connect4.web.response;

import lombok.Data;

@Data
public class BaseResponse {
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	private String status;
	private String message;
}
