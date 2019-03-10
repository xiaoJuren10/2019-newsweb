<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String path=request.getContextPath();
 	String basePath=request.getScheme()+"://"+request.getServerName()+":"
 	+request.getServerPort()+path+"/";
 	pageContext.setAttribute("basePath",basePath);
 
 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="<%=basePath %>">
</head>
<body>     
	<h2>添加用户</h2>
	<form action="UserServlet" method="post">
		<input type="hidden" name="method" value="insert"/> 
		用户名：<input type="text" name="name"/><br/>
		用户密码：<input type="password" name="pwd"/><br/>
		生日：<input type="text" name="birthday"/>[yyyy-MM-dd]<br/>
		email:<input type="text" name="email"/><br/>
		<input type="submit" value="提交">
	</form>
</body>
</html>