package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp.BasicDataSource;

//쿠폰 생성 module 파일
public class coupon_insert {
	
	Connection con = null;
	PreparedStatement ps = null;
	String rs = null; //결과값 return 받을 변수
	

	
	//삭제 기능 추가
	public String del_result(BasicDataSource dbinfo, int cidx) throws Exception {	

		try {	
			this.con = dbinfo.getConnection();
			String sql = "delete from coupon where cidx=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setInt(1, cidx);
			this.ps.executeUpdate();
			this.rs = "Y";
			
		}catch(Exception e) {
			System.out.println("DB 쿼리문 오류발생!!");
			this.rs = "N";
		}finally {
			this.ps.close();
			this.con.close();			
		}		
		return this.rs;	
	}
	
	
	
	
	//DB정보 및 setter값을 받아서 insert 실행
	public String result(BasicDataSource dbinfo,coupon_dao dao) {
		try {
			con = dbinfo.getConnection();
			String sql = "insert into coupon values('0',?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dao.getCpname());
			this.ps.setInt(2, dao.getCprate());
			this.ps.setString(3, dao.getCpuse());
			this.ps.setString(4, dao.getCpdate());
			this.ps.executeUpdate();
			this.rs = "Y";	//승인확정
			this.ps.close();
			this.con.close();
		}catch(Exception e) {
			//System.out.println("DB연결 오류!");
			this.rs = "N";	//오류발생
		}
		return this.rs;	//결과값 return
	}

	
}
