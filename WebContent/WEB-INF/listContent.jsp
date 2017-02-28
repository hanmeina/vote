<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    欢迎${!empty sessionScope.user?user.username:'游客'}光临
	<table border="1" align="center" width="60%">
		<caption><h1>候选人详细信息</h1></caption>
		<tr>
			<th>人名</th>
			<td>${content.vote.content}</td>
		</tr>	
		<tr>	
			<th>年龄</th>
			<td>${content.age}</td>
		</tr>	
		<tr>
			<th>描述</th>
			<td>${content.description}</td>
		</tr>
	</table>
	<jsp:include page="back.jsp"/>
  </body>
</html>
