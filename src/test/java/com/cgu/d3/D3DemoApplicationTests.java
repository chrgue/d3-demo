package com.cgu.d3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class D3DemoApplicationTests {

	@LocalServerPort
	int port;

	@Test
	public void response200OnRoot() {
	
		RestAssured.given().port(port).basePath("/").get("").then().statusCode(200);
	}

	@Test
	public void response200ForSingleComponent() {
	
		RestAssured.given().port(port).basePath("/").get("component1").then().statusCode(200);
	}
}
