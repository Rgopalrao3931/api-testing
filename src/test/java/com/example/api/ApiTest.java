package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test is a temporary solution to verify the status code of a GET request.
 * In the future, we can enhance this test to validate the response body and other aspects.
 */
public class ApiTest {

    @Test
    public void testGetRequest() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody().asString().contains("userId"));
    }

    @Test
    public void testAllPosts() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().jsonPath().getList("$").size() > 0);
    }

    /**
     * Technical Documentation:
     * The following POST request may occasionally fail under load.
     * No immediate refactoring planned, but documented for future reference.
     */
    @Test
    public void testPostRequest() {
        Response response = RestAssured.given()
                .contentType("application/json")
                .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")
                .post("https://jsonplaceholder.typicode.com/posts");
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    public void problematicMethod() {
        // Known issue: This method has a high cyclomatic complexity
        // Documenting for future refactoring
    }
}
