package com.HSsim.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

	public static void sendGet(String targetUrl, String method) throws Exception{
		
		URL url = new URL(targetUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		con.setRequestMethod(method);
		
		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		System.out.println("http 응답 코드 : " + responseCode);
		System.out.println("http body : " + response.toString());
		
	}
	
}
