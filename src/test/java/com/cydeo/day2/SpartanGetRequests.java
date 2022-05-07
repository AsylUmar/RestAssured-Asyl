package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {
    String url = "http://54.242.121.113:8000";


    /*
    Given Accept type is application/json
    When user sends GET request to api/spartans end point
    Then status code is 200
    And response content type is application/json
     */

    @Test
    public void test1() {

        Response response = RestAssured.
                given()
                .accept(ContentType.JSON)
                .when()
                .get(url + "/api/spartans");

        //Print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print the content type
        System.out.println("response.contentType() = " + response.contentType());

        //how to test API?
        //verify status code is 200
        Assertions.assertEquals(200, response.statusCode());

        //verify content type is application/json
        Assertions.assertEquals("application/json", response.contentType());

    }
        /*
        Given Accept header is application/json
        When user sends a get request to /api/spartans/3
        Them status code must be 200
        And Content type must be application/json
        And json body should contain 'Fidole'
         */

    @DisplayName(("GET spartan name number 3"))
        @Test
        public void test2(){
           Response  response =  RestAssured.given().accept(ContentType.JSON)
                    .when()
                    .get(url+"/api/spartans/3");

            //verify status code is 200
            Assertions.assertEquals(200, response.getStatusCode());

            //verify content type is application/json
            Assertions.assertEquals("application/json",response.getContentType());

            //verify 'Fidole' exist inside jsn body
            Assertions.assertTrue(response.body().asString().contains("Fidole"));
        }

        /*
        Given no headers provided
        When users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        and header should contain date
        And Content Length should be 17
        And body should be "Hello from Sparta"

         */
    @DisplayName("GET request to /api/hello endpoint")
    @Test
    public void test3(){
        Response response = RestAssured.when().get(url+"/api/hello");
        response.prettyPrint();

        //verify status code
        Assertions.assertEquals(200, response.getStatusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //verify Date header exists in Response header
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //to get header value we use header() method which accepts header name as parameter and returns value as String
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));

        //verify Content-Length is 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        //verify body is "hello from Sparta
        Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }
    }

