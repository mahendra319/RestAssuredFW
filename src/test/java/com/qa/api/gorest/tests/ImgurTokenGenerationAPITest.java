package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ImgurTokenGenerationAPITest {

	Map<Object, Object> tokenMap;
	String accessToken ;
	String accountUserName ;
	String refreshToken;
	String accntId;
	
	
	@BeforeMethod
	public void tokenSetUp() {
		tokenMap=Token.getAccessToken();
		accessToken=tokenMap.get("access_token").toString();
		accountUserName=tokenMap.get("account_username").toString();
		accntId=tokenMap.get("account_id").toString();
		refreshToken=tokenMap.get("refresh_token").toString();
	}
	
	
	@Test
	public void getAccountBlockStatusTest() {
		
		Map<String, String> authTokenMap = Token.getAuthenticationToken();
		Response response = RestClient.doGet(null, "https://api.imgur.com", "/account/v1/"+accountUserName+"/block", authTokenMap, null, true);
					
		JsonPath jP = response.jsonPath();
		//System.out.println(jP.get("data.blocked"));
		System.out.println(response.prettyPrint());
		Assert.assertFalse(jP.get("data.blocked"));
	
	}
	
	@Test
	public void getAccountImagesTest() {
		
		Map<String, String> authTokenMap = Token.getAuthenticationToken();
		
		Response res = RestClient.doGet(null, "https://api.imgur.com", "/3/account/me/images", authTokenMap, null, true);
	
		System.out.println(res.prettyPrint());
		System.out.println("Status Code is: "+res.getStatusCode());
	
	}
	
	
	@Test (enabled=false)
	public void uploadImagePostApiTest() {
		
		Map<String, String> clientIdMap = Token.getClientId();
		
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "Test Title");
		formMap.put("description", "test description");
		
		Response res = RestClient.doPOST("multipart", "https://api.imgur.com", "/3/upload", clientIdMap, null, true, formMap);
		System.out.println(res.getStatusCode());
		System.out.println(res.prettyPrint());
		
	}
	
	
	
}
