package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HrTestBase {
    //beforeAll is the same thing as BeforeClass in TestNG and runs before the class
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.242.121.113:1000/ords/hr";

    }
}
