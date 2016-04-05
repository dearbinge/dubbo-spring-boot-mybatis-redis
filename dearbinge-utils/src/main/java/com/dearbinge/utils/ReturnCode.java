package com.dearbinge.utils;

public enum ReturnCode {
	SUCCESS(0x00, "success"), REQ_SYSTEM_PARAMS_HAVE_NULLOREMPTY(0x1000,
			"request system params have null or emprty"), REQ_DIFF_TIME_OVER(0x1001,
					"this request time is out of the specific time"), REQ_IS_REPEATED(0x1002,
							"this request is repeated"), SECURITY_IS_NOT_EXIST_OR_EXPIRED(0x1003,
									"security key is not existed or expired"), REQ_SIGN_IS_ERROR(0x1004,
											"sign of request is error"), SYSTEM_ERROR(0x1005, "system error");
	private int code;
	private String propertyDescription;

	private ReturnCode(int code, String propertyDescription) {
		this.code = code;
		this.propertyDescription = propertyDescription;
	}

	public int getCode() {
		return this.code;
	}

	public String toString() {
		return this.propertyDescription;
	}
}
