<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>admin.jsp</h1>
	<hr>
	<a href="https://kr.battle.net/oauth/authorize?response_type=code&access_type=offline&client_id=0533b43ca53c46b7ba17482a1c72ac8b&redirect_uri=http://localhost:8080/BDGG/GetCode.oa">인증코드 받기</a>
	<hr>
	<h3>토큰 발급</h3>
	<form action="https://kr.battle.net/oauth/token" method="post">
		<label for="grant_type">grant_type</label><input type="text" id="grant_type" name="grant_type" value="authorization_code">
		<label for="client_id">client_id</label><input type="text" id="client_id" name="client_id" value="0533b43ca53c46b7ba17482a1c72ac8b">
		<label for="client_secret">client_secret</label><input type="text" id="client_secret" name="client_secret" value="jNhp49OcADE6abZ0cqVUsH5WSy4zwPEQ">
		<label for="code">code</label><input type="text" id="code" name="code" value="<%=request.getParameter("code")%>">
		<label for="code">redirect_uri</label><input type="text" id="redirect_uri" name="redirect_uri" value="http://localhost:8080/BDGG/admin/admin.jsp">
		<hr>
		<input type="submit">
	</form>
</body>
</html>