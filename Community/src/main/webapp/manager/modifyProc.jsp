<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="bMgr" class="pack.dao.BoardMgr" scope="page" />
  
<%


// out.print(bean.getNum() +  "<br>");
// out.print(bean.getSubject() + "<br>");
// out.print(bean.getContent() + "<br>");
// out.print(bean.getDepth() + "<br>");
// out.print(bean.getFileName() + "<br>");
// out.print(bean.getFileSize() + "<br>");
// out.print(bean.getIp() + "<br>");
// out.print(bean.getPos() + "<br>");
// out.print(bean.getReadCnt() + "<br>");
// out.print(bean.getRef() + "<br>");
// out.print(bean.getRegTM() + "<br>");
// out.print(bean.getuId() + "<br>");
// out.print(bean.getuName() + "<br>");


int exeCnt = bMgr.updateBoard(request);
String msg = "";
String redirectURL = "";

if (exeCnt == 1) {
	msg = "수정이 완료되었습니다.";
	redirectURL = "/bbs/list.jsp";
} else {
	msg = "수정에 실패했습니다.";
	redirectURL = "javascript:history.back()";
}
%>

<script>
	alert("<%=msg %>");
	location.href="<%=redirectURL %>";
</script>