package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetUserTest {

	String baseURI = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token ="7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49";
	
	
	
	@Test
	public void getAllUsersListApiTest() {
		
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		Response res = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);
		JsonPath jPath =res.jsonPath();
		
		System.out.println(res.prettyPrint());
		System.out.println("Total number of users: "+jPath.get("meta.pagination.total"));
		
	}
	
	@Test
	public void getUsersListWithParamApiTest() {
		
		Map<String, String> authTokenMap = new HashMap<String, String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "Pandey");
		params.put("gender", "male");
		
		Response res = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);
		JsonPath jPath = res.jsonPath();
		
		System.out.println(res.prettyPrint());
		System.out.println("Total number of users with name Pandey is: "+jPath.get("meta.pagination.total"));
		
	}
	
	
	
}
