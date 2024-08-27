package api;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class pointdao {
	int uidx,upoint;
	String uid,uname,udate;
	
	public ArrayList<Object> data(){
	ArrayList<Object> onedata = new ArrayList<Object>();
	onedata.add(this.getUidx());
	onedata.add(this.getUid());
	onedata.add(this.getUpoint());
	onedata.add(this.getUname());
	onedata.add(this.getUdate());
	
	return onedata;
	}
}
