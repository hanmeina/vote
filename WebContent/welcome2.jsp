<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
	${message}<br/>
	<c:choose>
		<c:when test="${requestScope.flag=='admin'}">
			<a href="#">下载</a>
		</c:when>
		<c:otherwise>
			下载
		</c:otherwise>
	</c:choose>
  </body>
</html>
