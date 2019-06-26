<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<span>Tico e teco</span>
	<span>${pageContext.request.contextPath}/</span>
	<div>
		<img alt="" src="../../resources/images/Space-Invaders.gif">
	</div>
	
	<div>
		<img alt="asas" src="<%=request.getContextPath()%>/resources/images/Space-Invaders.gif">
	</div>
</body>
</html>