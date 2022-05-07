package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetRequest {

    //beforeAll is the same thing as BeforeClass in TestNG and runs before the class
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.242.121.113:8000";
    }
    /*
    Given Accept type is application/json
    When user sends GET request to api/spartans end point
    Then status code is 200
    And response content type is application/json
     */

    @DisplayName("GET all spartans")
    @Test
    public void test1() {

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        //Print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print the content type
        System.out.println("response.contentType() = " + response.contentType());

        //how to test API?
        //verify status code is 200
        assertEquals(200, response.statusCode());

        //verify content type is application/json
        assertEquals("application/json", response.contentType());

    }

    /*
    Given Accept type is application/xml
    When user sends GET request to/api/spartans/10 endpoint
    Then status code is 406
    And response Content type is application/xml;charset=UTF-8;
     */

    @DisplayName(("GET request with Accept XML individual Spartan"))
    @Test
    public void test2(){


        Response response = given()
                    .accept(ContentType.XML)
                .when()
                    .get("/api/spartans/10");

        assertEquals(406,response.statusCode());
        assertEquals("application/xml;charset=UTF-8",response.contentType());
    }
}
