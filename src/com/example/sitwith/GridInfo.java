package com.example.sitwith;

public class GridInfo {

	private String name;
	private int imageID;
	
	public GridInfo(String name,int imageID) {
		super();
		this.name = name;
		this.setImageID(imageID);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	
}
