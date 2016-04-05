package com.dearbinge.data.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.dearbinge.utils.Constants;

@JsonRootName(Constants.ERROR_RESPONSE)
public class ErrorResponse {
	private String code;
	private String msg;
	private String sub_code;
	private String sub_msg;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSub_code() {
		return this.sub_code;
	}

	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}

	public String getSub_msg() {
		return this.sub_msg;
	}

	public void setSub_msg(String sub_msg) {
		this.sub_msg = sub_msg;
	}
}
