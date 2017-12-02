package hyun.db;

import java.sql.Connection;

import javax.naming.*;
import javax.sql.*;

public class HyunDB {
	static DataSource ds;
	static{
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException e){
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public static Connection getConn() throws Exception{
		return ds.getConnection();
	}
}
