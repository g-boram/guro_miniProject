<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="mMgr" class="pack.dao.MemberMgr" scope="page" />
<%
String uId = (String)session.getAttribute("uId_Session");
String uName = mMgr.getMemberName(uId);
%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>글쓰기</title>
	<link rel="stylesheet" href="/style/style_Common.css?v">
	<link rel="stylesheet" href="/style/style_Template.css?v">
	<link rel="stylesheet" href="/style/style_BBS.css?v">
	<script src="/source/jquery-3.6.0.min.js"></script>
	<script src="/script/script_BBS.js"></script>
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
			<div id="contents" class="bbsWrite">

				<h2>글쓰기</h2>			
				
				<form name="writeFrm" enctype="multipart/form-data" method="post" id="writeFrm">
				
					<table>
						<tbody>
							<tr>
								<td class="req">성명</td>
								<td>
									<%=uName %>
									<input type="hidden" name="uName" value="<%=uName %>">
									<input type="hidden" name="uId" value="<%=uId %>">
								</td>
							</tr>
							<tr>
								<td class="req">제목</td>
								<td>
									<input type="text" name="subject" maxlength="50" id="subject">
								</td>
							</tr>
							<tr>
								<td class="contentTD">내용</td>
								<td><textarea name="content" id="content" cols="60" wrap="hard"></textarea></td>
							</tr>
							<tr>
								<td>파일첨부</td>
								<td>
									<span class="spanFile"><input type="file" name="fileName" id="fileName"></span>
								</td>
							</tr>
							<tr>
								<td>내용타입</td>
								<td>
									<label><input type="radio" name="contentType" value="HTML"><span>HTML</span></label>
									<label><input type="radio" name="contentType" value="TEXT" checked ><span>TEXT</span></label>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2"><hr></td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="button" id="regBtn">등록</button>
									<button type="reset">다시쓰기</button>
									<button type="button" id="listBtn">리스트</button>
								</td>
							</tr>
						</tfoot>
					</table>
					
					<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>">
					<!--
					IP주소를 IPv4 형식으로 설정함.
					프로젝트 -> Run Configuration -> Tomcat -> Argument -> VM arguments의 제일 아랫 줄에
					-Djava.net.preferIPv4Stack=true
					입력
					-->
				</form>
				
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