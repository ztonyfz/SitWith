package com.example.sitwith;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

public class RequestService {
	
	public void makeRequest(String userId, String tableId) {
		SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		Document doc = null;
		try {
			doc = builder.build(Constants.SERVER_ADDRESS + "/makeRequest?user_id=" + userId + "&table_id=" + tableId + "&restaurant_id=1");
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Request> getRequests(String userId) {
		SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		Document doc = null;
		try {
			doc = builder.build(Constants.SERVER_ADDRESS + "/getRequests?user_id=" + userId);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element root = doc.getRootElement();
		List<Request> requests = new ArrayList<Request>();
	    List<Element> requestsE = root.getChildren();
	    for (Element requestE : requestsE) {
	    	Request request = new Request();
	    	request.id = requestE.getChildText("id");
	    	request.status = requestE.getChildText("status");
	    	request.time = new Date(Long.parseLong(requestE.getChildText("time")));
	    	request.restaruantName = requestE.getChildText("restaurant");
	    	
	    	requests.add(request);
	    }
	    
	    return requests;
	}
	
	public List<User> getTable(String tableId) {
		SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		Document doc = null;
		try {
			doc = builder.build(Constants.SERVER_ADDRESS + "/getTable?table_id=" + tableId);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Element root = doc.getRootElement();
		List<User> users = new ArrayList<User>();
		List<Element> usersE = root.getChildren();
	    for (Element userE : usersE) {
	    	User user = new User();
	    	user.fullname = userE.getChildText("fullname");
	    	user.gender = userE.getChildText("gender");
	    	user.age = Integer.parseInt(userE.getChildText("age"));
	    	
	    	users.add(user);
	    }
	    
	    return users;
		
	}

}
