package com.cydeo.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CTrainingTests {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://api.cybertektraining.com";
    }


    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 24103)
                .when()
                .get("/student/{id}");

        response.prettyPrint();

        //verify status code
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json;charset=UTF-8", response.contentType());

        //how to get any header value
        assertEquals("gzip", response.header("Content-Encoding"));

        //how to verify header exists or not
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //payload body verification
        /*
        firstName Karole                            --> students[0].firstName
        batch 7                                     --> students[0].batch
        major Master of Creative Arts               --> students[0].major
        emailAddress hassan.lebsack@hotmail.com.    --> students[0].contact.emailAddress
        companyName Legacy Integration Analyst      --> students[0].company.companyName
        street 6253 Willard Place                   --> students[0].company.address.street
        zipCode 28524                               --> students[0].company.address.zipCode

         */

        //to get Jsonpath object
        JsonPath jsonPath = response.jsonPath();

        assertEquals("Karole","students[0].firstName");
        assertEquals(7,"students[0].batch");
        assertEquals("Master of Creative Arts","students[0].major");
        assertEquals("hassan.lebsack@hotmail.com","students[0].contact.emailAddress");
        assertEquals("Legacy Integration Analyst","students[0].company.companyName");
        assertEquals("6253 Willard Place","students[0].company.address.street");
        assertEquals("28524","students[0].company.address.zipCode");


    }
}
//send a get request to student id 24103 as a path parameter and accept header application/json
//verify status code=200
// /content type=application/json;charset=UTF-8
// /Content-Encoding = gzip
//verify Date header exists
//assert that
            /*
                firstName Karole
                batch 7
                major Master of Creative Arts
                emailAddress hassan.lebsack@hotmail.com
                companyName Legacy Integration Analyst
                street 6253 Willard Place
                zipCode 28524

                using JsonPath
             */