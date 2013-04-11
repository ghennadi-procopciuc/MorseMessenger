package com.netdata;

import java.io.Serializable;

public class ChangeProfileResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	Boolean result;

	public ChangeProfileResponse(Boolean result) {
		this.result = result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public Boolean getResult(Boolean result) {
		return result;
	}
}
