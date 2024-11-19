<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="mMgr" class="pack.dao.MemberMgr" />    

<% 
String uId_Session = (String)session.getAttribute("uId_Session"); 
String uName = mMgr.getMemberName(uId_Session);
%>

<body>

	<div id="slideshowArea">
	
	<div id="Msg">
	<h1>Main</h1>
				<h2 id="indexGuideMsg">
				<% if(uId_Session == null ) { %>	
					작업 중(회원인증, BBS 등)<br>
					메인에 노출하고 싶은 결과를 출력	
				<% } else {
					out.print(uName + "님이 로그인했습니다.");	
				   } %>	
				   	</h2>
			</div>
			
		
		<div id="slideFrame" class="dflex">

			<div class="slide">
				<a href="#"> <img src="/images/shineStrar.png" alt="이미지0">
				</a>
			</div>

			<!-- 두 번째 슬라이드 -->
			<div class="slide">
				<a href="#"> <img src="/images/StarryNight.png" alt="이미지1">
				</a>
			</div>

			<!-- 세 번째 슬라이드 -->
			<div class="slide">
				<a href="#"> <img src="/images/tree.png" alt="이미지2">
				</a>
			</div>

		</div>
	</div>
	<!-- div#slideshowArea -->

</body>

