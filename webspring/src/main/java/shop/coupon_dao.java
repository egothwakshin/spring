package shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class coupon_dao {
	int cidx,cprate;
	String cpname,cpuse,cpdate,indate;
	
	//select에 사용할 1차 클래스 배열
	public ArrayList<Object> list(){
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(getCidx());
		al.add(getCpname());
		al.add(getCprate());
		al.add(getCpuse());
		al.add(getCpdate());
		al.add(getIndate());
		//al.removeAll(Arrays.asList("",null));
		al.removeAll(Collections.singletonList(null));
		return al;
	}
	
}
