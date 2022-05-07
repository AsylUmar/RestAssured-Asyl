package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class HamcrestMatchingApiTest {
    /*
     Given accept type is json
     And path param id is 15
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     Json data has the following:
          id is 15,
          name is "Meta",
          gender is "Female",
          phone is 1938695106
   */

    @DisplayName("One Spartan with Hamcrest and Chaining")
    @Test
    public void test1(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id",is(15),
                        "name",is("Meta"),
                        "gender",equalTo("Female"),
                         "phone",is(1938695106));


    }
}
