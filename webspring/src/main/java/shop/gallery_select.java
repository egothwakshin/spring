package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.dbcp.BasicDataSource;

//갤러리 select 영역의 module
public class gallery_select {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
		//insert,delete,update
	
	
	
	
	//게시물 리스트
	public ArrayList<ArrayList<Object>> all_list(BasicDataSource dbinfo, gallery_dao dao) throws Exception{
		ArrayList<ArrayList<Object>> data = null;
		try {
			data = new ArrayList<ArrayList<Object>>();
			this.con = dbinfo.getConnection();
			String sql = "select gidx,gwriter,gsubject,gindate,gfile from gallery order by gidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()) {
				dao.setGidx(Integer.parseInt(this.rs.getString(1)));
				dao.setGwriter(this.rs.getString(2));
				dao.setGsubject(this.rs.getString(3));
				dao.setGindate(this.rs.getString(4));
				dao.setGfile(this.rs.getString(5));
				data.add(dao.list());
			}
			
		}catch(Exception e) {
			System.out.println("error");
		}finally {
			this.rs.close();
			this.ps.close();
		}
		
		return data;
	}
	
	//Controller의 클래스를 로드 (new 안써도됨)
	shop_main2 sm;		//프로세서가 이미 읽어서 로드가 된 상황
	gallery_dao dao = new gallery_dao(); //setter,getter 모두 사용
	//gallery_dao dao;					//getter만 사용
	
	//게시물 상세보기 페이지(Controller에 있는 static 변수형을 활용하여 select함)
	public ArrayList<Object> one_list(BasicDataSource dbinfo){

		try {
			this.con = dbinfo.getConnection();
			String sql= "select * from gallery where gidx=? order by gidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, String.valueOf(sm.gidx));
			this.rs = this.ps.executeQuery();
			this.rs.next();
			this.dao.setGidx(this.rs.getInt(1));
			this.dao.setGwriter(this.rs.getString(2));
			this.dao.setGsubject(this.rs.getString(3));
			this.dao.setGtext(this.rs.getString(4));
			this.dao.setGorifile(this.rs.getString(5));
			this.dao.setGfile(this.rs.getString(6));
			this.dao.setGindate(this.rs.getString(7));

			
			this.rs.close();
			this.ps.close();
			this.con.close();

		}catch(Exception e) {
			System.out.println("error");
		}
		
		return this.dao.views();
	}
}
