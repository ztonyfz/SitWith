package com.example.sitwith;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

public class FeedbackService {
	
	public void giveFeedback(String userId, String tableId, String contents) {
		SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
		Document doc = null;
		try {
			doc = builder.build(Constants.SERVER_ADDRESS + "/giveFeedback?user_id=" + userId + "&table_id=" + tableId + "&contents=" + contents);
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
