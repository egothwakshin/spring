package webspring;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconfig {
	Connection con = null;
	public Connection dbinfo() throws Exception{
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/cms";
		String user = "hana";
		String pass = "hana1234";
		
		Class.forName(driver);
		this.con = DriverManager.getConnection(url,user,pass);
		
		return this.con;
	}
}
