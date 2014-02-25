package com.example.sitwith;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

public class UserService {
	
	public void login(String userId, String username, String firstname, String lastname, int age, String gender) {
		SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		try {
			builder.build(Constants.SERVER_ADDRESS + "/login?user_id=" + userId + "&username=" + username +
					"&firstname=" + firstname + "&lastname=" + lastname + "&age=" + age + "&gender=" + gender);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
