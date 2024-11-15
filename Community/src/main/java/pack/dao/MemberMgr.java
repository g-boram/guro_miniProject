package pack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import pack.dbcp.DBCP;
import pack.dto.MemberBean;
import pack.dto.ZipcodeBean;

public class MemberMgr {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet objRS = null;
	PreparedStatement pstmt = null;
	DBCP dbcp = null;
	
	/* 아이디 중복 검사 시작(/member/idCheck.jsp) */
	public boolean checkId(String id) {
		
		boolean res = false; 		// 임시 초기화, ID 사용 가능여부를 판별하는 변수
									// true면 입력한 ID는 사용불가
									// false면 입력한 ID는 사용가능
		
		try {
			conn = dbcp.mtdConn();
			
			String sql = "select count(*) from member where uId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			objRS = pstmt.executeQuery();
			
			if(objRS.next()) {
				int recordCnt = objRS.getInt(1);
				if (recordCnt == 1) {
					res = true;
				}
			}
			
		} catch (Exception e) {
			System.out.println("ID 중복검사 중 오류 발생 : " + e.getMessage());
		} finally {
			try { objRS.close(); } catch (Exception e) {System.out.println(e.getMessage());}
			try { pstmt.close(); } catch (Exception e) {System.out.println(e.getMessage());}
			try { conn.close(); } catch (Exception ex) {System.out.println(ex.getMessage());}
		}
		
		return res;
	}
	/* 아이디 중복 검사 끝(/member/idCheck.jsp) */
	
	/* 우편번호 찾기 시작(/member/zipCheck.jsp) */
	public List<ZipcodeBean> zipcodeRead(String area3) {
		
		List<ZipcodeBean> objList = new Vector<>();
		
		try {
			
			conn = dbcp.mtdConn();
			
			String sql = "select zipcode, area1, area2, area3, area4 ";
			sql += "from tblZipcode where area3 like ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + area3 + "%");
			objRS = pstmt.executeQuery();
			
			while(objRS.next()) {
				ZipcodeBean zipBean = new ZipcodeBean();
				zipBean.setZipcode(objRS.getString(1));
				zipBean.setArea1(objRS.getString(2));
				zipBean.setArea2(objRS.getString(3));
				zipBean.setArea3(objRS.getString(4));
				zipBean.setArea4(objRS.getString(5));
				
				objList.add(zipBean);
			}
			
		} catch (Exception e) {
			System.out.println("오류발생 : " + e.getMessage());
		} finally {
			try { objRS.close(); } catch (Exception e) {System.out.println(e.getMessage());}
			try { pstmt.close(); } catch (Exception e) {System.out.println(e.getMessage());}
			try { conn.close(); } catch (Exception e) { System.out.println(e.getMessage()); }
		}
		
		return objList;
	}
	/* 우편번호 찾기 끝(/member/zipCheck.jsp) */
	
	
	/* 회원가입 시작(/member/memberProc.jsp) */
	public boolean insertMember(MemberBean bean) {
		boolean flag = false;
		
		try {
			
			conn = dbcp.mtdConn();
			
			String sql = "insert into member (";
			sql += "uId, uPw, uName, uEmail, gender, uBirthday, ";
			sql += "uZipcode, uAddr, uHobby, uJob, joinTM) values (";
			sql += "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getuId());
			pstmt.setString(2, bean.getuPw());
			pstmt.setString(3, bean.getuName());
			pstmt.setString(4, bean.getuEmail());
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getuBirthday());
			pstmt.setString(7, bean.getuZipcode());
			pstmt.setString(8, bean.getuAddr());
			String[] hobby = bean.getuHobby(); 	// {"여행", "게임", "운동"}
			
//			수정 시작
			// 배열 null 체크 추가
			if(hobby == null) {					
				hobby = new String[0];
			}
			
			String[] hobbyName = {"인터넷", "여행", "게임", "영화", "운동"};
			char[] hobbyCode = {'0', '0', '0', '0', '0'};
			
			// hobbyName과 hobby의 길이가 다를 때를 대비
			for (int i=0; i<hobby.length; i++) {		
				for(int j=0; j<hobbyName.length; j++) {
					if (hobby[i].equals(hobbyName[j])) {
						hobbyCode[j] = '1';
					}
				}
			}
			
			// char 배열을 String으로 변환 시 문제 예방 추가
			StringBuilder hobbyCodeStr = new StringBuilder();
			for(char c : hobbyCode) {
				hobbyCodeStr.append(c);
			}
			pstmt.setString(9, hobbyCodeStr.toString());
//			수정 끝
			pstmt.setString(10, bean.getuJob());
			
			int rowCnt = pstmt.executeUpdate();
			
			if(rowCnt == 1) {
				flag = true;
			}
			
		} catch (Exception e) {
			System.out.println("회원가입 중 오류발생 : " + e.getMessage());
		} finally {
			try { pstmt.close(); } catch (Exception ex) {System.out.println(ex.getMessage());}
			try { conn.close(); } catch (Exception e) {System.out.println(e.getMessage());}
		}
		
		return flag;
	}
	/* 회원가입 끝(/member/memberProc.jsp) */
	
	/* 로그인 처리 시작(/member/loginProc.jsp) */
	public boolean loginMember(String id, String pw) {
		
		boolean loginChkTF = false;
		
		try {
			conn = dbcp.mtdConn();
			
			String sql = "select count(*) from member where uId = ? and uPw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			objRS = pstmt.executeQuery();
			
			if(objRS.next()) {
				
				int recordCnt = objRS.getInt(1);
				if (recordCnt == 1) loginChkTF = true;
			}
			
		} catch (Exception e) {
			System.out.println("로그인 처리 중 오류 발생 : " + e.getMessage());
		} finally {
			try {objRS.close();} catch (Exception e) {System.out.println(e.getMessage());}
			try {pstmt.close();} catch (Exception e) {System.out.println(e.getMessage());}
			try {conn.close();} catch (Exception e) {System.out.println(e.getMessage());}
		}
		
		return loginChkTF;
	}
	/* 로그인 처리 끝(/member/loginProc.jsp) */
	
	/* 회원정보 수정 시작(/member/memberModProc.jsp) */
	public MemberBean modifyMember(String id) {		
		
		MemberBean mBean = new MemberBean();
		
		try {
			
			conn = dbcp.mtdConn();
			
			String sql = "select uId, uPw, uName, uEmail, gender, uBirthday, ";
			sql += "uZipcode, uAddr, uHobby, uJob, joinTM ";
			sql += "from member where uId = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			objRS = pstmt.executeQuery();
			
			if(objRS.next()) {
				mBean.setuId(objRS.getString("uId"));
				mBean.setuPw(objRS.getString("uPw"));
				mBean.setuName(objRS.getString("uName"));
				mBean.setuEmail(objRS.getString("uEmail"));
				mBean.setGender(objRS.getString("gender"));
				mBean.setuBirthday(objRS.getString("uBirthday"));
				mBean.setuZipcode(objRS.getString("uZipcode"));
				mBean.setuAddr(objRS.getString("uAddr"));
				String[]hobbyAry = new String[5];
				String hobby = objRS.getString("uHobby");
				hobbyAry = hobby.split("");
				System.out.println("회원정보수정 split 결과 : " + Arrays.toString(hobbyAry));
				mBean.setuHobby(hobbyAry);
				mBean.setuJob(objRS.getString("uJob"));
			}
			
		} catch (Exception e) {
			System.out.println("회원정보 수정 중 오류발생 : " + e.getMessage());
		} finally {
			try {objRS.close();} catch (Exception e) {System.out.println(e.getMessage());}
			try {pstmt.close();} catch (Exception e) {System.out.println(e.getMessage());}
			try {conn.close();} catch (Exception e) {System.out.println(e.getMessage());}
		}
		
		return mBean;
	}
	/* 회원정보 수정 끝(/member/memberModProc.jsp) */
	
	/* 회원정보 업데이트 시작 */
	public boolean updateMember(MemberBean mBean) {
		
		
		boolean result = false;
		try {
			
			conn = dbcp.mtdConn();
			
			String sql = "update member set uPw=?, uName=?, uEmail=?, uBirthday=?, uZipcode=?, ";
			sql += "uAddr=?, uHobby=?, uJob=?, gender=? where uId=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mBean.getuPw());
			pstmt.setString(2, mBean.getuName());
			pstmt.setString(3, mBean.getuEmail());
			pstmt.setString(4, mBean.getuBirthday());
			pstmt.setString(5, mBean.getuZipcode());
			pstmt.setString(6, mBean.getuAddr());
			
			String[] hobby = mBean.getuHobby(); 	// {"여행", "게임", "운동"}
			
			if(hobby == null) {					
				hobby = new String[0];
			}
			
			String[] hobbyName = {"인터넷", "여행", "게임", "영화", "운동"};
			char[] hobbyCode = {'0', '0', '0', '0', '0'};
			
			// hobbyName과 hobby의 길이가 다를 때를 대비
			for (int i=0; i<hobby.length; i++) {		
				for(int j=0; j<hobbyName.length; j++) {
					if (hobby[i].equals(hobbyName[j])) {
						hobbyCode[j] = '1';
					}
				}
			}
			
			// char 배열을 String으로 변환 시 문제 예방 추가
			StringBuilder hobbyCodeStr = new StringBuilder();
			for(char c : hobbyCode) {
				hobbyCodeStr.append(c);
			}
			
			pstmt.setString(7, hobbyCodeStr.toString());
			pstmt.setString(8, mBean.getuJob());
			pstmt.setString(9, mBean.getGender());
			pstmt.setString(10, mBean.getuId());
			
			int rowCnt = pstmt.executeUpdate();
			
			if(rowCnt == 1) {
				result = true;
			}
			
			
		} catch (Exception e) {
			System.out.println("회원정보 수정 중 오류발생 : " + e.getMessage());
		} finally {
			try {pstmt.close();} catch (Exception e) {System.out.println(e.getMessage());}
			try {conn.close();} catch (Exception e) {System.out.println(e.getMessage());}
		}
		
		return result;
	}
	/* 회원정보 업데이트 끝 */
	
	/* 회원탈퇴 시작 */
	public boolean deleteMember(String uId) {
		
		boolean chk = false;
		
		try {
			
			conn = dbcp.mtdConn();
			
			String sql = "delete from member where uId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			int resCnt = pstmt.executeUpdate();
			if (resCnt == 1) chk = true;
			
			
		} catch (Exception e) {
			System.out.println("회원 탈퇴 중 오류 : " + e.getMessage());
		} finally {
			try {pstmt.close();} catch (Exception e) {System.out.println(e.getMessage());}
			try {conn.close();} catch (Exception e) {System.out.println(e.getMessage());}
		}
		
		return chk;
	}
	/* 회원탈퇴 끝 */
	
	/* 로그인 사용자 이름 반환(/bbs/write.jsp) 시작 */
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
	/* 로그인 사용자 이름 반환(/bbs/write.jsp) 끝 */
	
}
