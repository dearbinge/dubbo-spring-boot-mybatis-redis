package com.dearbinge.data.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.dearbinge.utils.Constants;

@JsonRootName(Constants.PARKING_BASIC_DATA_SYNC_RESPONSE)
public class ParkingBasicDataSyncResponse {
	private String park_id;
	private String message;
	private int code;
	private Boolean successful;

	public String getPark_id() {
		return this.park_id;
	}

	public void setPark_id(String park_id) {
		this.park_id = park_id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Boolean getSuccessful() {
		return this.successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}
}
