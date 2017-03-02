<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    欢迎${!empty sessionScope.user?user.username:'游客'}光临
    <c:choose>
    	<c:when test="${!empty sessionScope.user}">
		    <a style="text-decoration:none" href="${pageContext.request.contextPath}/VoteServlet?method=exit">
		    	安全退出
		    </a>
    	</c:when>
    	<c:otherwise>
		    	安全退出
    	</c:otherwise>
    </c:choose>
    <hr/>
	<table border="1" align="center" width="60%">
		<caption><h1>投票人信息</h1></caption>
		<tr>
			<th>IP地址</th>
			<th>最后投票时间</th>
		</tr>
		<c:forEach var="info" items="${requestScope.infoList}">
			<tr>
				<td>
					${info.ip}
				</td>
				<td align="center">
					<fmt:formatDate
						type="both"
						dateStyle="full"
						timeStyle="default"
						value="${info.votetime}"
					/>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p align="right">
		<a  style="text-decoration:none"
			href="${pageContext.request.contextPath}/welcome.jsp">
			返回
		</a> 	
	</p>
  </body>
</html>
