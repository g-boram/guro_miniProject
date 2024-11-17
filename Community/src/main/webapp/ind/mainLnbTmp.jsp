<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String uId_Session_MLTmp = (String)session.getAttribute("uId_Session");
request.setCharacterEncoding("UTF-8");

String gnbParam = "";
if(request.getParameter("gnbParam") != null) {
	gnbParam = request.getParameter("gnbParam");
}
%>    

<body>

	<nav id="mainLNB">
		<ul id="lnbMainMenu">
			<li class="lnbMainLi"><a href="#">About</a></li>
			<li class="lnbMainLi"><a href="/ind/galleryTable.jsp">Gallery</a></li>
			<li class="lnbMainLi"><a href="/bbs/list.jsp?gnbParam=list">News</a></li>
			<li class="lnbMainLi"><a href="/bbs/list.jsp?gnbParam=list">Board</a></li>
			<li class="lnbMainLi"><a href="#">Reservation</a></li>
		</ul>
	</nav>

</body>
 