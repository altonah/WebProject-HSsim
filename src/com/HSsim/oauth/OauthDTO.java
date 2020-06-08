package com.HSsim.oauth;

import com.HSsim.util.DTO;

public class OauthDTO extends DTO {

	private String code;
	private String token;
	private String ref_token;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRef_token() {
		return ref_token;
	}
	public void setRef_token(String ref_token) {
		this.ref_token = ref_token;
	}
	
	
	
}
