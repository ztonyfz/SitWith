package com.example.sitwith;

public class Restaurant {
	Restaurant() {
	};

	Restaurant(String id, String name, String neiborhood, String address) {
		super();
		this.id = id;
		this.name = name;
		this.neiborhood = neiborhood;
		this.address = address;
	}

	Restaurant(String id, String neiborhood, String name, String category,
			String address, String website, String phone, String picture,String rating) {
		super();
		this.id = id;
		this.neiborhood = neiborhood;
		this.name = name;
		this.category = category;
		this.address = address;
		this.website = website;
		this.phone = phone;
		this.picture = picture;
		this.rating = rating;
	}

	public String id;

	public String neiborhood;

	public String name;

	public String category;

	public String address;

	public String website;

	public String phone;

	public String picture;

	public String rating;

}
