package ru.qa_scooter.praktikum_services;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.qa_scooter.praktikum_services.ErrorMessage.BAD_MESSAGE;
import static ru.qa_scooter.praktikum_services.ErrorMessage.CONFLICT_MESSAGE;

public class CourierTest {
  Courier courier;
  CourierClient courierClient;
  private int courierId;

  @Before
  public void setup(){
    courierClient = new CourierClient();
  }

  @After
  public void tearDown(){
    CourierClient.deleteId(courierId);
  }

  @Test
  @DisplayName("Creating a courier")
  @Description("Service return 201 Created when new courier created")
    public void createCourierTest(){
      courier = GenerationCourier.getRandomCourier();
      ValidatableResponse response = CourierClient.create(courier);
      response.statusCode(SC_CREATED);
      boolean isOk = response.extract().path("ok");
      assertTrue(isOk);
  }

  @Test
  @DisplayName("The courier cannot be registered")
  @Description("Service return 400 bad request,when client create courier with double login")
  //Баг с текстом ошибки.
    public void createDoubleCourierTest(){
      courier = GenerationCourier.getRandomCourier();
      ValidatableResponse response = CourierClient.create(courier);
      response.statusCode(SC_CREATED);
      boolean isOk = response.extract().path("ok");
      assertTrue(isOk);

       ValidatableResponse loginResponse = CourierClient.loginCourier(LoginCourier.from(courier));
       courierId = loginResponse.extract().path("id");
       assertNotNull(courierId);

      ValidatableResponse doubleResponse = CourierClient.create(courier);
      doubleResponse.statusCode(SC_CONFLICT).and().assertThat().body("message", is(CONFLICT_MESSAGE));
  }

  @Test
  @DisplayName("Create Courier without login")
  @Description("Service return 400 bad request,when Courier create without field login")
  public void createWithoutLogin(){
    courier = GenerationCourier.getRandomCourierWithoutLogin();
    ValidatableResponse response = CourierClient.create(courier);
    response.statusCode(SC_BAD_REQUEST).and().assertThat().body("message", is(BAD_MESSAGE));
  }

  @Test
  @DisplayName("Create Courier without password")
  @Description("Service return 400 bad request,when Courier create without field password")
  public void createWithoutPassword(){
    courier = GenerationCourier.getRandomCourierWithoutPass();
    ValidatableResponse response = CourierClient.create(courier);
    response.statusCode(SC_BAD_REQUEST).and().assertThat().body("message", is(BAD_MESSAGE));
  }
}