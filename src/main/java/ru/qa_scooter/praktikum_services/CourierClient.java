package ru.qa_scooter.praktikum_services;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
public class CourierClient extends ConfigRest {
    private static final String COURIER_PATH = "/api/v1/courier";
    private static final String LOGIN_PATH = "/api/v1/courier/login";
    private static final String DELETE_COURIER = "/api/v1/courier/";
    private static final String ORDER_PATH = "/api/v1/orders";

    public static ValidatableResponse create(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    public static ValidatableResponse loginCourier(LoginCourier loginCourier) {
        return getSpec()
                .body(loginCourier)
                .when()
                .post(LOGIN_PATH)
                .then().log().all();
    }

    public static ValidatableResponse deleteId(int id) {
        return getSpec()
                .pathParam("id", id)
                .when()
                .delete(DELETE_COURIER + "{id}")
                .then().log().all();
    }

    public static ValidatableResponse createOrder(Order order) {
        return getSpec()
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }

    public static ValidatableResponse listOrder() {
        return getSpec()
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }

}
