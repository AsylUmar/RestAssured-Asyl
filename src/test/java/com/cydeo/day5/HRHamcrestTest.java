package com.cydeo.day5;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HRHamcrestTest extends HrTestBase {
    @DisplayName("GET requests to Employees IT-PROG endpoint and chaining")
    @Test
    public void test1(){
/*
TASK
SEND A GET request to EMPLOYEE endpoint with a query parameter job_id IT-PROG
Verify status code and content type
Verify each job_id is IT_PROG
verify firstNames are ... (find a proper method to check List against List)
Verify emails without checking order (provide email in different order, just make sure it has same emails)
Expected names
 */
        //expected names
        List<String> names = Arrays.asList("Alexander","Bruce","David","Valli","Diana");

        given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("items.job_id",everyItem(is("IT_PROG")),
                        "items.salary",everyItem(greaterThan(3000)),
                        "items.first_name", equalTo(names),
                        "items.email",containsInAnyOrder("AHUNOLD","DAUSTIN","BERNST","VPATABAL","DLORENTZ"));


    }
    @Test
    public void test2(){
        //we want to chain with hamcrest and also get a response object
       JsonPath jsonPath =  given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
                .extract().response().jsonPath();

       //extract() -> method that allows us to get response object after we used then() method

       //assert that we have only 5 names
       assertThat(jsonPath.getList("items.first_name"),hasSize(5));

    }
}
