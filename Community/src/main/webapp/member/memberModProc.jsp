<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>  
<jsp:useBean id="mMgr" class="pack.dao.MemberMgr" />
<jsp:useBean id="mBean" class="pack.dto.MemberBean" />
<jsp:setProperty name="mBean" property="*" />

<%
boolean modChk = mMgr.updateMember(mBean);
%>

<% // Quiz. 회원 정보를 수정하는 소스 구현 %>
<script>
<% if (modChk) { %>
alert("정보를 수정하셨습니다.");
location.href="/index.jsp";
<% } else { %>
alert("회원정보 수정 중 문제가 발생했습니다. 다시 시도해주세요.\n만일 문제가 계속될 경우 고객센터(02-1234-5678)로 연락해주세요.");
history.back();
<% } %>
</script>