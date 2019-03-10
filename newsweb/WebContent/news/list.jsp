<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 <%@ include file="/pub.jsp" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<Nnew> list=(List<Nnew>)request.getAttribute("list");
	if(list!=null){
%>
	<h2>所有新闻</h>	
	<ul>
		<%
			for(Nnew news:list){
		%>
		<li><a href=#><%=news.getNtitle() %></a><%="\t" %>
			<a href="NewsServlet?method=delete&nid=<%=news.getNid() %>">删除</a>
			<%="\t" %><a href="NewsServlet?method=find&nid=<%=news.getNid() %>">修改</a>
		</li>
	
		<%} %>
	</ul>
<%}else{	
	response.sendRedirect("../NewsServlet?method=list");

} %>
</body>
</html>