package com.example.sitwith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Table {
	Table(){}
	
	Table(String id,Date time){
		this.id = id;
		this.time = time;
	}
	
	public String id;
	
	public int count;
	
	public String notificationStatus;
	
	public Date time = new Date();
	
	public List<User> users = new ArrayList<User>();

}
