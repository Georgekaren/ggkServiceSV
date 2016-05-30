<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html> 
	<head>
		<title>List Services</title> 
		</head> 
	<body> 
		<h1>Available services</h1> 
		<c:forEach items="${servicemap}" var="service">
			${service.value.name}
			<h2>Available Operations</h2> 
			<c:forEach items="${service.value.operations}" var="operation">
				${operation.name.localPart}<br/>
			</c:forEach>
		</c:forEach>
	</body> 
</html> 
