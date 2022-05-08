package com.cydeo.day6;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanPojoGetRequestTest extends SpartanTestBase {

    @DisplayName("GET one Spartan and convert it to Spartan Object")
    @Test
    public void test() {
        Response response = given()
                .accept(ContentType.JSON).log().all()
                .and()
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)//.log().all() -> prints on console detailed output
                .extract().response();

        //deserialize ---> JSON to POJO (JAVA Custom class)
        //2 ways to do this
        //1. using as() method
        //we convert json response to Spartan Object wiht the help of Jackson
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);

        System.out.println(spartan15.getName());
        System.out.println(spartan15.getPhone());

        //second way of deserialization
        //2. using JsonPath to deserialize to Custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("", Spartan.class);
        System.out.println(s15.getName());
        System.out.println(s15.getPhone());
    }

    @DisplayName("GET one Spartan from search endpoint and use POJO")
    @Test
    public void test2() {
        JsonPath jsonPath = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")//getting all spartans
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //get the second spartan on the content list and put inside the spartan object
        Spartan spartan = jsonPath.getObject("content[1]", Spartan.class);
        System.out.println(spartan.getName());
        System.out.println(spartan);

    }

    @DisplayName("GET one Spartan from search endpoint and use POJO")
    @Test
    public void test3() {
        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")//getting all spartans
                .then()
                .statusCode(200)
                .extract().response();

        //get the full content json and convert it to search object
        Search searchResult = response.as(Search.class);
        System.out.println(searchResult.getTotalElement());
        System.out.println(searchResult.getContent().get(1).getName());

        //also jackson can be used
        Search search2 = response.jsonPath().getObject("", Search.class);
        System.out.println(search2.getTotalElement());
        System.out.println(search2.getContent().get(1).getName());

    }

    @DisplayName("GET /api/spartans/search and save as List<Spartans>")
    @Test
    public void test4() {
        Response response = given()
                .accept(ContentType.JSON)
                .when().get("/api/spartans/search")//getting all spartans
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<Spartan> content = jsonPath.getList("content", Spartan.class);
        System.out.println(content.get(1).getName());
    }

    //create pojo without including everything

}
