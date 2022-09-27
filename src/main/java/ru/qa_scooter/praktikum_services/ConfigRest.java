package ru.qa_scooter.praktikum_services;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ConfigRest {
    public static final String BASE_URI = "http://qa-scooter.praktikum-services.ru";

    public static RequestSpecification getSpec(){
        return given().log().all()
                .header("Content-Type", "application/json")
                .baseUri(ConfigRest.BASE_URI);
    }
}
