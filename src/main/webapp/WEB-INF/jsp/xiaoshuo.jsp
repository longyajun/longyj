<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- ${content} --%>

<form action="${pageContext.request.contextPath}/xs" method="get">
  <p>id: <input type="text" name="id"  value="${id}"/></p>
  <p>下一页: <input type="text" name="nexturl" value="${nexturl}"/></p>
  <input type="submit" value="下一页" />
<%--   <a href="${pageContext.request.contextPath}/xs/${nexturl}"><input type="submit" value="上一页" /></a>
	<a href="${pageContext.request.contextPath}/xs?id=${id}"><input type="submit" value="下一页" /></a> --%>

</form>

</body>
</html>