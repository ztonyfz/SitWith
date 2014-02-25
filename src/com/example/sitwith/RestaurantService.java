package com.example.sitwith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

import android.util.Log;

public class RestaurantService {
	
	public List<Neiborhood> getRestaurants() {
		SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		Document doc = null;
		try {
			doc = builder.build(Constants.SERVER_ADDRESS + "/getRestaurants");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Neiborhood> neiborhoods = new ArrayList<Neiborhood>();
		
		Element root = doc.getRootElement();
		List<Element> neiborhoodsE = root.getChildren();
		for (Element neiborhoodE : neiborhoodsE) {
			Neiborhood neiborhood = new Neiborhood();
			neiborhood.name = neiborhoodE.getChildText("name");
			Log.i("jdom", neiborhood.name);
			List<Element> restaurantsE = neiborhoodE.getChildren("restaurant");
			System.out.println(restaurantsE.size());
			for (Element restaurantE : restaurantsE) {
				Restaurant restaurant = new Restaurant();
				restaurant.name = restaurantE.getChildText("name");
				restaurant.id = restaurantE.getChildText("id");
				Log.i("jdom", restaurant.name);
				neiborhood.restaurants.add(restaurant);
			}
			neiborhoods.add(neiborhood);
		}
		
		return neiborhoods;
	}
	
	public Restaurant getRestaurant(String restaurantId) {
		SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		Document doc = null;
		try {
			doc = builder.build(Constants.SERVER_ADDRESS + "/getRestaurant?restaurant_id=" + restaurantId);
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element root = doc.getRootElement();
		Restaurant restaurant = new Restaurant();
		restaurant.id = root.getChildText("id");
		restaurant.name = root.getChildText("name");
		restaurant.address = root.getChildText("address");
		restaurant.category = root.getChildText("category");
		restaurant.phone = root.getChildText("phone");
		restaurant.picture = root.getChildText("picture");
		restaurant.rating = root.getChildText("rating");
		restaurant.website = root.getChildText("website");
		
		return restaurant;
	}
	
	public List<Table> getAvailableTables(String restaurantId) {
		SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		Document doc = null;
		try {
			doc = builder.build(Constants.SERVER_ADDRESS + "/getAvailableTables?restaurant_id=" + restaurantId);
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element root = doc.getRootElement();
		
		List<Table> tables = new ArrayList<Table>();
		List<Element> tablesE = root.getChildren();
	    for (Element tableE : tablesE) {
	    	Table table = new Table();
	    	table.id = tableE.getChildText("id");
	    	table.time = new Date(Long.parseLong(tableE.getChildText("time")));
	    	tables.add(table);
	    }
	    
	    return tables;
	}

}
