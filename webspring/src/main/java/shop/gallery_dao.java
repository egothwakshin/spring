package shop;

import java.util.ArrayList;
import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class gallery_dao {
	int gidx;
	String gwriter,gsubject,gtext;
	String gorifile,gfile;
	String gindate;
	
	
	//상세보기
	public ArrayList<Object> views(){
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(getGidx());	//0
		al.add(getGwriter());	//1
		al.add(getGsubject());	//2
		al.add(getGtext());	//3
		al.add(getGorifile());	//4 사용자가 올린 파일명
		al.add(getGfile());	//5 개발자가 바꾼 파일명
		al.add(getGindate());	//6
		return al;
	}
	//리스트출력
	public ArrayList<Object> list(){
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(getGidx());
		al.add(getGwriter());
		al.add(getGsubject());
		al.add(getGindate());
		//추가사항 (첨부파일 이미지 부분)
		al.add(getGfile());
		
		return al;
	}
}
