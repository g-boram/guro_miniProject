<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



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
		<!-- 헤더템플릿 시작, iframe으로 변경 -->
		<%@ include file="/ind/headerTmp.jsp"%>
		<!-- 헤더템플릿 끝 -->

		<main id="main" class="dFlex">

			<div id="lnb">
				<!-- 메인 LNB 템플릿 시작 -->
				<%@ include file="/ind/mainLnbTmp.jsp"%>
				<!-- 메인 LNB 템플릿 끝 -->
			</div>

			<!-- 실제 작업 영역 시작 -->
			<div id="contents">

				<!-- backGround 영역 시작-->
				<%-- 					<%@ include file="/ind/backGround.jsp" %> --%>
				<jsp:include page="/ind/backGround.jsp" />

				<!-- backGround 영역 끝-->
				</div>
			<!-- 실제 작업 영역 끝 -->
		</main>
		<!-- main#main -->
		<!-- footer 템플릿 시작 -->
		<%@ include file="/ind/footerTmp.jsp"%>
		<!-- footer 템플릿 끝 -->

	</div>
	<!-- div#wrap -->
	<script src="/source/jquery-3.6.0.min.js"></script>
	<script src="/script/script.js"></script>
</body>
</html>
