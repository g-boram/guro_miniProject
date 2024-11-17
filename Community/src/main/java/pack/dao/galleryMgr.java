package pack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import pack.dbcp.DBCP;
import pack.dto.GalleryBean;

public class galleryMgr {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;

    // 게시물 목록을 가져오는 메서드
    public Vector<GalleryBean> getBoardList(String keyField, String keyWord, int start, int end) {
        Vector<GalleryBean> vList = new Vector<>();
        String sql = null;

        try {
            conn = DBCP.mtdConn();

            // 검색어가 없을 때
            if (keyWord == null || keyWord.isEmpty()) {
                sql = "SELECT * FROM tblGallery ORDER BY ref DESC, pos ASC LIMIT ?, ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, start);
                pstmt.setInt(2, end);
            } else {
                // 검색어가 있을 때
                sql = "SELECT * FROM tblGallery WHERE " + keyField + " LIKE ? ORDER BY ref DESC, pos ASC LIMIT ?, ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%" + keyWord + "%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, end);
            }

            rs = pstmt.executeQuery();

            // 결과 처리
            while (rs.next()) {
                GalleryBean bean = new GalleryBean();
                bean.setNum(rs.getInt("num"));
                bean.setuName(rs.getString("uName"));
                bean.setSubject(rs.getString("subject"));
                bean.setPos(rs.getInt("pos"));
                bean.setRef(rs.getInt("ref"));
                bean.setDepth(rs.getInt("depth"));
                bean.setRegTM(rs.getString("regTM"));
                bean.setReadCnt(rs.getInt("readCnt"));
                bean.setFileName(rs.getString("filename")); // 이미지 파일명
                vList.add(bean);
            }
        } catch (Exception e) {
            System.out.println("갤러리 리스트 출력 중 오류 : " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { System.out.println(e.getMessage()); }
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) { System.out.println(e.getMessage()); }
            try { if (conn != null) conn.close(); } catch (Exception e) { System.out.println(e.getMessage()); }
        }

        return vList;
    }
}
