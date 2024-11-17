package pack.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pack.dbcp.DBCP;
import pack.dto.GalleryBean;

public class ManagerMgr {

	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	ResultSet objRS = null;
	
	DBCP dbcp = null;
	
	private static final String SAVEFOLDER = "/Users/goboram_1/git/guro_miniProject/Community/src/main/webapp/fileupload/gallery";
	private static String encType = "UTF-8";
	private static int maxSize = 10 * 1024 * 1024;
	
	/* 갤러리 게시글 등록  */
	public void insertGallery(HttpServletRequest req) {
		
		String sql = null;
		MultipartRequest multi = null;
		int fileSize = 0;
		String fileName = null;
		
		try {
			conn = DBCP.mtdConn();
			sql = "select max(num) from tblgallery";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int ref = 1; 	// 답변글 작성용, 원본글의 글번호(num)와 일치
			if(rs.next()) ref = rs.getInt(1) + 1;
			
			File file = new File(SAVEFOLDER);
			if(!file.exists()) file.mkdirs();
			
			multi = new MultipartRequest(req, SAVEFOLDER, maxSize, encType, new DefaultFileRenamePolicy());
			
			if(multi.getFilesystemName("fileName") != null) {
				fileName = multi.getFilesystemName("fileName");
				fileSize = (int) multi.getFile("fileName").length();
			}
			
			String content = multi.getParameter("content");
			if(multi.getParameter("contentType").equalsIgnoreCase("TEXT")) {
				content = content.replace("<", "&lt;");
			}
			
			sql = "insert into tblgallery (";
			sql += "uId, uName, subject, category, content, ref, pos, depth, regTM, ip, readCnt, filename, fileSize) ";
			sql += "values (?, ?, ?, ?, ?, ?, 0, 0, now(), ?, 0, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("uId"));
			pstmt.setString(2, multi.getParameter("uName"));
			pstmt.setString(3, multi.getParameter("subject"));
			pstmt.setString(4, multi.getParameter("category"));
			pstmt.setString(5, content);
			pstmt.setInt(6, ref);
			pstmt.setString(7, multi.getParameter("ip"));
			pstmt.setString(8, fileName);
			pstmt.setInt(9, fileSize);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("갤러리 게시글 입력 중 오류 : " + e.getMessage());
		} finally {
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
		
	}

	

	public Vector<GalleryBean> getGalleryList(String keyField, String keyWord, int start, int end) {
		
		Vector<GalleryBean> vList = new Vector<>();
		String sql = null;
		
		try {
			conn = DBCP.mtdConn();
			
			if(keyWord.equals("null") || keyWord.equals("")) {
				// 검색어가 없을 경우
				sql = "select * from tblgallery order by ref desc, pos asc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			} else {
				// 검색어가 있을 경우
				sql = "select * from tblgallery where " + keyField + " like ? order by ref desc, pos asc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GalleryBean bean = new GalleryBean();
				bean.setNum(rs.getInt("num"));
				bean.setuName(rs.getString("uName"));
				bean.setSubject(rs.getString("subject"));
				bean.setPos(rs.getInt("pos"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegTM(rs.getString("regTM"));
				bean.setReadCnt(rs.getInt("readCnt"));
				vList.add(bean);
			}
		} catch (Exception e) {
			System.out.println("게시판 리스트 출력 중 오류 : " + e.getMessage());
		} finally {
			try { rs.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
		
		return vList;
	}
	/* 게시판 리스트 출력(/bbs/list.jsp) 끝 */
	
	/* 총 게시물 수(/bbs/list.jsp) 시작 */
	public int getTotalCount(String keyField, String keyWord) {
		
		String sql = null;
		int totalCnt = 0;
		
		try {
			
			conn = DBCP.mtdConn();
			
			if (keyWord.equals("null") || keyWord.equals("")) {
				sql = "select count(*) from tblBoard";
				pstmt = conn.prepareStatement(sql);
			} else {
				sql = "select count(*) from tblBoard where " + keyField + " like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCnt = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("총 게시물 수 오류 : " + e.getMessage());
		} finally {
			try { rs.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
		
		return totalCnt;
	}
	/* 총 게시물 수(/bbs/list.jsp) 끝 */
	
	/* 게시판 뷰페이지 조회수 증가 시작(/bbs/read.jsp, 내용보기 페이지) */
	public void upCount(int num) {
		String sql = null;
		
		try {
			conn = DBCP.mtdConn();
			sql = "update tblBoard set readCnt = readCnt+1 where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("조회수 증가 중 오류 : " + e.getMessage());
		} finally {
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
	}
	/* 게시판 뷰페이지 조회수 증가 끝 (/bbs/read.jsp, 내용보기 페이지) */
	
	/* 상세보기 페이지 게시글 출력 시작(/bbs/read.jsp, 내용보기 페이지) */
	public GalleryBean getBoard(int num) {
		String sql = null;
		
		GalleryBean bean = new GalleryBean();
		
		try {
			conn = DBCP.mtdConn();
			sql = "select * from tblBoard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setNum(rs.getInt("num"));
				bean.setuId(rs.getString("uId"));
				bean.setuName(rs.getString("uName"));
				bean.setSubject(rs.getString("subject"));
				bean.setContent(rs.getString("content"));
				bean.setPos(rs.getInt("pos"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegTM(rs.getString("regTM"));
				bean.setReadCnt(rs.getInt("readCnt"));
				bean.setFileName(rs.getString("fileName"));
				bean.setFileSize(rs.getInt("fileSize"));
				bean.setIp(rs.getString("ip"));
			}
			
		} catch (Exception e) {
			System.out.println("게시글 출력 중 오류 : " + e.getMessage());
		} finally {
			try { rs.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
		
		return bean;
	}
	/* 상세보기 페이지 게시글 출력 끝(/bbs/read.jsp, 내용보기 페이지) */
	

	
	/* 게시글 삭제(/bbs/delete.jsp) 시작 */
	public int deleteBoard(int num) {
		
		String sql = null;
		
		int exeCnt = 0;
		
		try {
			conn = DBCP.mtdConn();
			
			////////// 게시글의 파일 삭제 시작 //////////
			sql = "select fileName from tblBoard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next() && rs.getString(1) != null) {
				if (!rs.getString(1).equals("")) {
					String fName = rs.getString(1);
					
					String fileSrc = SAVEFOLDER + "/" + fName;
					File file = new File(fileSrc);
					
					if(file.exists()) {
						file.delete();
					}
				}
			}
			////////// 게시글의 파일 삭제 끝 //////////
			
			////////// 게시글의 삭제 시작 //////////
			sql = "delete from tblBoard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			exeCnt = pstmt.executeUpdate();
			////////// 게시글의 삭제 끝 //////////
			
		} catch (Exception e) {
			System.out.println("게시글 삭제 중 오류 : " + e.getMessage());
		} finally {
			try { rs.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
		
		return exeCnt;
	}
	/* 게시글 삭제(/bbs/delete.jsp) 끝 */
	
	/* 게시글 수정페이지 (/bbs/updateProc.jsp) 시작 */
	public int updateBoard(HttpServletRequest req) {
		 String sql = null;
		 int exeCnt = 0;
		 MultipartRequest multi = null;
		 int fileSize = 0;
		 String fileName = "";
		 
		 try {
			 conn = DBCP.mtdConn();
			 
			 File file = new File(SAVEFOLDER);
			 if(!file.exists()) file.mkdirs();
			
			 multi = new MultipartRequest(req, SAVEFOLDER, maxSize, encType, new DefaultFileRenamePolicy());
			
			 
		 	 if(multi.getFilesystemName("fileName") != null) {
		 		 fileName = multi.getFilesystemName("fileName");
		 		 fileSize = (int) multi.getFile("fileName").length();
			 }
			
			 String content = multi.getParameter("content");
			 if(multi.getParameter("contentType").equalsIgnoreCase("TEXT")) {
				content = content.replace("<", "&lt;");
			 }
			 
//			 System.out.println(multi.getParameter("uName"));
//			 System.out.println(multi.getParameter("subject"));
//			 System.out.println(multi.getParameter("content"));
//			 System.out.println(multi.getFilesystemName("fileName"));
//			 System.out.println(Integer.parseInt(multi.getParameter("num")));
			 
			 sql = "update tblBoard set uName=?, subject=?, content=?, filename=?, fileSize=? where num=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, multi.getParameter("uName"));
			 pstmt.setString(2, multi.getParameter("subject"));
			 pstmt.setString(3, multi.getParameter("content"));
			 pstmt.setString(4, fileName);
			 pstmt.setInt(5, fileSize);
			 pstmt.setInt(6, Integer.parseInt(multi.getParameter("num")));
			 exeCnt = pstmt.executeUpdate();
		 } catch (Exception e) {
			 System.out.println("Exception : " + e.getMessage());
		 } finally {
			 try { pstmt.close(); } catch (Exception ex) { System.out.println(ex.getMessage());}
			 try { conn.close(); } catch (Exception ex) { System.out.println(ex.getMessage());}
		 }
		
		 return	exeCnt;
	 }
	/* 게시글 수정페이지 (/bbs/updateProc.jsp) 끝 */
	
	
	public String getMemberName(String uId) {
		String uName = "";
		String sql = null;
		
		try {
			
			conn = dbcp.mtdConn();
			sql = "select uName from member where uId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			
			objRS = pstmt.executeQuery();
			if(objRS.next()) {
				uName = objRS.getString(1);
			}
			
		} catch (Exception e) {
			System.out.println("로그인 사용자 이름 반환 중 오류발생 : " + e.getMessage());
		} finally {
			try {objRS.close();} catch (Exception e) {System.out.println(e.getMessage());}
			try {pstmt.close();} catch (Exception e) {System.out.println(e.getMessage());}
			try {conn.close();} catch (Exception e) {System.out.println(e.getMessage());}
		}
		
		return uName;
	}
}




