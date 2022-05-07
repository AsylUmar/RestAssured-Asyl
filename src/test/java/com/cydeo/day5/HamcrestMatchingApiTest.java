package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.apache.commons.lang3.builder.ToStringExclude;
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

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void test2(){
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",10423)
                .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Encoding","gzip")
                .and()
                .header("Date", notNullValue())
                .body("teachers[0].firstName",is("Alexander"),
                        "teachers[0].lastName",is("Syrup"),
                        "teacher[0].gender",is("male"));

    }

    @Test
    public void test3(){
        given()
                .accept(ContentType.JSON)
                .when()
                .get("http://api.cybertektraining.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Candi","Alexander","Francesca"));


    }
}
