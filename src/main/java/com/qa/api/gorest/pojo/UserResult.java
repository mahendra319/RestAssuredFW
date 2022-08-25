package com.qa.api.gorest.pojo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class UserResult {

	@Test
	public void createUserWithFullJson() {
		
		String token = "7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49";
		
		Self sf = new Self("http://www.sf.com");
		Edit ed = new Edit("http://www.ed.com");
		Avatar av = new Avatar("http://www.av.com");

		Links ln = new Links(sf, ed, av);

		UserDetails ud = new UserDetails("Kotike", "male", "active", "kotike@zyz.com", ln);

		String userJsonPayload = TestUtil.getSerializedJson(ud);
		System.out.println("********************************");
		System.out.println(userJsonPayload);
		Map<String, String> authTokenMap = new HashMap<String,String>();
		authTokenMap.put("Authorization", "Bearer "+token);
		Response res=RestClient.doPOST("JSON", "https://www.gorest.co.in", "/public/v2/users", authTokenMap, null, true, userJsonPayload);
		System.out.println(res.getStatusCode());
		System.out.println(res.prettyPrint());

	}

}
