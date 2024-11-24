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
<form action="login" method="post" >
<h1>Login Page</h1>
<label>User name</label>
<input type="email" name="lemail"/><br>
<label>Password</label>
<input type="password" name="lpass"/><br>
<label>

<%
if(request.getAttribute("message")!=null){
 out.println("<h4>"+request.getAttribute("message")+"</h4>");
}
%>

</label>
<input type="submit" value="login"/>
<a href="register.jsp">Sign Up</a>
</form>
</body>
</html>