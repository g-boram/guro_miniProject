<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String uId_Session = (String)session.getAttribute("uId_Session"); %>    

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>회원탈퇴</title>
	<link rel="stylesheet" href="/style/style_Common.css?v">
	<link rel="stylesheet" href="/style/style_Template.css?v">
</head>
<body>
	<div id="wrap">
		
		<!-- 헤더템플릿 시작 -->
			<%@ include file="/ind/headerTmp.jsp" %>
		<!-- 헤더템플릿 끝 -->
		
		<main id="main" class="dFlex">
			<!-- 실제 작업 영역 시작 -->
			<div id="contents" class="memQuitDiv">
				<form name="memQuitFrm" id="memQuitFrm">
					<h1>회원탈퇴</h1>
					<p>아래 버튼을 클릭하시면 회원을 탈퇴합니다</p>
					<button type="button" id="memQuitBtn">회원 탈퇴하기</button>
				</form>
			</div>
			<!-- 실제 작업 영역 끝 -->
		</main>
		<!-- main#main -->
		
	</div>
	<!-- div#wrap -->
	<script src="/source/jquery-3.6.0.min.js"></script>
	<script src="/script/script.js"></script>
</body>
</html>    