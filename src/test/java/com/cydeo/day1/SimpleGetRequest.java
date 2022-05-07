package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://54.242.121.113:8000/api/spartans";

    @Test
    public void test(){

        //send a get request and save response inside the Response object
        Response response = RestAssured.get(url);

        //Print Response status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //Print Response body
        response.prettyPrint();
    }
}
