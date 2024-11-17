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
		<div id="lnb">
			<!-- 메인 LNB 템플릿 시작 -->
				<%@ include file="/ind/mainLnbTmp.jsp" %>
			<!-- 메인 LNB 템플릿 끝 -->
		</div>
		<main id="main" class="dFlex">
			<!-- 실제 작업 영역 시작 -->
			<div id="contents">
				<div id="myPageContent" style="
					display: flex;
					height: 500px;
					justify-content: center;
					align-items: center;
				">
					<div id="myPageBtn" style="
						width: 200px;
						height: 50px;
						background-color: black;
						border-radius:5px;
						margin-right:30px;
						display: flex;
						justify-content: center;
						align-items: center;	
					"><a href="/member/memberMod.jsp" style="color: #fff;">회원정보 수정</a></div>
					<div id="myPageBtn"  style="
						width: 200px;
						height: 50px;
						background-color: black;
						border-radius:5px;
						margin-right:30px;
						display: flex;
						justify-content: center;
						align-items: center;	
					"><a href="/member/memberQuit.jsp" style="color: #fff;">회원탈퇴</a></div>
				</div>
				
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