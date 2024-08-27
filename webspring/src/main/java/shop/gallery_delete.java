package shop;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;

public class gallery_delete {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String result = "";	//결과값을 Contoroller로 이관하는 변수
	
	shop_main2 sm;
	
	public String deleteok(BasicDataSource dbinfo,HttpServletRequest req) throws Exception {
		try {
			//파일삭제
			this.con = dbinfo.getConnection();
			String query = "select gfile from gallery where gidx=? order by gidx desc";
			this.ps = this.con.prepareStatement(query);
			this.ps.setInt(1, sm.gidx);
			this.rs = this.ps.executeQuery();
			this.rs.next();
			//첨부파일이 있을 경우 삭제하는 조건문
			if(this.rs.getString(1)!=null) {	
				String filename[] = this.rs.getString(1).split(",");
				String url = req.getServletContext().getRealPath("/upload/");				
				int w = 0;
				while(w<filename.length) {				
					File f = new File(url+filename[w]);
					f.delete();		//파일삭제를 실행하는 메소드
					w++;
				}
			}
			
			//DB에 저장된데이터 삭제			
			String sql = "delete from gallery where gidx=? order by gidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setInt(1, sm.gidx);
			this.ps.executeUpdate();
			this.result = "Y";			
		}catch(Exception e) {
			this.result = "N";
		}finally {
			this.ps.close();
		}		
		return result;
	}
	
	//게시물 삭제 (내가 작성한 코드)
	/*
	public int gallery_delete(BasicDataSource dbinfo){
		System.out.println(sm.gidx);
		int result = 0;
		try {
			this.con = dbinfo.getConnection();
			String sql = "delete from gallery where gidx=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setInt(1, sm.gidx);
			result = this.ps.executeUpdate();
			//System.out.println(result); //삭제되면 1, 삭제안되면 0
		}catch(Exception e) {
			System.out.println("error");
		}
		
		return result;
	}
	*/
}
