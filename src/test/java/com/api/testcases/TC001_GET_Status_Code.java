/**
 * @author Kalyani_Kannuri
 * Create a test to verify an http status code.
 */

package com.api.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.TestBaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_Status_Code extends TestBaseClass {

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void setUp() throws InterruptedException {
		logger.info("********* Started TC001_HTTP_Status_Code  **********");
		

		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value
		// pairs using the put method

		JSONObject requestParams = new JSONObject();

		// Add a header stating the Request body is a JSON
		httpRequest.header("Accept-Encoding", "gzip, deflate, br");
		httpRequest.header("Cache-Control", "no-cache");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.GET, "/users");

		Thread.sleep(5000);

	}

	

	@Test
	void testCheckStatusCode() {
		int statusCode = response.getStatusCode(); 
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void testCheckStatusLine() {
		String statusLine = response.getStatusLine(); 
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC001_HTTP_Status_Code **********");
	}

}
