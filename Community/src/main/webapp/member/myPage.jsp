<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" href="/style/style_Common.css?v">
	<link rel="stylesheet" href="/style/style_Template.css?v">
	<script src="/source/jquery-3.6.0.min.js"></script>
	<script src="/script/script.js"></script>
</head>
<body>
	<div id="wrap">
		<!-- 헤더템플릿 시작 -->
			<%@ include file="/ind/headerTmp.jsp" %>
		<!-- 헤더템플릿 끝 -->
		
		<main id="main" class="dFlex">
			<div id="lnb">
				<!-- 메인 LNB 템플릿 시작 -->
					<%@ include file="/ind/mainLnbTmp.jsp" %>
				<!-- 메인 LNB 템플릿 끝 -->
			</div>
			
			<!-- 실제 작업 영역 시작 -->
			<div id="contents">
				<h1>LNB 메뉴에서 작업을 선택하세요</h1>
			</div>			
			<!-- 실제 작업 영역 끝 -->
		</main>
		<!-- main#main -->
		
		<!-- 푸터템플릿 시작 -->
			<%@ include file="/ind/footerTmp.jsp" %>
		<!-- 푸터템플릿 끝 -->
		
	</div>
	<!-- div#wrap -->
</body>
</html>    