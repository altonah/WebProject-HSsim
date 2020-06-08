<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/main.css">
<title>Insert title here</title>
</head>
<body>
	<h1>main.jsp</h1>
	<%
		out.write("토큰값 : " + request.getParameter("token"));
	%>
	
	<header style="height:200px; background-color:gray;">헤더가 들어갈곳</header>
	
	<section class="slider">
		슬라이더
	</section>
	
	<section class="content">
		<section class="main_content">
			메인 컨텐츠
			
		</section>
		<section class="side_content">
			사이드 컨텐츠
		</section>
	</section>
	
	
	
	
	<footer style="height:200px; background-color:gray;">푸터가 들어갈곳</footer>
	
</body>
</html>