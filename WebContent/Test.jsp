<%@page import="com.bdgg.util.HttpConnection"%>
<%@page import="com.bdgg.util.OAuth"%>
<%@page import="java.io.PrintStream"%>
<%@page import="com.bdgg.util.Connection"%>
<%@page import="com.bdgg.util.Property"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="java.util.List"%>
<%@page import="com.bdgg.util.TestDTO"%>
<%@page import="com.bdgg.util.DTO"%>
<%@page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Test.jsp</h1>
<%
	
// 	TestDAO tdao = new TestDAO();
// 	TestDTO tdto = new TestDTO();
// 	//tdto.setIdtest(3);
// 	//tdto.setCol1("aa");
	
// 	//tdao.delete("where idtest=3");
// 	//tdao.insert(tdto);
// 	List<TestDTO> aa =  tdao.getListAll("");
// 	//System.out.println(aa.get(1).getIdtest());
// 	for(TestDTO dto : aa){
// 		System.out.println(dto.getIdtest());
// 		System.out.println(dto.getCol1());	
// 		System.out.println(dto.getCol2());	
// 	}
	
// 	Property.setProperty(request, tdto);
// 	System.out.println(tdto.getIdtest());
// 	System.out.println(tdto.getCol1());
// 	System.out.println(tdto.getCol2());
	
// 	tdao.insert(tdto);
	
	//String str = Connection.getStringJSONFromRequest("https://kr.api.blizzard.com/hearthstone/cards?locale=ko_KR&manaCost=10&attack=4&health=10&collectible=1&page=1&pageSize=5&sort=name&order=desc&access_token=USOAa1nSzntE2jrg1SJGGIF0cDso72cPrT");
	//String str = Connection.getStringJSONFromRequest("https://us.battle.net/oauth/token?grant_type=authorization_code&client_id=0533b43ca53c46b7ba17482a1c72ac8b&client_secret=jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ&code=US7B18UQ8PE5691XDIWZ5GMEJXMP4NR4LL&redirect_uri=https://develop.battle.net/documentation/hearthstone/game-data-apis");
	/*
	if(session.getAttribute("code")==null){
		String code = request.getParameter("code");
		
		session.setAttribute("code", code);
		System.out.println("코드  - " + code);
		
		
		response.sendRedirect("https://kr.battle.net/oauth/token?grant_type=authorization_code&client_id=0533b43ca53c46b7ba17482a1c72ac8b&client_secret=jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ&code="+ code +"&redirect_uri=http://192.168.7.241:8080/BDGG/Test.jsp");
		
	}else if(session.getAttribute("token")==null){
		String token = request.getParameter("access_token");
		System.out.println("토큰 - " + token);
		session.setAttribute("token", token);
		
	}

	*/
	
	
%>
</body>
</html>