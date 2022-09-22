package ru.qa_scooter.praktikum_services;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.qa_scooter.praktikum_services.ErrorMessage.BAD_MESSAGE_LOGIN;
import static ru.qa_scooter.praktikum_services.ErrorMessage.MESSAGE_NOT_FOUND;

public class LoginCourierTest {
Courier courier;
CourierClient courierClient;
private int courierId;

@Before
public void setup(){
    courier = GenerationCourier.getDefault();
    CourierClient courierClient =  new CourierClient();

}

@Test
@DisplayName("Login as a courier")
@Description("Service return 200, when courier login")
    public void loginTest(){
    ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
    response.statusCode(SC_OK);
    MatcherAssert.assertThat("id", notNullValue());
}

@Test
@DisplayName("Login as a courier, without password")
@Description("Service return 400, when courier login without parameters login")
    public void loginWithoutParameterTest(){
    courier = GenerationCourier.getRandomCourierWithoutLogin();
    ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
    response.statusCode(SC_BAD_REQUEST).and().assertThat().body("message", is(BAD_MESSAGE_LOGIN));
}

    @Test
    @DisplayName("Login as a courier, without password")
    @Description("Service return 400, when courier login without parameters password")
    public void loginWithoutPasswordParameterTest(){
        courier = GenerationCourier.getRandomCourierWithoutPass();
        ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
        response.statusCode(SC_BAD_REQUEST).and().assertThat().body("message", is(BAD_MESSAGE_LOGIN));
    }

@Test
@DisplayName("Login as a courier, without login")
@Description("Service return 400, when courier login without login")
    public void loginWithoutTest() {
    courier.setLogin(null);
    ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
    response.statusCode(SC_BAD_REQUEST).and().assertThat().body("message", is(BAD_MESSAGE_LOGIN));
}

    @Test
    @DisplayName("Login as a courier, without login")
    @Description("Service return 400, when courier login without password")
    public void loginWithoutPasswordTest() {
        courier.setPassword(null);
        ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
         response.statusCode(SC_BAD_REQUEST).and().assertThat().body("message", is(BAD_MESSAGE_LOGIN));
    }

@Test
@DisplayName("Login as a courier, unregistered login")
@Description("Service return 404, when courier unregistered login")
    public void loginNonFoundTest(){
     courier = GenerationCourier.getNonExistentLogin();
    ValidatableResponse response = CourierClient.loginCourier(LoginCourier.from(courier));
    response.statusCode(SC_NOT_FOUND).and().assertThat().body("message", is(MESSAGE_NOT_FOUND));
}
}