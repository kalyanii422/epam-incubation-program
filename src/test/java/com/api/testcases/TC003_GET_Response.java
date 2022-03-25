/**
 * @author Kalyani_Kannuri
 */
package com.api.testcases;

import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.api.base.TestBaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_GET_Response extends TestBaseClass {

	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getUsersData() throws InterruptedException {
		logger.info("********* Started TC003_GET_Response **********");

		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value
		// pairs using the put method
		JSONObject requestParams = new JSONObject();
		// Add a header stating the Request body is a JSON
		httpRequest.header("Cache-Control", "no-cache");
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the POST Reset request
		httpRequest.body(requestParams.toJSONString());
		response = httpRequest.request(Method.GET, "/users");
		Thread.sleep(7000);

	}

	@Test
	void checkResposeBody() {
		String responseBody = response.getBody().asString();
		List<String> jsonResponse = response.jsonPath().getList("$");
		logger.info("Response body as string" + responseBody);
		Assert.assertEquals(jsonResponse.size(), 10);
	}

	// Test Case - Response Time check
	@Test
	void checkResponseTime() {
		long responseTime = response.getTime();
		Assert.assertTrue(responseTime < 7000);

	}


	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC003_GET_Response  **********");
	}

}
