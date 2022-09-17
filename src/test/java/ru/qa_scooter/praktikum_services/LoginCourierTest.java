package ru.qa_scooter.praktikum_services;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;
import static ru.qa_scooter.praktikum_services.ErrorMessage.BAD_MESSAGE_LOGIN;
import static ru.qa_scooter.praktikum_services.ErrorMessage.MESSAGE_NOT_FOUND;

public class LoginCourierTest {
Courier courier;
CourierClient courierClient;
private int courierId;

@Before
public void Setup(){
    courier = GenerationCourier.getDefault();
    CourierClient courierClient =  new CourierClient();
}

@Test
@DisplayName("Login as a courier")
@Description("Service return 200, when courier login")
    public void loginTest(){
    courier = GenerationCourier.getRight();
    ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
    int statusCode = response.extract().statusCode();
    assertEquals(SC_OK, statusCode);
    int courierId = response.extract().path("id");
    assertNotNull(courierId);
}

@Test
@DisplayName("Login as a courier, without password")
@Description("Service return 400, when courier login without password")
    public void loginWithoutPassTest(){
    courier = GenerationCourier.getWithoutPassword();
    ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
    int statusCode = response.extract().statusCode();
    assertEquals(SC_BAD_REQUEST, statusCode);
    String isMessage = response.extract().path("message");
    assertEquals(BAD_MESSAGE_LOGIN, isMessage);
}

@Test
@DisplayName("Login as a courier, without login")
@Description("Service return 400, when courier login without login")
    public void loginWithoutTest() {
    courier = GenerationCourier.getWithoutLogin();
    ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
    int statusCode = response.extract().statusCode();
    assertEquals(SC_BAD_REQUEST, statusCode);
    String isMessage = response.extract().path("message");
    assertEquals(BAD_MESSAGE_LOGIN, isMessage);
}

@Test
@DisplayName("Login as a courier, unregistered login")
@Description("Service return 404, when courier unregistered login")
    public void loginNonFoundTest(){
    courier = GenerationCourier.getNonExistentLogin();
    ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
    int statusCode = response.extract().statusCode();
    assertEquals(SC_NOT_FOUND, statusCode);
    String isMessage = response.extract().path("message");
    assertEquals(MESSAGE_NOT_FOUND, isMessage);
}
}