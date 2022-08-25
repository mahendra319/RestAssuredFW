package com.qa.api.gorest.util;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Token {
	
	public static Map<Object, Object> appTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String, String>();
	public static String clientId = "4c81f9f26c45062";
	
	public static Map<Object, Object> getAccessToken() {
		
		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("refresh_token", "ae6f59915d99b4abed23cd6b24b7bc54edac10a4");
		formParams.put("client_id", "4c81f9f26c45062");
		formParams.put("client_secret", "df267b786698b18cc4a3f220ee9939544edf3f2e");
		formParams.put("grant_type", "refresh_token");
		
		
		
	JsonPath resJson=	given()
			.formParams(formParams)
		.when()
			.post("https://api.imgur.com/oauth2/token")
		.then()
			.extract().jsonPath();
	
	System.out.println(resJson.getMap(""));
	
	appTokenMap= resJson.getMap("");
	
	return appTokenMap;
	}
	
	
	public static Map<String, String> getAuthenticationToken() {
		
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("Authorization Token is: "+authToken);
		tokenMap.put("Authorization", "Bearer "+authToken);
		return tokenMap;
	}
	
public static Map<String, String> getClientId() {
		
		System.out.println("Client Id is: "+clientId);
		tokenMap.put("Authorization", "Client-ID "+clientId);
		return tokenMap;
	}
	
	


}
