<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/pub.jsp" %>
 <%
 	String path=request.getContextPath();
 	String basePath=request.getScheme()+"://"+request.getServerName()+":"+
 		request.getServerPort()+path+"/";
 	pageContext.setAttribute("basePath", basePath);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="ncontent"]', {
				cssPath : 'kindeditor/plugins/code/prettify.css',
				uploadJson : 'kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>
<body>
	<h2>修改新闻</h2>
	
	<%
	Nnew news=(Nnew)request.getAttribute("news");
	List<Type> list=(List<Type>)request.getAttribute("tlist");
	if(list!=null){
	%>
	<form action="NewsServlet" method="post" name="example">
		<input type="hidden" name="method" value="update" />
		<input type="hidden" name="nid" value="<%=news.getNid() %>"/>
		分类：<select name="tid">
		<%
			for(Type type: list){
		%>
			<option value="<%=type.getTid() %>"
		<%
			if(news.getTid()==type.getTid()){
		%>	
			selected="selected"
		<%} %>	
			><%=type.getTname() %></option>
		<% 
			}
		%>
		</select><br/>
		标题：<input type="text" name="ntitil" value="<%=news.getNtitle() %>" /><br/>
		日期：<input type="text" name="ndate" value="<%=news.getNdate() %>" /><br/>
		作者：<input type="text" name="nauthor" value="<%=news.getNauthor() %>" /><br/>
		内容：<textarea name="ncontent" rows="10" cols="100" value="<%=news.getNcontent() %>"></textarea>
		<input type="submit" value="提交"/>
	</form>
	
	<%}else{
			
		response.sendRedirect("../NewsServlet?method=find ");
	
	}
	%>
</body>
</html>