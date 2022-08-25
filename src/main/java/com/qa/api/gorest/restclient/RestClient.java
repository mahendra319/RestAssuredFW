package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all http methods which will call the api's and having
 * generic methods for getting response and fetch the values from response
 * 
 * @author mahen
 *
 */
public class RestClient {

	// HTTP Methods: GET POST PUT DELETE

	private static boolean setBaseURI(String baseURI) {

		if (baseURI == null || baseURI.isEmpty()) {
			System.out.println("Please pass correct baseURI. It is either null or blank/empy");
			return false;
		}
		try {
			RestAssured.baseURI = baseURI;
			return true;
		} catch (Exception e) {
			System.out.println("Exception got occured while assing the baseURI with RestAssured");
			return false;
		}

	}

	private static RequestSpecification createRequest(String contentType, Map<String, String> token,
			Map<String, String> params, boolean log) {

		RequestSpecification request;
		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (token.size() > 0) {
			// request.header("Authorization", "Bearer "+token);
			request.headers(token);
		}

		if (!(params == null)) {

			request.queryParams(params);
		}
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			} else if (contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			} else if (contentType.equalsIgnoreCase("TEXT")) {
				request.contentType(ContentType.TEXT);
			} else if (contentType.equalsIgnoreCase("multipart")) {
				// request.contentType(ContentType.MULTIPART); OR
				request.multiPart("image", new File("C:\\Users\\mahen\\Downloads\\Screenshot_20220730-212659.png"));
			}
		}
		return request;
	}

	private static Response getResponse(String httpMethod, RequestSpecification req, String basePath) {

		return executeAPI(httpMethod, req, basePath);
	}

	private static Response executeAPI(String httpMethod, RequestSpecification req, String basePath) {

		Response response = null;

		switch (httpMethod) {

		case "GET":
			response = req.get(basePath);
			break;

		case "POST":
			response = req.post(basePath);
			break;

		case "PUT":
			response = req.post(basePath);
			break;

		case "DELETE":
			response = req.post(basePath);
			break;

		default:
			System.out.println("Please pass the correct httpMethod");
			break;
		}

		return response;

	}

	/**
	 * This method is used to call GET API
	 * 
	 * @param contentType
	 * @param baseUri
	 * @param basePath
	 * @param token
	 * @param params
	 * @param log
	 * @return this method is returning respose from GET call
	 */
	public static Response doGet(String contentType, String baseUri, String basePath, Map<String, String> token,
			Map<String, String> params, boolean log) {

		if (setBaseURI(baseUri)) {
			RequestSpecification reqSpec = createRequest(contentType, token, params, log);
			return getResponse("GET", reqSpec, basePath);
		}

		return null;

	}

//POST Call	
	/**
	 * This method is used to call POST API
	 * 
	 * @param contentType
	 * @param baseUri
	 * @param basePath
	 * @param token
	 * @param params
	 * @param log
	 * @param obj
	 * @return this method is returning response from POST call
	 */
	public static Response doPOST(String contentType, String baseUri, String basePath, Map<String, String> token,
			Map<String, String> params, boolean log, Object obj) {

		if (setBaseURI(baseUri)) {
			RequestSpecification reqSpec = createRequest(contentType, token, params, log);
//			String jsonBody = TestUtil.getSerializedJson(obj);
//			reqSpec.body(jsonBody);
			addRequestPayload(reqSpec, obj);
			return getResponse("POST", reqSpec, basePath);
		}

		return null;

	}

	public static void addRequestPayload(RequestSpecification reqSpec, Object obj) {

		if (obj instanceof Map) {
			reqSpec.formParams((Map<String, String>) obj);
		} else {
			String jsonBody = TestUtil.getSerializedJson(obj);
			reqSpec.body(jsonBody);
		}

	}

}
