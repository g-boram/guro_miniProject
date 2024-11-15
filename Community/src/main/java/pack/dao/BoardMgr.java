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
import pack.dto.BoardBean;

public class BoardMgr {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	private static final String SAVEFOLDER = "D:\\git_workSpace\\guroAi_MiniProj\\Community\\src\\main\\webapp\\fileupload";
	private static String encType = "UTF-8";
	private static int maxSize = 10 * 1024 * 1024;
	
	/* 게시판 입력(/bbs/postProc.jsp) 시작 */
	public void insertBoard(HttpServletRequest req) {
		
		String sql = null;
		MultipartRequest multi = null;
		int fileSize = 0;
		String fileName = null;
		
		try {
			conn = DBCP.mtdConn();
			sql = "select max(num) from tblBoard";
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
			
			sql = "insert into tblBoard (";
			sql += "uId, uName, subject, content, ref, pos, depth, regTM, ip, readCnt, filename, fileSize) ";
			sql += "values (?, ?, ?, ?, ?, 0, 0, now(), ?, 0, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("uId"));
			pstmt.setString(2, multi.getParameter("uName"));
			pstmt.setString(3, multi.getParameter("subject"));
			pstmt.setString(4, content);
			pstmt.setInt(5, ref);
			pstmt.setString(6, multi.getParameter("ip"));
			pstmt.setString(7, fileName);
			pstmt.setInt(8, fileSize);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("게시판 입력 중 오류 : " + e.getMessage());
		} finally {
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
		
	}
	/* 게시판 입력(/bbs/postProc.jsp) 끝 */
	
	/* 게시판 리스트 출력(/bbs/list.jsp) 시작 */
	public Vector<BoardBean> getBoardList(String keyField, String keyWord, int start, int end) {
		
		Vector<BoardBean> vList = new Vector<>();
		String sql = null;
		
		try {
			conn = DBCP.mtdConn();
			
			if(keyWord.equals("null") || keyWord.equals("")) {
				// 검색어가 없을 경우
				sql = "select * from tblBoard order by ref desc, pos asc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			} else {
				// 검색어가 있을 경우
				sql = "select * from tblBoard where " + keyField + " like ? order by ref desc, pos asc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean bean = new BoardBean();
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
	public BoardBean getBoard(int num) {
		String sql = null;
		
		BoardBean bean = new BoardBean();
		
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
	
	/* 상세보기 페이지 파일다운로드 시작 (/bbs/read.jsp) */
	public static int len;
	
	public void downLoad(HttpServletRequest req, HttpServletResponse res, JspWriter out, PageContext pageContext) {
		String fileName = req.getParameter("fileName");
		try {
			File file = new File(SAVEFOLDER + File.separator + fileName);
			
			byte[] b = new byte[(int) file.length()];
			res.setHeader("Accept-Ranges", "bytes");
			req.getHeader("User-Agent");
			res.setContentType("application/smnet;charset=utf-8");
			res.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ";");
			
			out.clear();
			out = pageContext.pushBody();
			
			if (file.isFile()) {
				BufferedInputStream fIn	= new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream fOuts = new BufferedOutputStream(res.getOutputStream());
				int read = 0;
				while ((read = fIn.read(b)) != -1) {
					fOuts.write(b, 0, read);
				}
				fOuts.close();
				fIn.close();
			}
		} catch (Exception e) {
			System.out.println("파일 다운로드 중 오류 : " + e.getMessage());
		}
	}
	/* 상세보기 페이지 파일다운로드 끝 (/bbs/read.jsp) */
	
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
	
	/* 게시글 답변 페이지 (/bbs/replyProc.jsp) 시작 */
	public int replyBoard(BoardBean bean) {
		
		String sql = null;
		int cnt = 0;
		
		try {
			conn = DBCP.mtdConn();
			
			sql = "insert into tblBoard (";
			sql += "uId, uName, content, subject, ref, pos, depth, regTM, readCnt, ip";
			sql += ") values (?, ?, ?, ?, ?, ?, ?, now(), 0, ?)";
			
			int depth = bean.getDepth() + 1;
			int pos = bean.getPos();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getuId());
			pstmt.setString(2, bean.getuName());
			pstmt.setString(3, bean.getContent());
			pstmt.setString(4, bean.getSubject());
			pstmt.setInt(5, bean.getRef());
			pstmt.setInt(6, bean.getPos());
			pstmt.setInt(7, depth);
			pstmt.setString(8, bean.getIp());
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("게시글 답변 중 오류 : " + e.getMessage());
		} finally {
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
		
		return cnt;
	}
	/* 게시글 답변 페이지 (/bbs/replyProc.jsp) 끝 */
	
	
	/* 답변글 끼어들기 시작 (/bbs/replyProc.jsp) */
	public int replyUpBoard(int ref, int pos) {
		
		String sql = null;
		int cnt = 0;
		
		try {
			conn = DBCP.mtdConn();
			
			////////// 게시글의 포지션 증가 시작 //////////
			sql = "update tblBoard set pos = pos + 1 ";
			sql += "where ref = ? and pos > ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt( 1, ref);
			pstmt.setInt(2, pos);
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("답변글 끼어들기 중 오류 : " + e.getMessage());
		} finally {
			try { pstmt.close();} catch(Exception e) {System.out.println(e.getMessage());}
			try { conn.close();} catch(Exception e) {System.out.println(e.getMessage());}
		}
		
		return cnt;
	}
	/* 답변글 끼어들기 끝 (/bbs/replyProc.jsp) */
	
}
