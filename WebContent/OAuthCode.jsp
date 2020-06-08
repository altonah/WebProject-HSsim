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
	response.sendRedirect("https://kr.battle.net/oauth/authorize?response_type=code&client_id=0533b43ca53c46b7ba17482a1c72ac8b&redirect_uri=http://192.168.7.241:8080/BDGG/Test.jsp");

%>


</body>
</html>