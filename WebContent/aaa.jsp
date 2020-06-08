<%@page import="java.util.HashMap"%>
<%@page import="com.bdgg.util.HttpConnectionUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String tokenUrl = "http://localhost:8080/BDGG/Server";


HashMap<String, String> pram = new HashMap<>();
pram.put("grant_type", "authorization_code");
pram.put("client_id", "0533b43ca53c46b7ba17482a1c72ac8b");
pram.put("client_secret", "jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ");

String resp = HttpConnectionUtil.postRequest(tokenUrl, pram);

System.out.println(resp);

%>
</body>
</html>