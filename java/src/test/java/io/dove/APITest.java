package io.dove;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APITest {
	private final String uri = "https://jsonplaceholder.typicode.com";

	@Test (testName = "GET /posts/1", description = "verify that userId property exists and equals 1")
	public void test01() {
		given()
			.baseUri(uri)
		.when()
			.get("/posts/1")
		.then()
			.assertThat()
			.statusCode(200)
			.body("$", hasKey("userId"))
			.body("userId", equalTo(1));
			//.log().body();
	}

	@Test (testName = "GET /todos/1", description = "verify that completed property data type is boolean")
	public void test02() {
		given()
			.baseUri(uri)
		.when()
			.get("/todos/1")
		.then()
			.assertThat()
			.statusCode(200)
			.body("", hasKey("completed"))
			.body("completed", is(instanceOf(Boolean.class)));
			//.log().all();
	}
	
	@Test (testName = "GET /posts/100/comments", description = "verify that response body size is 5")
	public void test03() {
		given()
			.baseUri(uri)
		.when()
			.get("/posts/100/comments")
		.then()
			.assertThat()
			.statusCode(200)
			.body("$", hasSize(5));
			//.log().body();
	}
	
	@Test (testName = "GET /posts/100/comments", description = "verify that body of Davion value equals we expected")
	public void test04() {
		Response response = given()
				.baseUri(uri)
			.when()
				.get("/posts/100/comments");
		JsonPath body = response.jsonPath();
		String davionBody = body.getString("find{it.email.startsWith('Davion')}.body");
		response.then()
			.assertThat()
			.statusCode(200);
		assertEquals(davionBody, "aliquam pariatur suscipit fugiat eos sunt"
				+ "\noptio voluptatem eveniet rerum dignissimos"
				+ "\nquia aut beatae"
				+ "\nmodi consequatur qui rerum sint veritatis deserunt est");
	}
	
	@Test (testName = "POST /posts/100/comments", description = "verify that response data equals data we posted")
	public void test05() {
		String postId = "100";
		String name = "나다";
		String email = "test_id@naver.com";
		String body = "본문이에요";
		
		Map<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", name);
		jsonAsMap.put("email", email);
		jsonAsMap.put("body", body);
		
		given()
			.baseUri(uri)
			.contentType("application/json; charset=UTF-8")
			.body(jsonAsMap)
		.when()
			.post("/posts/{postId}/comments", postId)
		.then()
			.assertThat()
			.statusCode(201)
			.body("$", hasEntry("postId", postId))
			.body("$", hasEntry("id", 501))
			.body("$", hasEntry("name", name))
			.body("$", hasEntry("email", email))
			.body("$", hasEntry("body", body));
			//.log().body();
	}
	
	@Test (testName = "GET /posts/1", description = "always fail")
	public void test06() {
		given()
			.baseUri(uri)
		.when()
			.get("/posts/1")
		.then()
			.assertThat()
			.statusCode(404);
	}
}
