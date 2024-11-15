<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String uId_Session = (String)session.getAttribute("uId_Session"); %>

<jsp:useBean id="mMgr" class="pack.dao.MemberMgr" />

<% boolean delChk = mMgr.deleteMember(uId_Session); %>
<script>
<% if (delChk) {
session.invalidate(); %>
alert("탈퇴처리가 완료되었습니다.\n이용해주셔서 감사합니다.");
location.href="/index.jsp";

<% } else { %>
	alert("회원탈퇴를 실패했습니다.");
	history.back();
<% } %>

</script>
