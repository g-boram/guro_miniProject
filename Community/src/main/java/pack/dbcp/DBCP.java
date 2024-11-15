package pack.dbcp;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCP {
	
	static Connection conn = null;
	
	public static Connection mtdConn() throws Exception {
		
		Context init = new InitialContext();
		DataSource dataSource = (DataSource)init.lookup("java:comp/env/jdbc/dbcpConn");
		
		conn = dataSource.getConnection();
		
		System.out.println("DAO DBCP 접속 OK!!!");
		
		return conn;
		
	}
	
}
