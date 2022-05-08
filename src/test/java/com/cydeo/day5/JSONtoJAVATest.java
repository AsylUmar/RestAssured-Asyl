package com.cydeo.day5;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class JSONtoJAVATest extends SpartanTestBase {
    @DisplayName("GET one Spartan and deserialize to MAP")
    @Test
    public void test1(){
      Response response =  given()
                .accept(ContentType.JSON)
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}")
        .then()
                .statusCode(200)
                .extract().response();

      //get Json body and convert it to JAVA Map
        Map<String, Object> jsonMap= response.as(Map.class);
        System.out.println(jsonMap);

        String name = (String)jsonMap.get("name");

        //verify name is Meta
        assertThat(name,is("Meta"));
    }

    @DisplayName("GET all Spartans to JAVA data structure")
    @Test
    public void test2(){
        Response response =get("/api/spartans").then().statusCode(200).extract().response();

        //we need to convert json to java which is deserialization
        List<Map<String, Object>> jsonList = response.as(List.class);
        System.out.println(jsonList.get(1).get("name"));
        System.out.println(jsonList.get(15).get("gender"));
    }
}
