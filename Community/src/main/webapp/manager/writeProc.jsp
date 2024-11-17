<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %> 
<jsp:useBean id="bMgr" class="pack.dao.ManagerMgr" scope="page" />
<%
	bMgr.insertGallery(request);
	response.sendRedirect("/manager/list.jsp?gnbParam=list");
%>
