<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.FileSystemXmlApplicationContext" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
ApplicationContext ac = new FileSystemXmlApplicationContext("spring.xml");
ac.getBean("ggkServiceDAO");
%>

1111