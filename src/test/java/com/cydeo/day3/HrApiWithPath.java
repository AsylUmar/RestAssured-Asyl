package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrApiWithPath extends HrTestBase {

    @DisplayName("GET request to /countries with path method")
    @Test
    public void test() {
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        //response.prettyPrint();
        System.out.println(response.path("limit").toString());
        System.out.println(response.path("hasMore").toString());
        System.out.println(response.path("items[1].country_id").toString());
        System.out.println(response.path("items[3].country_name").toString());

        //to get all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println(countryNames);

        //assert that in the response all the region_ids are 2
        List<Integer> allRegionIDs = response.path("items.region_id");
        for (Integer regionID : allRegionIDs) {
            assertEquals(2, regionID);
        }

    }

    /*
    Send a GET request to employee endpoint, filter job_id IT PROG
    then assert that all job ids ar IT PROG
     */
    @DisplayName("GET request to /employees with path param")
    @Test
    public void test3() {


        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when()
                .get("/employees");

        //response.prettyPrint();

        List<String> AllJobIDs = response.path("items.job_id");
        for (String jobIDs : AllJobIDs) {
            assertEquals("IT_PROG",jobIDs);
        }
    }
}
