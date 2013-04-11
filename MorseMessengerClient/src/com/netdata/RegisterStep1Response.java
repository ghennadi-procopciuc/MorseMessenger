package com.netdata;
import java.io.Serializable;


public class RegisterStep1Response implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Boolean valid;

	public RegisterStep1Response(Boolean valid) {
		super();
		this.valid = valid;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "RegisterStep1Response [valid=" + valid + "]";
	}
	
}
