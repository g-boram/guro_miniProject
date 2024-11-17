<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이미지 갤러리</title>
    <link rel="stylesheet" href="/style/style_Gallery.css?v">
</head>
<body>
    <h1>이미지 갤러리</h1>
    <div class="gallery">
            <% 
                // galleryData가 null이 아니면 출력
                if (galleryData != null && !galleryData.isEmpty()) {
                    for (GalleryBean board : galleryData) {
            %>
                <div class="board-item">
                    <%-- 이미지 파일명이 있을 경우 이미지 표시 --%>
                    <% if (board.getFileName() != null && !board.getFileName().isEmpty()) { %>
                        <img src="../images/gallery/<%= board.getFileName() %>" alt="<%= board.getSubject() %>">
                    <% } %>
                    <div>
                        <h3><%= board.getSubject() %></h3>
                        <p>작성자: <%= board.getuName() %></p>
                        <p>작성일: <%= board.getRegTM() %></p>
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
</body>
</html>
