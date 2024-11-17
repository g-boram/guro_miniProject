<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mMgr" class="pack.dao.MemberMgr" />

<%
request.setCharacterEncoding("UTF-8");
String uId = request.getParameter("uId");
String uPw = request.getParameter("uPw");

boolean loginRes = mMgr.loginMember(uId, uPw);
%>    

<script>
<%
if (loginRes) {
	session.setAttribute("uId_Session", uId);
	String loginAuth = mMgr.setLoginQuth(uId); 
	session.setAttribute("auth_Session", loginAuth);
%>
	location.href="/index.jsp";
<%
} else {
%>
	alert("아이디 또는 비밀번호를 확인해주세요.");
	location.href="/member/login.jsp";
<%
}
%>
</script>   