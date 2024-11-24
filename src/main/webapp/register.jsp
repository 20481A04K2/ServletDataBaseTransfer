<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h4{
color:red;
}
</style>
</head>
<body>
<form action="reg" method="post">
<h1>Register Page</h1>
<label>Name</label>
<input type="text" name="tbname"/><br>
<label>Email</lable>
<input type="email" name="tbmail"/><br>
<label>Password</label>
<input type="password" name="tbpass"/><br>
<%
if(request.getAttribute("message")!=null){
	out.println("<h4>"+request.getAttribute("message")+"</h4>");
}
%>
<input type="submit" value="Register"/>
<a href="login.jsp">Login</a>
</form>
</body>
</html>