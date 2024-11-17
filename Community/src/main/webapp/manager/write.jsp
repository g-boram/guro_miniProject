<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="mMgr" class="pack.dao.ManagerMgr" scope="page" />
<%
String uId = (String)session.getAttribute("uId_Session");
String uName = mMgr.getMemberName(uId);
String category = request.getParameter("category");
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
	<script src="/script/script_Manager.js"></script>
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
			<div id="contents" class="bbsWrite">

				<h2>글쓰기</h2>			
				
				<form name="writeFrm" enctype="multipart/form-data" method="post" id="writeFrm">
				
					<table>
						<tbody>
							<tr>
								<td>카테고리</td>	
								<td>
									<%=category %>
									<input type="hidden" name="category" value="<%=category %>">
								</td>
							</tr>
							<tr>
								<td class="req">성명</td>
								<td>
									<%=uId %>
									<input type="hidden" name="uId" value="<%=uId %>">
									<input type="hidden" name="uName" value="<%=uName %>">
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
									<label><input type="radio" name="contentType" value="TEXT"><span>TEXT</span></label>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2"></td>
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
				</form>
			</div>
		</main>
		<%@ include file="/ind/footerTmp.jsp" %>
	</div>
</body>
</html>    