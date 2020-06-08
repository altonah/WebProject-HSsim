package com.HSsim.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.HSsim.oauth.OauthDAO;
import com.HSsim.oauth.OauthDTO;
import com.oracle.jrockit.jfr.RequestableEvent;
import com.sun.xml.internal.ws.encoding.XMLHTTPBindingCodec;

public class GetCodeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("겟코드액션.java ...");
		ActionForward af = new ActionForward();
		
		String OAUTH_URL = "https://kr.battle.net/oauth/authorize";
		String TOKEN_URL = "https://kr.battle.net/oauth/token";
		
		String ID = "0533b43ca53c46b7ba17482a1c72ac8b";
		String PASS = "jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ";
		String CODE = request.getParameter("code");
		String REDIRECT_URL = "http://localhost:8080/HSsim/GetCode.oa";
		
		HashMap<String, String> pram = new HashMap<>();
		pram.put("grant_type", "authorization_code");
		pram.put("client_id", ID);
		pram.put("client_secret", PASS);
		pram.put("code", CODE);
		pram.put("redirect_uri", REDIRECT_URL);
		
		/*
		String tagetURL = TOKEN_URL + "?grant_type=authorization_code&"
						+ "client_id=" + ID + "client_secret=" + PASS + "&code=" + CODE
						+ "&redirect_uri=" + REDIRECT_URL;
		
		*/
		String result = HttpConnectionUtil.postRequest(TOKEN_URL, pram);
		
		System.out.println("응답 MSG - " + result);
		
		JSONParser jsonParser = new JSONParser();
		
		JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
		
		String token = (String) jsonObj.get("access_token");
		String refToken = (String) jsonObj.get("refresh_token");
		
		OauthDAO odao = new OauthDAO();
		OauthDTO odto = new OauthDTO();
		Property.setProperty(request, odto);
		odto.setToken(token);
		odto.setRef_token(refToken);
		odao.insert(odto);
		
		System.out.println("토큰값 : " + token);
		
		
		
		af.setPath("main.jsp?token="+token);
		af.setRedirect(false);
		
//		HttpSession session = request.getSession();

//		String str = "https://kr.battle.net/oauth/token?grant_type=authorization_code&"
//				+ "client_id=0533b43ca53c46b7ba17482a1c72ac8b&"
//				+ "client_secret=jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ&";
//		
//		if(session.getAttribute("code")==null){
//			session.setAttribute("code", request.getParameter("code"));
//			System.out.println("code : "+session.getAttribute("code"));
//			String tokenUrl = "https://kr.battle.net/oauth/token";
//			HashMap<String, String> pram = new HashMap<>();
//			pram.put("grant_type", "authorization_code");
//			pram.put("client_id", "0533b43ca53c46b7ba17482a1c72ac8b");
//			//pram.put("client_secret", "jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ");
//			pram.put("code", session.getAttribute("code").toString());
//			pram.put("redirect_url", "http://localhost:8080/BDGG/GetCode.oa");
//			
//			String resp = HttpConnectionUtil.postRequest(tokenUrl, pram);
//			
//			System.out.println(resp);
//			
//		}
//		else if(session.getAttribute("token")==null){
//			System.out.println("토큰값....");
//			url.setPath("requestCode.jsp");
//			url.setRedirect(true);
//			//response.sendRedirect("/requestCode.jsp");
//			
//			
//		}
		
		
		return af;
	}

}
