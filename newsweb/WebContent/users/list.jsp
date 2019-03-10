<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
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
<script type="text/javascript">
	function deleteUserRow(userid){
		var result=confirm("是否删除编号为"+userid+"用户！");
		 if(result){
			 window.location.href="UserServlet?method=delete&uid="+userid;
		 }
	}
</script>
</head>
<body>
	<% 
	int index=0;
	User loginUser = (User)session.getAttribute("user");
	if(loginUser==null){
	%>
		<a href="login.jsp">请先登录</a>
	<% }else{%>
	欢迎<%=loginUser.getUsername() %>登陆本系统
	<h4>学生信息表</h4>
	<table weidth="800px" border="1px">
		<tr>
			<th>编号</th>	
			<th>姓名</th>	
			<th>密码</th>	
			<th>生日</th>	
			<th>email</th>
			<th>操作</th>		
		</tr>
	<%
		List<User>list=(List<User>)request.getAttribute("list");
		if(list!=null){
			for(User user:list){
	%>
			<tr <%if(index%2==0){%>style="background-color: red"<% } %>>
				<td><%=user.getUid() %></td>
				<td><%=user.getUsername() %></td>
				<td><%=user.getUserpwd()%></td>
				<td><%=user.getBirthday() %></td>
				<td><%=user.getEmail() %></td>
				<td>
					<a href="javascript:deleteUserRow(<%=user.getUid() %>)">删除</a>
					<a href="UserServlet?method=find&uid=<%=user.getUid()%>">修改</a>
				</td>
	
			</tr>
			
	<% 	index+=1;	
			}
		}
	}
	%>
	
	</table>
	<a href="users/insertUser.jsp">添加用户</a>
</body>
</html>