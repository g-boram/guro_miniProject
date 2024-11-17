<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	String uId_Session = (String)session.getAttribute("uId_Session");
	out.print(request.getContextPath());
%>

<%@ page import="pack.dto.BoardBean,java.util.Vector" %>
<jsp:useBean id="bMgr" class="pack.dao.BoardMgr" />


    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>매니져 목록</title>
	<link rel="stylesheet" href="/style/style_Common.css?v">
	<link rel="stylesheet" href="/style/style_Template.css?v">
	<link rel="stylesheet" href="/style/style_Manager.css?v">
	<script src="/source/jquery-3.6.0.min.js"></script>
	<script src="/script/script_Manager.js"></script>
</head>
<body>
	<div id="wrap">
		<%@ include file="/ind/headerTmp.jsp" %>
		
		<div id="lnb">
			<%@ include file="/ind/mainLnbTmp.jsp" %>
		</div>
		
		<main id="main" class="dFlex">
			<div id="contents" class="managerList">
				<h1>Manager Page</h1>	
				<div id="menuBtn">
					<div id="menuItem">
						<img src="../images/menuImg/menu1.png" alt="menu1" width="300px" height="400px"/>
						<button type="button" id="" class="listBtnStyle">전시회 정보 등록</button>
					</div>
					<div id="menuItem">
						<img src="../images/menuImg/menu2.png" alt="menu2" width="300px" height="400px"/>
						<button type="button" id="galleryWriteBtn" class="listBtnStyle">전시작품 등록</button>
					</div>
					<div id="menuItem">
						<img src="../images/menuImg/menu3.png" alt="menu3" width="300px" height="400px"/>
						<button type="button" id="writeBtn" class="listBtnStyle">공지사항 등록</button>
					</div>
				</div>
				<div id="manager_list">
				
				</div>
			</div>
			
			
		</main>
		<%@ include file="/ind/footerTmp.jsp" %>
	</div>
</body>
</html>    