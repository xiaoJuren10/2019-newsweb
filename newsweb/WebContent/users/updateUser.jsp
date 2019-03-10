<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/pub.jsp" %>
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
	<%
	User user=(User)request.getAttribute("user");
	SimpleDateFormat sdft=new SimpleDateFormat("yyyy-MM-dd");
	String str=sdft.format(user.getBirthday());
	
	%>

	<h2>修改</h2>
	<form action="UserServlet?uid=<%=user.getUid() %>" method="post" >
		<input type="hidden" name="method" value="update" />
		用户编号：<%=user.getUid()%><br/>
		用户名：<input type="text" name="username" value="<%=user.getUsername()%>"/><br/>
		用户密码：<input type="password" name="userpwd" value="<%=user.getUserpwd()%>"/><br/>
		生日：<input type="text" name="birthday" value="<%=str%>"/>[yyyy-MM-dd]<br/>
		email:<input type="text" name="email" value="<%=user.getEmail() %>"><br/>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>