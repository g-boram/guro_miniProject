<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String uId_Session = (String)session.getAttribute("uId_Session"); %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>메인</title>
	<link rel="stylesheet" href="/style/style_Common.css?v">
	<link rel="stylesheet" href="/style/style_Template.css?v">
</head>
<body>
	<div id="wrap">
			<%@ include file="/ind/headerTmp.jsp" %>
			<div id="lnb">
				<%@ include file="/ind/mainLnbTmp.jsp" %>
			</div>
		<main id="main" >
			<div id="contents" class="mainContent">
				<!-- Slide -->
				<div id="lnb">
					<%@ include file="/ind/mainSlide.jsp" %>
				</div>
				
				<div id="lnb">
					<%@ include file="/ind/galleryList.jsp" %>
				</div>
				<div id="lnb">
                   	<img src="../images/mainImg/mainImg1.png" width="100%" alt="mainImg1">
               	</div>
               	<div id="lnb">
                   	<img src="../images/mainImg/mainImg2.png" width="100%" alt="mainImg2">
               	</div>
               	<div id="lnb">
                   	<img src="../images/mainImg/mainImg3.png" width="100%" alt="mainImg3">
               	</div>
               	<div id="lnb">
                   	<img src="../images/mainImg/mainImg4.png" width="100%" alt="mainImg4">
               	</div>
			</div>
		</main>
		<%@ include file="/ind/footerTmp.jsp" %>
	</div>
	<script src="/source/jquery-3.6.0.min.js"></script>
	<script src="/script/script.js"></script>
</body>
</html>    