<%@page import="pack.dto.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="bMgr" class="pack.dao.BoardMgr" scope="page" />

<%
request.setCharacterEncoding("UTF-8");
int numParam = Integer.parseInt(request.getParameter("num"));

// 검색어 수신 시작
String keyField = request.getParameter("keyField");
String keyWord = request.getParameter("keyWord");
// 검색어 수신 끝

// 현재 페이지 돌아가기 소스 시작
String nowPage = request.getParameter("nowPage");
// 현재 페이지 돌아가기 소스 끝

BoardBean bean = bMgr.getBoard(numParam);

int num = bean.getNum();
String uId = bean.getuId();
String uName = bean.getuName();
String subject = bean.getSubject();
String content = bean.getContent();
int pos = bean.getPos();
int ref = bean.getRef();
int depth = bean.getDepth();
String regTM = bean.getRegTM();
int readCnt = bean.getReadCnt();
String fileName = bean.getFileName();
double fileSize = bean.getFileSize();
String fUnit = "Bytes";
if(fileSize > 1024) {
	fileSize /= 1024;
	fUnit = "KBytes";
}

String ip = bean.getIp();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>수정페이지</title>
	<link rel="shortcut icon" href="#">
	<link rel="stylesheet" href="/style/style_Common.css?v">
	<link rel="stylesheet" href="/style/style_Template.css?v">
	<link rel="stylesheet" href="/style/style_BBS.css?v">
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
			
				<h2>글 수정하기</h2>
				
				<form name="modFrm" enctype="multipart/form-data" method="post" id="modFrm">
					
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
								<td><input type="text" name="subject" maxlength="50" id="subject" value="<%=subject %>"></td>
							</tr>
							<tr>
								<td class="contentTd">내용</td>
								<td><textarea name="content" id="content" cols="60" wrap="hard"><%=content %></textarea></td>
							</tr>
							<tr>
								<td>파일첨부</td>
								<td><span class="spanFile"><input type="file" name="fileName" id="fileName"></span></td>
							</tr>
							<tr>
								<td>내용타입</td>
								<td>
									<label><input type="radio" name="contentType" value="HTML"><span>HTML</span></label>
									<label><input type="radio" name="contentType" value="TEXT" checked><span>TEXT</span></label>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr><td colspan="2"><hr></td></tr>
							<tr>
								<td colspan="2">
									<button type="button" id="modProcBtn">수정하기</button>
									<button type="reset">다시쓰기</button>
									<button type="button" id="listBtn">리스트</button>
								</td>
							</tr>
						</tfoot>
					</table>
					<input type="hidden" name="ip" value="<%=request.getRemoteAddr() %>">					
					<input type="hidden" name="num" value="<%=num %>">
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
	
	<script src="/source/jquery-3.6.0.min.js"></script>
	<script src="/script/script_BBS.js"></script>
	
</body>
</html>    