package shop;

//한개의 데이터값 및 수정 module
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbcp.BasicDataSource;

public class coupon_update {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	/*
	1. DB데이터 1개의 값을 dao로 setter값 모두 적용 후
	   Controller에서 해당 배열을 Model -> View 적용
	2. 해당 module에서 1차 배열을 미리 만든 후
	   Controller로 return 후 Model -> View 적용
	  */
	
	public void update_golist(BasicDataSource dbinfo, coupon_dao dao) throws Exception{
		
		try {
			String sql = "update coupon set cpname=?,cprate=?,cpuse=?,cpdate=? where cidx=? ";
			this.con = dbinfo.getConnection();
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dao.getCpname());
			this.ps.setInt(2, dao.getCprate());
			this.ps.setString(3, dao.getCpuse());
			this.ps.setString(4, dao.getCpdate());
			this.ps.setInt(5, dao.getCidx());
			int result = this.ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("error");
		}finally {
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
	}
	
	
	//new를 사용하지 않을 경우 dao는 Module과 Controller 모두 동일한 값을 공유할 수 있음
	public void select_one(BasicDataSource dbinfo, coupon_dao dao, int cidx) throws Exception {
		try {
			String sql = "select * from coupon where cidx=?";
			this.con = dbinfo.getConnection();
			this.ps = this.con.prepareStatement(sql);
			this.ps.setInt(1, cidx);
			this.rs = this.ps.executeQuery();
			this.rs.next();
			
			//database의 하나의 row값을 dao의 setter 메소드에 값을 이관
			dao.setCidx(Integer.parseInt(this.rs.getString(1)));
			dao.setCpname(this.rs.getString(2));
			dao.setCprate(Integer.parseInt(this.rs.getString(3)));
			dao.setCpuse(this.rs.getString(4));
			dao.setCpdate(this.rs.getString(5));
			dao.setIndate(this.rs.getString(6));
			
			
		}catch(Exception e) {
			System.out.println("error");
		}finally {
			this.rs.close();
			this.ps.close();
			this.con.close();
		}
		
	}
}
