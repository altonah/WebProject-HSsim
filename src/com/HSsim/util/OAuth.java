package com.HSsim.util;

import java.net.URL;

public class OAuth {
	
	private final static String AUTH_HOST = "https://kr.battle.net";
	private final static String AUTHORIZE_URL = "/oauth/authorize";
	private final static String REDIRECT_URL = "http://localhost:8080/BDGG/Test.jsp";
	
	
	private final static String TOKEN_REQUEST_URL = AUTH_HOST + "/oauth/token";
	private String code = "";
	
	/*
	/oauth/authorize?client_id={app_key}&redirect_uri={redirect_uri}&response_type=code
	*/
	
	private final static String CLIENTID = "0533b43ca53c46b7ba17482a1c72ac8b";
	private final static String SECRET = "jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ";
	
	public final static String getToken(){
		
		
		//Connection.getStringJSONFromRequest(str);
		
		return Connection.getStringJSONFromRequest(str);
	}
	
}
