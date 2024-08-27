package shop;

import java.io.File;
import java.sql.Connection;
/*
	1. 파일명이 개발자에 맞게 이름이 변경
	2. 파일이 웹디렉토리 경로에 저장
	3. Database에 해당 컬럼에 경로가 저장
 */
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
public class file_save {
	Connection con = null;
	PreparedStatement ps = null;
	String result = "";	//결과값 (Y:성공, N:실패)
	
	//파일명을 랜덤메소드를 이용하여 생성
	public String rename() {
		Date day = new Date();	//sql 아니고 util 로 가져와야함
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String today = sf.format(day);
		
		int no = (int)Math.ceil(Math.random()*1000);
		String datacode = today + no;
		return datacode;
	}
	
	//파일 저장 및 Database에 insert
	public String datafile_save(BasicDataSource dbinfo,
			gallery_dao dao,
			MultipartFile files[],
			HttpServletRequest req) throws Exception {		
		this.con = dbinfo.getConnection();
		String sql = "";
		
		
		String ori_name = "";	//사용자가 업로드한 파일명
		String new_name = "";	//개발자가 사용자가 업로드한 파일명을 다른이름으로 변경
		
		//첨부파일이 있을 경우
		if(files[0].getSize() > 0) {
			//여러개의 파일명을 하나의 컬럼에 저장하기 위한 배열
			ArrayList<String> al = new ArrayList<String>();	//원본명
			ArrayList<String> al2 = new ArrayList<String>();	//사본명
			
			//Module에서 웹 경로를 로드하여 저장시킴
			String url = req.getServletContext().getRealPath("/upload/");
			
			int w=0;
			while(w<files.length) {
				al.add(files[w].getOriginalFilename());
				//사본명 만드는 코드
				int com = files[w].getOriginalFilename().indexOf(".");
				String wd = files[w].getOriginalFilename().substring(com);
				//System.out.println(wd);
				String refilename = this.rename()+wd;
				al2.add(refilename);
				//웹디렉토리에 파일명이 변경되어서 저장됨
				FileCopyUtils.copy(files[w].getBytes(),new File(url+refilename));
				

				w++;
			}
			//String.join : 클래스배열을 문자열 형태로 변환해주는 메소드.("," 의미 : 콤마로 구분해줌)
 			ori_name = String.join(",",al);
			new_name = String.join(",", al2);
			
			sql = "insert into gallery (gidx,gwriter,gsubject,gtext,gorifile,gfile,gindate)"
					+ " values('0',?,?,?,?,?,now())";
			
		}else {	//첨부파일이 없을 경우
			sql = "insert into gallery (gidx,gwriter,gsubject,gtext,gindate)"
					+ " values('0',?,?,?,now())";
		}
		try {
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dao.getGwriter());
			this.ps.setString(2, dao.getGsubject());
			this.ps.setString(3, dao.getGtext());
			if(ori_name!="") {
				this.ps.setString(4, ori_name);
				this.ps.setString(5, new_name);
			}
			this.ps.executeUpdate();
			this.result = "Y";
		}catch(Exception e) {	//sql문법오류 발생시 작동
			this.result = "N";
		}finally {
			this.ps.close();
		}
		return result;
	}
	

}
