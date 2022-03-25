/**
 * @author Kalyani_Kannuri
 * 
 */

package com.api.testcases;

import org.json.simple.JSONObject;

import org.testng.Assert;
import org.testng.annotations.*;

import com.api.base.TestBaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_GET_Content_Type extends TestBaseClass {

	RequestSpecification httpRequest;
	Response response;
	String email = "hemant12@fox.com";
	String password = "abcdef";

	@BeforeClass
	void getEmployeeData() throws InterruptedException {
		logger.info("********* Started TC002_GET_Content_Type **********");

		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value
		// pairs using the put method
		JSONObject requestParams = new JSONObject();

		// Add a header stating the Request body is a JSON
		httpRequest.header("Accept-Encoding", "gzip, deflate, br");
		httpRequest.header("Cache-Control", "no-cache");

		// Add the Json to the body of the POST LOGIN request
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.GET, "/users");
		Thread.sleep(7000);

	}
	
	@Test
	void testCheckContentTypeIsPresent() {
		Headers headers = response.getHeaders();
		Assert.assertTrue(headers.toString().contains("Content-Type"));
	}

	
	@Test
	void testCheckContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC002_GET_Content_Type  **********");
	}

}
