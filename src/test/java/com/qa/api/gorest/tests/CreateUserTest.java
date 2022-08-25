package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUserTest {
	
	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token ="7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49";
	
	
	
/*
 * To create multiple users	
 */
	
	
	@DataProvider()
	public Object[][] getUserData() {
		Object [][] userData= ExcelUtil.getTestData("Users");
		return userData;
	}
	

	@Test(dataProvider="getUserData")
	public void createUserApiPostTest(String name, String gender,String status,String email) {
		
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		User user = new User(name,gender,status,email);
		Response response = RestClient.doPOST("JSON", baseURI, basePath, authTokenMap, null, true, user);
		
		JsonPath jPath = response.jsonPath();
		int id = jPath.get("data.id");
		String userName =jPath.get("data.name");
		
		System.out.println(response.prettyPrint());
		System.out.println("User id is: "+id);
		Assert.assertEquals(userName, name);
		System.out.println("************************************************");
	}
	
	
//	For single user creation
//	@Test
//	public void createUserApiPostTest() {
//		
//		User user = new User("Rekha","female","active","rekha@xyz.com");
//		Response response = RestClient.doPOST("JSON", baseURI, basePath, token, null, true, user);
//		
//		JsonPath jPath = response.jsonPath();
//		int id = jPath.get("data.id");
//		String userName =jPath.get("data.name");
//		
//		System.out.println(response.prettyPrint());
//		System.out.println("User id is: "+id);
//		Assert.assertEquals(userName, "Rekha");
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
