package com.example.sitwith;

import java.util.Date;

public class Table {
	Table(){}
	
	Table(String id,Date time){
		this.id = id;
		this.time = time;
	}
	
	public String id;
	
	public Date time = new Date();

}
