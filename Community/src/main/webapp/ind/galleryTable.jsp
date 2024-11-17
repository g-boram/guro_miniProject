<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector, pack.dto.GalleryBean" %>
<jsp:useBean id="gMgr" class="pack.dao.galleryMgr" />
 <%
    // GalleryBean 데이터를 gMgr에서 받아오기
    Vector<GalleryBean> galleryData = gMgr.getBoardList("", "", 0, 10); // 예시로 0부터 10까지의 데이터를 가져옴
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Gallery</title>
	<link rel="stylesheet" href="/style/style_Gallery.css?v">
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
				<h2>1500-1600</h2>
			    <div class="gallery">
		            <% 
		                // galleryData가 null이 아니면 출력
		                if (galleryData != null && !galleryData.isEmpty()) {
		                    for (GalleryBean board : galleryData) {
		            %>
	                <div class="boardRow">
	                    <%-- 이미지 파일명이 있을 경우 이미지 표시 --%>
	                    <div class="boardItem">
		                    <% if (board.getFileName() != null && !board.getFileName().isEmpty()) { %>
		                    	<div class="boardImg">
		                        	<img src="../fileupload/gallery/<%= board.getFileName() %>" alt="<%= board.getSubject() %>">
		                    	</div>
		                    <% } else { %>
		                   		<div>
		                        	<img src="../fileupload/gallery/<%= board.getFileName() %>" alt="<%= board.getSubject() %>">
		                    	</div>
		                    <% }  %>
		                    <div>
		                        <h4><%= board.getSubject() %></h4>
		                       
		                    </div>
	                    </div>
	                    
	                </div>
		            <% 
		                    }
		                } else {
		            %>
		                <p>게시물이 없습니다.</p>
		            <% 
		                }
		            %>
			    </div>
			</div>
				
		</main>
		<%@ include file="/ind/footerTmp.jsp" %>
	</div>
	<script src="/source/jquery-3.6.0.min.js"></script>
	<script src="/script/script.js"></script>
</body>
</html>    
