package shop;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class movie_dao {
	int midx,ticketing;
	String msubject,cinema,screen_date;
	
	public ArrayList<Object> data(){
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(getMidx());
		al.add(getMsubject());
		al.add(getCinema());
		al.add(getTicketing());
		al.add(getScreen_date());
		return al;
	}
}
