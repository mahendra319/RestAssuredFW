package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	
	
	/**
	 * This method is used to convert POJO object to the JSON string
	 * @param obj
	 * @return this method is returning Json String
	 */
	public static String getSerializedJson(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		String stringJson = null;
		try {
			stringJson=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			System.out.println("JSON body payload is: "+stringJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return stringJson;
	}
	
	
	
	
	
	
	
	
	
	
	

}
